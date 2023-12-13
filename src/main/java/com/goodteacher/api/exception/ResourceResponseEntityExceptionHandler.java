/*
 * Copyright 2023 imlystyi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.goodteacher.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles exceptions thrown by the application.
 */
@ControllerAdvice
public class ResourceResponseEntityExceptionHandler {
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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponseDto> handleServiceException(final BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleServiceException(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionResponseDto> handleServiceException(final ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponseDto(ex.getMessage()));
    }
}
