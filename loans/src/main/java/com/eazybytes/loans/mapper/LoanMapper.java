package com.eazybytes.loans.mapper;

import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.entity.Loans;

public class LoanMapper {

    public static LoanDto mapToLoanDto(Loans loan, LoanDto loanDto){
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setTotalLoan(loan.getTotalLoan());
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());
        return loanDto;
    }

    public static Loans mapToLoan(LoanDto loanDto, Loans loan){
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setLoanType(loanDto.getLoanType());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());
        return loan;
    }
}
