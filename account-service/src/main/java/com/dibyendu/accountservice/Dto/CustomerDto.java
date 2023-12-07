package com.dibyendu.accountservice.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Schema(
//        name = "Customer",
        description = "Schema to hold Customer and Account Details"
)
public class CustomerDto {

    @NotEmpty(message = "Name can't be null or empty")
    @Size(min=3,max=30,message = "The length of the customer name should be between 5 to 30")
    @Schema(
            description = "Customer Name",
            example = "Dibyendu Kar"
    )
    private String name;

    @NotEmpty(message = "Email can't be null or empty")
    @Email(message = "Please enter valid email format")
    @Schema(
            description = "Customer Email",
            example = "dibyendu01kar@gmail.com"
    )
    private String email;

    @NotEmpty(message = "mobile number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid mobile number")
    @Schema(
            description = "Customer Mobile Number",
            example = "8597706641"
    )
    private String mobileNumber;

    @Schema(
            description = "Account Details of the Customer"
    )
    private AccountsDto accountDetails;
}
