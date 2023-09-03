package com.example.accounts.service;

import com.example.accounts.model.Account;
import com.example.accounts.model.Customer;
import com.example.accounts.repository.AccountRepository;
import com.example.accounts.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Account getAccountByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId).get();

        Account account = accountRepository.findByCustomerId(customer.getCustomerId());
        return account;
    }
}
