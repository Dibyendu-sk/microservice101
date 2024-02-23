package com.dibyendu.loanservice.Dto.response;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loans")
public record LoansContactInfoDto(String message, Map<String,String> contactDetails, List<String> customerCare) {
}
