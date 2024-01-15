package com.dibyendu.cardsservice.service;

import com.dibyendu.cardsservice.dto.CardDto;

public interface CardsService {
    void createCard(String mobileNumber);
    CardDto fetchCardDetails(String mobileNumber);
    boolean updateCardDetails(CardDto cardDto);
    boolean deleteCard(String mobileNumber);
}
