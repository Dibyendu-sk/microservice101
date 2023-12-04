package com.dibyendu.accountservice.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccountsDto {

    @NotEmpty(message = "Account number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid account number")
    private Long accountNumber;

    @NotEmpty(message = "Account type can't be null or empty")
    private String accountType;
    @NotEmpty(message = "Branch address can't be null or empty")
    private String branchAddress;
}
