package com.example.loans.controller;

import com.example.loans.config.LoanServiceConfig;
import com.example.loans.model.Customer;
import com.example.loans.model.Loan;
import com.example.loans.model.Properties;
import com.example.loans.service.LoanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanServiceConfig loanConfig;

    @PostMapping("my-loans")
    public List<Loan> getAllLoanDetails(@RequestBody Customer customer) {
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
