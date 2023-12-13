package com.goodteacher.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleServiceException(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionResponseDto> handleServiceException(final ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponseDto> handleServiceException(final BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleServiceException(
            final MethodArgumentNotValidException ex) {
        final String message = ex.getBindingResult().getAllErrors().stream()
                                 .map(e -> ex.getBindingResult().getFieldError() == null
                                           ? ""
                                           : ex.getBindingResult().getFieldError().getField()
                                             + " " + e.getDefaultMessage())
                                 .reduce((s1, s2) -> s1 + "; " + s2)
                                 .orElse("Problem while creating error message");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(message));
    }
}
