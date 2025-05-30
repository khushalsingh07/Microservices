package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
public class CustomerDto {
    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 to 30")
    private String name;
    @NotEmpty(message = "Email can't be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;
    @NotEmpty(message = "Mobile number can't be null or empty")
    @Pattern(regexp = "(^|[0-9]{10})", message = "Mobile number must be of 10 digit")
    private String mobileNumber;
    private AccountsDto accountsDto;
}
