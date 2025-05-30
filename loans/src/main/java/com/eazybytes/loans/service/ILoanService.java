package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoanDto;

public interface ILoanService {
    void createAccount(String mobileNumber);

    LoanDto fetchLoan(String mobileNumber);

    boolean updateLoan(LoanDto loanDto);

    boolean deleteAccount(String mobileNumber);
}
