package com.example.cards.controller;

import com.example.cards.model.Card;
import com.example.cards.model.Customer;
import com.example.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("my-cards")
    public List<Card> getCardDetailsOfCustomer(@RequestBody Customer customer) {
        return cardService.getAllCardDetails(customer.getCustomerId());
    }
}
