package com.example.cards.controller;

import com.example.cards.config.CardServiceConfig;
import com.example.cards.model.Card;
import com.example.cards.model.Customer;
import com.example.cards.model.Properties;
import com.example.cards.service.CardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardServiceConfig cardConfig;

    @PostMapping("my-cards")
    public List<Card> getCardDetailsOfCustomer(@RequestHeader("bank-service-correlation-id") String correlationId, @RequestBody Customer customer) {
        return cardService.getAllCardDetails(customer.getCustomerId());
    }

    @GetMapping("/card/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardConfig.getMsg(), cardConfig.getBuildVersion(), cardConfig.getMailDetails(), cardConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
