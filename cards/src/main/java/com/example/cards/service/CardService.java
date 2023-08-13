package com.example.cards.service;

import com.example.cards.model.Card;
import com.example.cards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getAllCardDetails(int customerId) {
        List<Card> cards = cardRepository.findByCustomerId(customerId);

        if (cards != null) {
            return cards;
        }
        return new ArrayList<>();
    }
}
