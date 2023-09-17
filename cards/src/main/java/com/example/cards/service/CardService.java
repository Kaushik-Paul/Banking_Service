package com.example.cards.service;

import com.example.cards.model.Card;
import com.example.cards.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    private static final Logger logger = LoggerFactory.getLogger(CardService.class);

    public List<Card> getAllCardDetails(int customerId) {
        List<Card> cards = cardRepository.findByCustomerId(customerId);

        if (cards != null) {
            logger.info("getCardDetails() method ended");
            return cards;
        }
        return new ArrayList<>();
    }
}
