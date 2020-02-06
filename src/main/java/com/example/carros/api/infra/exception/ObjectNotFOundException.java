package com.example.carros.api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFOundException extends RuntimeException {
    public ObjectNotFOundException(String message) {
        super(message);
    }

    public ObjectNotFOundException(String message, Throwable cause) {
        super(message, cause);
    }
}
