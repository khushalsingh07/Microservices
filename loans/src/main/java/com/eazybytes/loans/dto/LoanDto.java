package com.eazybytes.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jdk.jfr.Description;
import lombok.Data;

@Data
public class LoanDto {

    @NotEmpty(message = "Mobile number can't be null or empty")
    @Pattern(regexp = "(^|[0-9]{10})", message = "Mobile number must be of 10 digit")
    private String mobileNumber;

    @NotEmpty(message = "Loan number can't be null or empty")
    @Pattern(regexp = "(^|[0-9]{12})", message = "Mobile number must be of 12 digit")
    private String loanNumber;

    @NotEmpty(message = "Loan Type can't be null or empty")
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    private int outstandingAmount;
}
