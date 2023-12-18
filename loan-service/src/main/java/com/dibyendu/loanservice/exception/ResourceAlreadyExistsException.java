package com.dibyendu.loanservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String mesaage){
        super(mesaage);
        log.error("----Resource Already Exist Exception--- "+super.getMessage());
    }
}
