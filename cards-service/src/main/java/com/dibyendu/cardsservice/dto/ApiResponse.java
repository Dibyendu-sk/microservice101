package com.dibyendu.cardsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ApiResponse <T>{
    private HttpStatus status;
    private String message;
    private T response;
}
