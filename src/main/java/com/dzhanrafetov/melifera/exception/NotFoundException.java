package com.dzhanrafetov.melifera.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    //AKO EXTENDSVA Exception vmesto RuntimeException  thread-a umira-toest spira
    //kogato vuznikne exception,no s RuntimeException thread-a produljava

    //a kato e RuntimeException
    //prashtam edin exception kum JVM I GeneralExceptionHandler shte go catchne
    //i shte go prevurne v Response Entity,i shte mojem da vurnem na usera-suotvetnoto
    //suobshtenie
    //ako beshe Exception tozi thread shteshe da umre i nqma da mojem da produljim
    public NotFoundException(String message) {
        super(message);
    }
}
