package com.dibyendu.accountservice.Dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccountsDto {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
