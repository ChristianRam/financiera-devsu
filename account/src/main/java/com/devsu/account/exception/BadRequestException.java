package com.devsu.account.exception;

import java.io.Serial;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
    @Serial
    private static final long serialVersionUID = -2135136780393912703L;
}
