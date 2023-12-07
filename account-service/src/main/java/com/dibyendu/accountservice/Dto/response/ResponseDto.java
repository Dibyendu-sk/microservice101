package com.dibyendu.accountservice.Dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        description = "Schema To Hold Successful Response Information"
)
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
