package com.goodteacher.api.exception;


/**
 * Represents an exception thrown when a request is malformed.
 */
public class BadRequestException extends RuntimeException {
    /**
     * Constructs a new {@link BadRequestException} with the specified message.
     *
     * @param message the exception message.
     */
    public BadRequestException(final String message) {
        super(message);
    }
}
