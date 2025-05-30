package com.eazybytes.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardDto {

    @NotEmpty(message = "Mobile number can't be null or empty")
    @Pattern(regexp = "(^|[0-9]{10})", message = "Mobile number must be of 10 digit")
    private String mobileNumber;

    @NotEmpty(message = "Card number can't be null or empty")
    @Pattern(regexp = "(^|[0-9]{12})", message = "Mobile number must be of 12 digit")
    private String cardNumber;

    @NotEmpty(message = "Card Type can't be null or empty")
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private int availableAmount;
}
