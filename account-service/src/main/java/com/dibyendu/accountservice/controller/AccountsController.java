package com.dibyendu.accountservice.controller;

import com.dibyendu.accountservice.Dto.AccountsContactInfoDto;
import com.dibyendu.accountservice.Dto.CustomerDto;
import com.dibyendu.accountservice.Dto.response.ErrorResponseDto;
import com.dibyendu.accountservice.Dto.response.ResponseDto;
import com.dibyendu.accountservice.constants.AccountsConstants;
import com.dibyendu.accountservice.exception.ResourceNotFoundException;
import com.dibyendu.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/microservice101/account-service", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Tag(
        name = "CRUD REST APIs",
        description = "REST APIs in accounts service for CREATE,READ,UPDATE and DELETE Account details"
)
@Validated
public class AccountsController {


    private final AccountService accountService;
    private Environment env;
    private final AccountsContactInfoDto accountsContactInfoDto;

    @Autowired
    public AccountsController(AccountService accountService, AccountsContactInfoDto accountsContactInfoDto, Environment env) {
        this.accountService = accountService;
        this.accountsContactInfoDto = accountsContactInfoDto;
        this.env = env;
    }

    @Operation(
            summary = "CREATE account rest api",
            description = "REST API to CREATE customer and account inside account-service"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customersDto) {

        accountService.createAccount(customersDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "GET account Details rest api",
            description = "REST API to GET customer and account inside account-service"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Please enter a valid mobile number")
                                                           String mobileNumber) throws ResourceNotFoundException {
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "UPDATE account Details rest api",
            description = "REST API to UPDATE customer and account inside account-service"
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
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) throws ResourceNotFoundException {
        boolean isUpdated = accountService.updateAccount(customerDto);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "DELETE account Details rest api",
            description = "REST API to DELETE customer and account inside account-service"
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
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
                                                     String mobileNumber) throws ResourceNotFoundException {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(hidden = true)
    @GetMapping("getUser")
    public ResponseEntity<String> getUser() {
        String user = env.getProperty("USERNAME");
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/getContactInfo")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity.ok().body(accountsContactInfoDto);
    }
}
