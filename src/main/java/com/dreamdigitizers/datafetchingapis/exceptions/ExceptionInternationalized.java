package com.dreamdigitizers.datafetchingapis.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class ExceptionInternationalized extends Exception {
    private MessageSource messageSource;

    public ExceptionInternationalized(MessageSource messageSource) {
        super();
        this.messageSource = messageSource;
    }

    public ExceptionInternationalized(MessageSource messageSource, String message) {
        super(message);
        this.messageSource = messageSource;
    }

    public ExceptionInternationalized(MessageSource messageSource, String message, Throwable cause) {
        super(message, cause);
        this.messageSource = messageSource;
    }

    protected final String getMessageFromResource(String resourceId) {
        return this.messageSource.getMessage(resourceId, null, LocaleContextHolder.getLocale());
    }
}
