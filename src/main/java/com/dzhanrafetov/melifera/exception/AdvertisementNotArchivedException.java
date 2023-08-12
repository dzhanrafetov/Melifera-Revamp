package com.dzhanrafetov.melifera.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AdvertisementNotArchivedException extends RuntimeException{
    private static final String message ="The advertisement wanted to delete is not archived!";

    public AdvertisementNotArchivedException() {
        super(message);
    }

    public AdvertisementNotArchivedException(String message) {
        super(message);
    }
}
