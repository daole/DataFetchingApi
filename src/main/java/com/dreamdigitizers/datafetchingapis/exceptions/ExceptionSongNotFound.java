package com.dreamdigitizers.datafetchingapis.exceptions;

import org.springframework.context.MessageSource;

public class ExceptionSongNotFound extends ExceptionDataNotFound {
    private String name;
    private Throwable cause;

    public ExceptionSongNotFound(MessageSource messageSource, String name) {
        super(messageSource);
        this.name = name;
    }

    public ExceptionSongNotFound(MessageSource messageSource, String name, Throwable cause) {
        super(messageSource);
        this.name = name;
        this.initCause(cause);
    }

    @Override
    public String getMessage() {
        return String.format(this.getMessageFromResource("errorMessage.SongNotFound"), this.name);
    }
}
