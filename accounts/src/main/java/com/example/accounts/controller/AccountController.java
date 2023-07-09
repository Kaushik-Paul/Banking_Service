package com.example.accounts.controller;

import com.example.accounts.model.Account;
import com.example.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/my-account/")
    public Account getAccountDetails(@RequestParam int id) {
        Account account = accountService.getAccountByCustomerId(id);

        return account;
    }
}
