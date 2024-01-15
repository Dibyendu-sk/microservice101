package com.dibyendu.cardsservice.service.impl;

import com.dibyendu.cardsservice.constants.CardConstants;
import com.dibyendu.cardsservice.dto.CardDto;
import com.dibyendu.cardsservice.entity.Cards;
import com.dibyendu.cardsservice.exception.ResourceAlreadyExistsException;
import com.dibyendu.cardsservice.exception.ResourceNotFoundException;
import com.dibyendu.cardsservice.mapper.CardsMapper;
import com.dibyendu.cardsservice.repository.CardsRepository;
import com.dibyendu.cardsservice.service.CardsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class CardServiceImpl implements CardsService {

    private CardsRepository cardsRepository;

    public void createCard(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);

        if (cards.isPresent()) {
            throw new ResourceAlreadyExistsException("card with mobile number " + mobileNumber + " already exists.");
        }

        cardsRepository.save(createNewCard(mobileNumber));
        log.info("-----Card successfully created-----");
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();

        Long cardNumber = 1000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(cardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);

        return newCard;
    }

    @Override
    public CardDto fetchCardDetails(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardDto(cards, new CardDto());
    }

    @Override
    public boolean updateCardDetails(CardDto cardDto) {
        Cards cards = cardsRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "cardNumber", cardDto.getCardNumber())
        );

        log.info("-----------Old Card Details---------- " + cards.toString());
        CardsMapper.mapToCards(cardDto, cards);
        log.info("-----------Updated Card Details---------- " + cards.toString());
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        log.info("--------Card deleted with card number " + cards.getCardNumber() + " --------");
        cardsRepository.deleteById(cards.getCardId());

        return true;
    }
}
