package com.dibyendu.loanservice.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Schema(
//        name = "Account Details",
        description = "Schema to hold Loans Details"
)
public class LoansDto {

    @NotEmpty(message = "mobile number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid mobile number")
    @Schema(
            description = "Customer Mobile Number",
            example = "8597706641"
    )
    private String mobileNumber;

    @NotEmpty(message = "Loan number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})",message = "Please enter a valid loan number")
    @Schema(
            description = "Loan Number",
            example = "123291021920"
    )
    private String loanNumber;

    @NotEmpty(message = "Loan type can't be null or empty")
    @Schema(
            description = "Type of Loan",
            example = "Home Loan"
    )
    private String loanType;
        @Positive(message = "Total amount should be greater than zero")
        @Schema(
                description = "Total Loan Amount",
                example = "100000"
        )
    private int totalLoan;

    @PositiveOrZero(message = "Total amount Paid should be Equal or greater than zero")
    @Schema(
            description = "Total Loan Amount Paid",
            example = "1000"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Total Outstanding amount should be Equal or greater than zero")
    @Schema(
            description = "Outstanding Amount",
            example = "99000 "
    )
    private int outstandingAmount;
}
