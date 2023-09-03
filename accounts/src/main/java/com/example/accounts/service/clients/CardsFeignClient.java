package com.example.accounts.service.clients;

import com.example.accounts.model.Cards;
import com.example.accounts.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("cards")
public interface CardsFeignClient {

    @PostMapping(value = "my-cards", consumes = "application/json")
    List<Cards> getCardDetails(@RequestBody Customer customer);
}
