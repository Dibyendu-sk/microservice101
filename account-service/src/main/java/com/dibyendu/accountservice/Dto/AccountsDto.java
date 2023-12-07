package com.dibyendu.accountservice.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Schema(
//        name = "Account Details",
        description = "Schema to hold Account Details"
)
public class AccountsDto {

    @NotEmpty(message = "Account number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid account number")
    @Schema(
            description = "Account Number",
            example = "1741450748"
    )
    private Long accountNumber;

    @NotEmpty(message = "Account type can't be null or empty")
    @Schema(
            description = "Account Type",
            example = "savings"
    )
    private String accountType;

    @NotEmpty(message = "Branch address can't be null or empty")
    @Schema(
            description = "Branch Address",
            example = "Bhubaneswar"
    )
    private String branchAddress;
}
