package com.eazybytes.loans.service;

import com.eazybytes.loans.constant.LoanConstant;
import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoanMapper;
import com.eazybytes.loans.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService{
    private LoanRepository loanRepository;

    @Override
    public void createAccount(String mobileNumber) {
        Optional<Loans> optionalLoans = loanRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber){
      Loans newLoan = new Loans();
      long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstant.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstant.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstant.NEW_LOAN_LIMIT);
        return  newLoan;
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));
        return LoanMapper.mapToLoanDto(loans, new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loans loans = loanRepository.findByMobileNumber(loanDto.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", loanDto.getMobileNumber()));
        LoanMapper.mapToLoan(loanDto, loans);
        loanRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));
        loanRepository.deleteById(loans.getLoanId());
        return true;
    }
}
