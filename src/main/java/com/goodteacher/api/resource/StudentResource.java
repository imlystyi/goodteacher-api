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

package com.goodteacher.api.resource;

import com.goodteacher.api.dto.IdentityDto;
import com.goodteacher.api.dto.NameDto;
import com.goodteacher.api.dto.StudentDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

/**
 * The REST controller to handle the requests to the student resource.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentResource {
    // region Fields

    private final StudentService studentService;

    // endregion

    // region GET mappings

    /**
     * Represents the HTTP GET request method for finding a student by their ID.
     *
     * @param id ID to search by as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the found student as {@link StudentDto} in the response body if the student was successfully found; otherwise, result depends on problem.
     */
    @GetMapping("/find/id/{id}")
    public ResponseEntity<StudentDto> findById(final @PathVariable Long id) {
        final StudentDto foundStudentDto = studentService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(foundStudentDto);
    }

    /**
     * Represents the HTTP GET request method for finding a student by their nickname.
     *
     * @param nickname nickname to search by as {@link String}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the found student as {@link StudentDto} in the response body if the student was successfully found; otherwise, result depends on problem.
     */
    @GetMapping("/find/nickname/{nickname}")
    public ResponseEntity<StudentDto> findByNickname(final @PathVariable String nickname) {
        final StudentDto foundStudentDto = studentService.findByNickname(nickname);

        return ResponseEntity.status(HttpStatus.OK).body(foundStudentDto);
    }

    /**
     * Represents the HTTP GET request method for finding a students by their name.
     *
     * @param nameDto name to search by as {@link NameDto}. Must be contained in the request body and be successfully validated.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the found students as {@link Set}{@code <}{@link StudentDto}{@code >} in the response body if any student with the specified name was successfully found; otherwise, result depends on problem.
     */
    @GetMapping("/find/name")
    public ResponseEntity<Set<StudentDto>> findAllByName(final @RequestBody @Valid NameDto nameDto) {
        final Set<StudentDto> foundStudentDtos = studentService.findAllByName(nameDto);

        return ResponseEntity.status(HttpStatus.OK).body(foundStudentDtos);
    }

    /**
     * Represents the HTTP GET request method for signing in a student.
     *
     * @param identityDto identity to signing in by as {@link IdentityDto}. Must be contained in the request body and be successfully validated.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and {@code true} in the response body if the signing in was successful; otherwise, {@link ResponseEntity} with HTTP status 200 "OK" and {@code false} in the response body.
     */
    @GetMapping("/sign-in")
    public ResponseEntity<Boolean> signIn(final @RequestBody @Valid IdentityDto identityDto) {
        final boolean isSignedIn = studentService.signIn(identityDto);

        return ResponseEntity.status(HttpStatus.OK).body(isSignedIn);
    }

    // endregion

    // region POST mappings

    /**
     * Represents the HTTP POST request method for saving a student.
     *
     * @param userDto student to save as {@link UserDto}. Must be contained in the request body and be successfully validated.
     * @return {@link ResponseEntity} with HTTP status 201 "Created" and the saved student as {@link StudentDto} in the response body if the student was successfully saved; otherwise, result depends on problem.
     */
    @PostMapping("/create")
    public ResponseEntity<StudentDto> create(final @RequestBody @Valid UserDto userDto) {
        final StudentDto savedStudentDto = studentService.save(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudentDto);
    }

    // endregion

    // region PATCH mappings

    /**
     * Represents the HTTP PATCH request method for updating student's email.
     *
     * @param id    student ID as {@link Long}. Must be contained in the path as the path variable.
     * @param email new email as {@link String}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 204 "No Content" if the email was successfully updated; otherwise, result depends on problem.
     */
    @PatchMapping("/update/email/{id}/{email}")
    public ResponseEntity<Void> updateEmail(final @PathVariable Long id,
                                            final @PathVariable String email) {
        studentService.updateEmail(id, email);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Represents the HTTP PATCH request method for updating student's password.
     *
     * @param id       student ID as {@link Long}. Must be contained in the path as the path variable.
     * @param password new password as {@link String}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 204 "No Content" if the password was successfully updated; otherwise, result depends on problem.
     */
    @PatchMapping("/update/password/{id}/{password}")
    public ResponseEntity<Void> updatePassword(final @PathVariable Long id,
                                               final @PathVariable String password) {
        studentService.updatePassword(id, password);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Represents the HTTP PATCH request method for updating student's birthdate.
     *
     * @param id        student ID as {@link Long}. Must be contained in the path as the path variable.
     * @param birthDate new birthdate as {@link Date}. Must be contained in the path as the path variable and be in ISO date format.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated student as {@link StudentDto} in the response body if the birthdate was successfully updated; otherwise, result depends on problem.
     */
    @PatchMapping("/update/birth-date/{id}/{birthDate}")
    public ResponseEntity<StudentDto> updateBirthDate(final @PathVariable Long id,
                                                      final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                      Date birthDate) {
        final LocalDate parsedBirthDate = LocalDate.ofInstant(birthDate.toInstant(), ZoneId.systemDefault());

        final StudentDto updatedStudentDto = studentService.updateBirthDate(id, parsedBirthDate);

        return ResponseEntity.status(HttpStatus.OK).body(updatedStudentDto);
    }

    // endregion

    // region PUT mappings

    /**
     * Represents the HTTP PUT request method for updating student's name.
     *
     * @param id      student ID as {@link Long}. Must be contained in the path as the path variable.
     * @param nameDto new name as {@link NameDto}. Must be contained in the request body and be successfully validated.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated student as {@link StudentDto} in the response body if the name was successfully updated; otherwise, result depends on problem.
     */
    @PutMapping("/update/name/{id}")
    public ResponseEntity<StudentDto> updateName(final @PathVariable Long id,
                                                 final @RequestBody @Valid NameDto nameDto) {
        final StudentDto updatedStudentDto = studentService.updateName(id, nameDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedStudentDto);
    }

    // endregion

    // region DELETE mappings

    /**
     * Represents the HTTP DELETE request method for deleting a student softly.
     *
     * @param id student ID as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 204 "No Content" if the student was successfully deleted; otherwise, result depends on problem.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(final @PathVariable Long id) {
        studentService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // endregion
}
