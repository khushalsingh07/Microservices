package com.eazybytes.loans.controller;

import com.eazybytes.loans.constant.LoanConstant;
import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.dto.ResponseDto;
import com.eazybytes.loans.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Loans in SBI",
        description = "CRUD REST APIs in SBI to create, update, delete & fetch loan details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoanController {

    private ILoanService iLoanService;
    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new loan inside SBI"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
   public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                     @Pattern(regexp = "($|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                     String mobileNumber){
        iLoanService.createAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LoanConstant.STATUS_201, LoanConstant.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Loan REST API",
            description = "REST API to fetch Loan detail"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetail(@RequestParam
                                                          @Pattern(regexp = "(^|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                          String mobileNumber){
        LoanDto loanDto = iLoanService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loanDto);
    }

    @Operation(
            summary = "Update Account REST API",
            description = "REST API to modify Loan detail"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @PutMapping("/update")
     public ResponseEntity<ResponseDto> updateLoanDetail(@Valid @RequestBody LoanDto loanDto){
         boolean isUpdated = iLoanService.updateLoan(loanDto);
         if (isUpdated){
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstant.STATUS_200, LoanConstant.MESSAGE_200));
         }else {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(LoanConstant.STATUS_417, LoanConstant.MESSAGE_UPDATE));
         }
     }

    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete loan detail"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetail(@RequestParam
                                                           @Pattern(regexp = "(^|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                           String mobileNumber){
        boolean isDeleted = iLoanService.deleteAccount(mobileNumber);
        if (isDeleted){
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstant.STATUS_200, LoanConstant.MESSAGE_200));
        }else {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(LoanConstant.STATUS_417, LoanConstant.MESSAGE_DELETE));
        }
    }
}
