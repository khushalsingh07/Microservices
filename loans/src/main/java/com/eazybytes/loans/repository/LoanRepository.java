package com.eazybytes.loans.repository;

import com.eazybytes.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loans, Long> {

    Optional<Loans> findByMobileNumber(String aLong);

    Optional<Loans> findByLoanNumber(String loanNumber);
}
