package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
public class AccountsDto {
    @NotEmpty(message = "Account number can't be null or empty")
    @Pattern(regexp = "(^|[0-9]{10})", message = "Account no must be of 10 digit")
    private Long accountNumber;
    @NotEmpty(message = "Account type can't be null or empty")
    private String accountType;
    @NotEmpty(message = "Branch Address can't be null or empty")
    private String branchAddress;
}
