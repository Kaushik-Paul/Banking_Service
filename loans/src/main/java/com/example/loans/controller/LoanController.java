package com.example.loans.controller;

import com.example.loans.config.LoanServiceConfig;
import com.example.loans.model.Customer;
import com.example.loans.model.Loan;
import com.example.loans.model.Properties;
import com.example.loans.service.LoanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanServiceConfig loanConfig;

    @PostMapping("my-loans")
    public List<Loan> getAllLoanDetails(@RequestHeader("bank-service-correlation-id") String correlationId, @RequestBody Customer customer) {
        logger.info("getLoansDetails() method started");
        logger.info("getLoansDetails() method ended");
        return loanService.loanDetails(customer.getCustomerId());
    }

    @GetMapping("/loan/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loanConfig.getMsg(), loanConfig.getBuildVersion(), loanConfig.getMailDetails(), loanConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
