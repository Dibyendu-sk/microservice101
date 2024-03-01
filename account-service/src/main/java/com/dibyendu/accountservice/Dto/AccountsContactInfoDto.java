package com.dibyendu.accountservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

// to change the data at runtime, we're changing the record type to class.(As record made all the value final)
@ConfigurationProperties(prefix = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsContactInfoDto{
    String message;
    Map<String,String> contactDetails;
    List<String> customerCare;
}
