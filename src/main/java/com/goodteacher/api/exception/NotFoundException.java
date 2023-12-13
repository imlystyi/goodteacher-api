package com.goodteacher.api.exception;

/**
 * Represents an exception thrown when a resource is not found.
 */
public class NotFoundException extends RuntimeException {
    /**
     * Constructs a new {@link NotFoundException} with the specified message.
     *
     * @param message the exception message.
     */
    public NotFoundException(final String message) {
        super(message);
    }
}
