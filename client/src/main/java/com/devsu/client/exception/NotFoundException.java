package com.devsu.client.exception;

import java.io.Serial;

/**
 * Exceptions that can be thrown when data it's not found
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = -4480183715278800783L;
}
