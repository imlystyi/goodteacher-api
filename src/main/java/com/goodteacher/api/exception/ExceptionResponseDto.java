package com.goodteacher.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a response to an exception.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseDto {
    private String message;
}
