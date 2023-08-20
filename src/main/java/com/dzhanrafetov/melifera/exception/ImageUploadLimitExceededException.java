package com.dzhanrafetov.melifera.exception;

public class ImageUploadLimitExceededException extends RuntimeException {
    public ImageUploadLimitExceededException(String message) {
        super(message);
    }
}
