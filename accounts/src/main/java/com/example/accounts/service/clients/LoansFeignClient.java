package com.example.accounts.service.clients;

import com.example.accounts.model.Customer;
import com.example.accounts.model.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "loans")
public interface LoansFeignClient {

    @PostMapping(value = "my-loans", consumes = "application/json")
    List<Loans> getLoansDetails(@RequestBody Customer customer);
}
