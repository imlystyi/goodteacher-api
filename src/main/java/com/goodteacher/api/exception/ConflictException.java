package com.goodteacher.api.exception;

/**
 * Represents an exception thrown when a request is conflicting.
 */
public class ConflictException extends RuntimeException {
    /**
     * Constructs a new {@link ConflictException} with the specified message.
     *
     * @param message the exception message.
     */
    public ConflictException(final String message) {
        super(message);
    }
}
