package com.dibyendu.cardsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseDto {
    @Schema(
            description = "Representing Status Code",
            example = "status code"
    )
    private String statusCode;

    @Schema(
            description = "Representing Status Message",
            example = "Status Message"
    )
    private String statusMsg;
}
