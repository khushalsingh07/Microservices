package com.eazybytes.cards.controller;

import com.eazybytes.cards.constant.CardConstant;
import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardService;
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
        name = "CRUD REST APIs for Cards in SBI",
        description = "CRUD REST APIs in SBI to create, update, delete & fetch card details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardController {

    private ICardService iCardService;
    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card inside SBI"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam
                                                  @Pattern(regexp = "($|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                  String mobileNumber){
        iCardService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstant.STATUS_201, CardConstant.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card REST API",
            description = "REST API to fetch Card detail"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CardDto> fetchCardDetail(@RequestParam
                                                   @Pattern(regexp = "(^|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                   String mobileNumber){
        CardDto cardDto = iCardService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardDto);
    }

    @Operation(
            summary = "Update Card REST API",
            description = "REST API to modify Card detail"
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
    public ResponseEntity<ResponseDto> updateCardDetail(@Valid @RequestBody CardDto cardDto){
        boolean isUpdated = iCardService.updateCard(cardDto);
        if (isUpdated){
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstant.STATUS_200, CardConstant.MESSAGE_200));
        }else {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardConstant.STATUS_417, CardConstant.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Card REST API",
            description = "REST API to delete card detail"
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
    public ResponseEntity<ResponseDto> deleteCardDetail(@RequestParam
                                                        @Pattern(regexp = "(^|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                        String mobileNumber){
        boolean isDeleted = iCardService.deleteCard(mobileNumber);
        if (isDeleted){
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstant.STATUS_200, CardConstant.MESSAGE_200));
        }else {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardConstant.STATUS_417, CardConstant.MESSAGE_417_DELETE));
        }
    }
}
