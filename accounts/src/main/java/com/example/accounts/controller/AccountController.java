package com.example.accounts.controller;

import com.example.accounts.config.AccountServiceConfig;
import com.example.accounts.model.Account;
import com.example.accounts.model.Properties;
import com.example.accounts.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountServiceConfig accountConfig;

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
}
