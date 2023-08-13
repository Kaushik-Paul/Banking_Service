package com.example.loans.service;

import com.example.loans.model.Loan;
import com.example.loans.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> loanDetails(int customerId) {
        List<Loan> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customerId);
        if (loans != null) {
            return loans;
        }
        return new ArrayList<>();
    }
}
