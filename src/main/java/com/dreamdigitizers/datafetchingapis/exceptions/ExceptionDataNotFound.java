package com.dreamdigitizers.datafetchingapis.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionDataNotFound extends ExceptionInternationalized {
    public ExceptionDataNotFound(MessageSource messageSource) {
        super(messageSource);
    }

    public ExceptionDataNotFound(MessageSource messageSource, String message) {
        super(messageSource, message);
    }

    public ExceptionDataNotFound(MessageSource messageSource, String message, Throwable cause) {
        super(messageSource, message, cause);
    }
}
