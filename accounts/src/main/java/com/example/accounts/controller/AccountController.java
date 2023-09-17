package com.example.accounts.controller;

import com.example.accounts.config.AccountServiceConfig;
import com.example.accounts.model.*;
import com.example.accounts.service.AccountService;
import com.example.accounts.service.clients.CardsFeignClient;
import com.example.accounts.service.clients.LoansFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountServiceConfig accountConfig;

    @Autowired
    private CardsFeignClient cardsFeignClient;

    @Autowired
    private LoansFeignClient loansFeignClient;

    @GetMapping("/my-account/")
    public Account getAccountDetails(@RequestParam int id) {
        Account account = accountService.getAccountByCustomerId(id);

        return account;
    }

    @GetMapping("/account/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountConfig.getMsg(), accountConfig.getBuildVersion(), accountConfig.getMailDetails(), accountConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

    @PostMapping("/my-customer-details")
    @CircuitBreaker(name = "detailsForCustomerSupportApp", fallbackMethod = "myCustomerDetailsFallBack")
    @Retry(name = "retryForCustomerDetails", fallbackMethod = "myCustomerDetailsFallBack")
    public CustomerDetails myCustomerDetails(@RequestHeader("bank-service-correlation-id") String correlationId, @RequestBody Customer customer) {
        logger.info("myCustomerDetails() method started");
        Account account = accountService.getAccountByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getLoansDetails(correlationId, customer);
        List<Cards> cards = cardsFeignClient.getCardDetails(correlationId, customer);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccount(account);
        customerDetails.setLoans(loans);
        customerDetails.setCards(cards);
        logger.info("myCustomerDetails() method ended");

        return customerDetails;

    }

    // Fallback method for circuit breaker detailsForCustomerSupportApp
    public CustomerDetails myCustomerDetailsFallBack(@RequestHeader("bank-service-correlation-id") String correlationId, Customer customer, Throwable t) {
        Account account = accountService.getAccountByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getLoansDetails(correlationId, customer);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccount(account);
        customerDetails.setLoans(loans);
        return customerDetails;

    }

    @GetMapping("/say-hello")
    @RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
    public String sayHello() {
        return "Hello, Welcome to Banking service";
    }

    private String sayHelloFallback(Throwable t) {
        return "Hi, Welcome to Banking service";
    }
}
