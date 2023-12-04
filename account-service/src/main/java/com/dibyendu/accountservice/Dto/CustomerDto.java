package com.dibyendu.accountservice.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerDto {

    @NotEmpty(message = "Name can't be null or empty")
    @Size(min=3,max=30,message = "The length of the customer name should be between 5 to 30")
    private String name;

    @NotEmpty(message = "Email can't be null or empty")
    @Email(message = "Please enter valid email format")
    private String email;

    @NotEmpty(message = "mobile number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid mobile number")
    private String mobileNumber;
    private AccountsDto accountDetails;
}
