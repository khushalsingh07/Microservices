package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constant.AccountConstant;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;
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
        name = "CRUD REST APIs for Accounts in SBI",
        description = "CRUD REST APIs in SBI to create, update, delete & fetch account details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountsService iAccountsService;
    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new customer & Account inside SBI"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
   public ResponseEntity<ResponseDto> createAccount(@Valid  @RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account REST API",
            description = "REST API to fetch customer & Account detail"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetail(@RequestParam
                                                          @Pattern(regexp = "(^|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                          String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccountDetail(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Account REST API",
            description = "REST API to modify customer & Account detail"
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
     public ResponseEntity<ResponseDto> updateAccountDetail(@Valid @RequestBody CustomerDto customerDto){
         boolean isUpdated = iAccountsService.updateAccount(customerDto);
         if (isUpdated){
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
         }else {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstant.STATUS_500, AccountConstant.MESSAGE_500));
         }
     }

    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete customer & Account detail"
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
    public ResponseEntity<ResponseDto> deleteAccountDetail(@RequestParam
                                                           @Pattern(regexp = "(^|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                           String mobileNumber){
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted){
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        }else {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstant.STATUS_500, AccountConstant.MESSAGE_500));
        }
    }


}
