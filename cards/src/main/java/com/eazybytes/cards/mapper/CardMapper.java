package com.eazybytes.cards.mapper;

import com.eazybytes.cards.constant.CardConstant;
import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.entity.Cards;

public class CardMapper {

    public static CardDto mapToCardDto(Cards card, CardDto cardDto){
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setTotalLimit(card.getTotalLimit());
        cardDto.setMobileNumber(card.getMobileNumber());
        cardDto.setAmountUsed(card.getAmountUsed());
        cardDto.setAvailableAmount(card.getAvailableAmount());
        return cardDto;
    }

    public static Cards mapToCard(CardDto cardDto, Cards card){
        card.setCardNumber(cardDto.getCardNumber());
        card.setCardType(cardDto.getCardType());
        card.setTotalLimit(cardDto.getTotalLimit());
        card.setMobileNumber(cardDto.getMobileNumber());
        card.setAmountUsed(cardDto.getAmountUsed());
        card.setAvailableAmount(cardDto.getAvailableAmount());
        return card;
    }
}
