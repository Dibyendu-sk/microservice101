package com.dibyendu.loanservice.controller;

import com.dibyendu.loanservice.Dto.LoansDto;
import com.dibyendu.loanservice.Dto.response.ResponseDto;
import com.dibyendu.loanservice.constants.LoanConstants;
import com.dibyendu.loanservice.service.LoanService;
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

    @PostMapping("/createLoan")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid mobile number")
                                                      String mobileNumber){

        loanService.createLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoanConstants.STATUS_201,LoanConstants.MESSAGE_201));
    }

    @GetMapping("/fetchLoanDetails")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid mobile number")String mobileNumber){
        LoansDto loansDto = loanService.fetchLoanDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

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
