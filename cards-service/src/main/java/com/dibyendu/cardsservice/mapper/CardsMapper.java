package com.dibyendu.cardsservice.mapper;

import com.dibyendu.cardsservice.dto.CardDto;
import com.dibyendu.cardsservice.entity.Cards;

public class CardsMapper {
    public static CardDto mapToCardDto(Cards cards,CardDto cardDto){
        cardDto.setCardNumber(cards.getCardNumber());
        cardDto.setAmountUsed(cards.getAmountUsed());
        cardDto.setAvailableAmount(cards.getAvailableAmount());
        cardDto.setMobileNumber(cards.getMobileNumber());
        cardDto.setCardType(cards.getCardType());
        cardDto.setTotalLimit(cards.getTotalLimit());

        return cardDto;
    }

    public static Cards mapToCards(CardDto cardDto,Cards cards){
        cards.setAvailableAmount(cardDto.getAvailableAmount());
        cards.setCardNumber(cardDto.getCardNumber());
        cards.setMobileNumber(cardDto.getMobileNumber());
        cards.setCardType(cardDto.getCardType());
        cards.setTotalLimit(cardDto.getTotalLimit());
        cards.setAmountUsed(cardDto.getAmountUsed());

        return cards;
    }
}
