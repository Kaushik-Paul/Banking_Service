package com.example.loans.controller;

import com.example.loans.model.Customer;
import com.example.loans.model.Loan;
import com.example.loans.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("my-loans")
    public List<Loan> getAllLoanDetails(@RequestBody Customer customer) {
        return loanService.loanDetails(customer.getCustomerId());
    }
}
