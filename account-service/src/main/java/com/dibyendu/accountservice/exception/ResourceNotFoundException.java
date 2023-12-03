package com.dibyendu.accountservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {

        super(String.format("%s not found with the given input data %s : %s", resourceName, fieldName, fieldValue));
        log.error("Resource Not found exception - " + super.getMessage());
    }
}
