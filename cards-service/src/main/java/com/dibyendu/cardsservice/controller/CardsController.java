package com.dibyendu.cardsservice.controller;

import com.dibyendu.cardsservice.constants.CardConstants;
import com.dibyendu.cardsservice.dto.CardDto;
import com.dibyendu.cardsservice.dto.CardsContactInfoDto;
import com.dibyendu.cardsservice.dto.ErrorResponseDto;
import com.dibyendu.cardsservice.dto.ResponseDto;
import com.dibyendu.cardsservice.service.CardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/microservice101/cards-service", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
        name = "CRUD REST APIs",
        description = "REST APIs in loan service for CREATE,READ,UPDATE and DELETE Card details"
)
@Validated
@Slf4j
public class CardsController {

    private final CardsService cardsService;
    private final CardsContactInfoDto cardsContactInfoDto;

    public CardsController(CardsService cardsService, CardsContactInfoDto cardsContactInfoDto) {
        this.cardsService = cardsService;
        this.cardsContactInfoDto = cardsContactInfoDto;
    }

    @Operation(
            summary = "CREATE card rest api",
            description = "REST API to CREATE card inside cards-service"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Please enter a valid mobile number")
                                                  String mobileNumber) {

        log.info("-----Create Card-----");
        cardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @Operation(
            summary = "GET card rest api",
            description = "REST API to GET card details inside card-service"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CardDto> fetchCardDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Please enter a valid mobile number")
                                                    String mobileNumber) {
        log.info("-----Fetch Card Details-----");
        return ResponseEntity.status(HttpStatus.OK).body(cardsService.fetchCardDetails(mobileNumber));
    }

    @Operation(
            summary = "UPDATE card Details rest api",
            description = "REST API to UPDATE card details inside card-service"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardDto cardDto) {
        boolean isUpdated = cardsService.updateCardDetails(cardDto);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "DELETE card Details rest api",
            description = "REST API to DELETE card inside card-service"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
        }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Please enter a valid mobile number")
                                                         String mobileNumber) {
        boolean isDeleted = cardsService.deleteCard(mobileNumber);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("/getContactInfo")
    public ResponseEntity<CardsContactInfoDto> getContactInfo() {
        return ResponseEntity.ok().body(cardsContactInfoDto);
    }
}
