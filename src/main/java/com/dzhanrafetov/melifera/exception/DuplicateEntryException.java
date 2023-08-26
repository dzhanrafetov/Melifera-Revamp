package com.dzhanrafetov.melifera.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateEntryException extends RuntimeException {

    public DuplicateEntryException() {
    }

    public DuplicateEntryException(String message) {
        super(message);
    }
}