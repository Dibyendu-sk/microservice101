package com.dibyendu.loanservice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s not found with the given input data %s : %s", resourceName, fieldName, fieldValue));

        log.error("Resource Not Found Exception - "+super.getMessage());
    }
}
