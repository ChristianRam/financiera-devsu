package com.devsu.account.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = -4480183715278800783L;
}
