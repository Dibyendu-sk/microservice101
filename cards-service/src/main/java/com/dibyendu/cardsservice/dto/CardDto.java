package com.dibyendu.cardsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDto {

    @NotEmpty(message = "mobile number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid mobile number")
    @Schema(
            description = "Customer Mobile Number",
            example = "8597706641"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid card number")
    @Schema(
            description = "Card Number",
            example = "1232910219"
    )
    private String cardNumber;

    @NotEmpty(message = "Card type can't be null or empty")
    @Schema(
            description = "Type of the Card",
            example = "Credit Card"
    )
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    @Schema(
            description = "Total Loan Amount",
            example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Total amount Paid should be Equal or greater than zero")
    @Schema(
            description = "Total Card Amount Paid",
            example = "1000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "Total Outstanding amount should be Equal or greater than zero")
    @Schema(
            description = "Outstanding Amount",
            example = "99000 "
    )
    private int availableAmount;
}
