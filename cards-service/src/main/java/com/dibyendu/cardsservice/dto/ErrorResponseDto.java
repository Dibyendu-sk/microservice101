package com.dibyendu.cardsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    @Schema(
            description = "API Invoked By Client"
    )
    private String apiPath;
    @Schema(
            description = "Error Code Representing The Error Happened"
    )
    private HttpStatus statusCode;

    @Schema(
            description = "Error Message"
    )
    private String errorMessage;

    @Schema(
            description = "Time When The Error Occurs"
    )
    private LocalDateTime errorTime;
}
