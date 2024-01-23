package com.dibyendu.loanservice.controller;

import com.dibyendu.loanservice.Dto.LoansDto;
import com.dibyendu.loanservice.Dto.response.ErrorResponseDto;
import com.dibyendu.loanservice.Dto.response.ResponseDto;
import com.dibyendu.loanservice.constants.LoanConstants;
import com.dibyendu.loanservice.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

@RestController
@RequestMapping(path = "/api/microservice101/loan-service", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Tag(
        name = "CRUD REST APIs",
        description = "REST APIs in loan service for CREATE,READ,UPDATE and DELETE Loan details"
)
@Validated
public class LoansController {

    private LoanService loanService;

    @Operation(
            summary = "CREATE loan rest api",
            description = "REST API to CREATE customer and account inside cards-service"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping("/createLoan")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid mobile number")
                                                      String mobileNumber){

        loanService.createLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoanConstants.STATUS_201,LoanConstants.MESSAGE_201));
    }

    @Operation(
            summary = "GET card rest api",
            description = "REST API to GET loan details inside loan-service"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/fetchLoanDetails")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid mobile number")String mobileNumber){
        LoansDto loansDto = loanService.fetchLoanDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Operation(
            summary = "UPDATE loan Details rest api",
            description = "REST API to UPDATE loan details inside loan-service"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    }
    )
    @PutMapping("/updateLoanDetails")
    public ResponseEntity<ResponseDto> updateLoanDetals(@Valid @RequestBody LoansDto loansDto){
        boolean isUpdated=loanService.updateLoanDetails(loansDto);

        if (isUpdated){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto(LoanConstants.STATUS_200,LoanConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoanConstants.STATUS_417,LoanConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "DELETE loan Details rest api",
            description = "REST API to DELETE loans inside loan-service"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    }
    )
    @DeleteMapping("/deleteLoanDetails")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid mobile number")String mobileNumber){
        boolean isDeleted= loanService.deleteLoanDetails(mobileNumber);

        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstants.STATUS_200,LoanConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoanConstants.STATUS_417,LoanConstants.MESSAGE_417_DELETE));
        }
    }

}
