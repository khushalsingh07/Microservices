package com.eazybytes.cards.service;

import com.eazybytes.cards.constant.CardConstant;
import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.CardAlreadyExistsException;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardMapper;
import com.eazybytes.cards.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService{
    private CardRepository cardRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCard.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobile no: "+mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber){
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L +new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstant.CREDIT_CARD);
        newCard.setTotalLimit(CardConstant.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstant.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber));
        return CardMapper.mapToCardDto(cards, new CardDto());
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        Cards cards = cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "cardNumber", cardDto.getCardNumber()));
        CardMapper.mapToCard(cardDto, cards);
        cardRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardRepository.deleteById(cards.getCardId());
        return true;
    }
}
