package com.goodteacher.api.resource;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.service.AssignmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

/**
 * The REST controller to handle the requests to the assignment resource.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/assignments")
public class AssignmentResource {
    // region Fields

    private final AssignmentService assignmentService;

    // endregion

    // region GET mappings

    /**
     * Represents the HTTP GET request method for finding an assignment by its ID.
     *
     * @param id ID to search by as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the found assignment as {@link AssignmentDto} in the response body if the assignment was successfully found; otherwise, result depends on problem.
     */
    @GetMapping("/find/id/{id}")
    public ResponseEntity<AssignmentDto> findById(final @PathVariable Long id) {
        final AssignmentDto assignmentDto = assignmentService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    /**
     * Represents the HTTP GET request method for finding an assignment by its title.
     *
     * @param title Title to search by as {@link String}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the found assignment as {@link AssignmentDto} in the response body if the assignment was successfully found; otherwise, result depends on problem.
     */
    @GetMapping("/find/title/{title}")
    public ResponseEntity<Set<AssignmentDto>> findByTitle(final @PathVariable String title) {
        final Set<AssignmentDto> assignmentDtos = assignmentService.findByTitle(title);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDtos);
    }

    // endregion

    // region POST mappings

    /**
     * Represents the HTTP POST request method for saving an assignment.
     *
     * @param assignmentSaveDto assignment to save as {@link AssignmentSaveDto}. Must be contained in the request body and be successfully validated.
     * @return {@link ResponseEntity} with HTTP status 201 "Created" and the saved assignment as {@link AssignmentSaveDto} in the response body if the assignment was successfully saved; otherwise, result depends on problem.
     */
    @PostMapping("/create")
    public ResponseEntity<AssignmentDto> create(final @RequestBody @Valid AssignmentSaveDto assignmentSaveDto) {
        final AssignmentDto assignmentDto = assignmentService.save(assignmentSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentDto);
    }

    /**
     * Represents the HTTP POST request method for saving an assignment to all students in the specific group.
     *
     * @param groupId                group ID as {@link Long}. Must be contained in the path as the path variable.
     * @param assignmentGroupSaveDto assignment to save as {@link AssignmentGroupSaveDto}. Must be contained in the request body and be successfully validated.
     * @return {@link ResponseEntity} with HTTP status 204 "No Content" if the assignment was successfully saved; otherwise, result depends on problem.
     */
    @PostMapping("/group-create/{groupId}")
    public ResponseEntity<Void> groupCreate(final @PathVariable Long groupId,
                                            final @RequestBody @Valid AssignmentGroupSaveDto assignmentGroupSaveDto) {
        assignmentService.groupSave(assignmentGroupSaveDto, groupId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // endregion

    // region PATCH mappings

    /**
     * Represents the HTTP PATCH request method for setting the grade of the specific assignment.
     *
     * @param id    assignment ID as {@link Long}. Must be contained in the path as the path variable.
     * @param grade new grade as {@link Double}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated assignment as {@link AssignmentDto} in the response body if the grade was successfully set; otherwise, result depends on problem.
     */
    @PatchMapping("/update/grade/{id}/{grade}")
    public ResponseEntity<AssignmentDto> setGrade(final @PathVariable Long id,
                                                  final @PathVariable Double grade) {
        final AssignmentDto assignmentDto = assignmentService.updateGrade(id, grade);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    /**
     * Represents the HTTP PATCH request method for resetting the grade of the specific assignment.
     *
     * @param id assignment ID as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated assignment as {@link AssignmentDto} in the response body if the grade was successfully reset; otherwise, result depends on problem.
     */
    @PatchMapping("/update/resetGrade/{id}")
    public ResponseEntity<AssignmentDto> resetGrade(final @PathVariable @Positive Long id) {
        final AssignmentDto assignmentDto = assignmentService.updateGrade(id, null);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    /**
     * Represents the HTTP PATCH request method for setting the comment of the specific assignment.
     *
     * @param id      assignment ID as {@link Long}. Must be contained in the path as the path variable.
     * @param comment new comment as {@link String}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated assignment as {@link AssignmentDto} in the response body if the grade was successfully set; otherwise, result depends on problem.
     */
    @PatchMapping("/update/comment/{id}/{comment}")
    public ResponseEntity<AssignmentDto> setComment(final @PathVariable Long id,
                                                    final @PathVariable String comment) {
        final AssignmentDto assignmentDto = assignmentService.updateComment(id, comment);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    /**
     * Represents the HTTP PATCH request method for resetting the comment of the specific assignment.
     *
     * @param id assignment ID as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated assignment as {@link AssignmentDto} in the response body if the comment was successfully reset; otherwise, result depends on problem.
     */
    @PatchMapping("/update/resetComment/{id}")
    public ResponseEntity<AssignmentDto> resetComment(final @PathVariable Long id) {
        final AssignmentDto assignmentDto = assignmentService.updateComment(id, null);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    /**
     * Represents the HTTP PATCH request method for setting the deadline of the specific assignment.
     *
     * @param id       assignment ID as {@link Long}. Must be contained in the path as the path variable.
     * @param deadline new deadline as {@link LocalDate}. Must be contained in the path as the path variable and be in ISO date format.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated assignment as {@link AssignmentDto} in the response body if the deadline was successfully set; otherwise, result depends on problem.
     */
    @PatchMapping("/update/deadline/{id}/{deadline}")
    public ResponseEntity<AssignmentDto> setDeadline(final @PathVariable Long id,
                                                     final @PathVariable
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                     LocalDate deadline) {
        final AssignmentDto assignmentDto = assignmentService.setDeadline(id, deadline);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    /**
     * Represents the HTTP PATCH request method for resetting the deadline of the specific assignment.
     *
     * @param id assignment ID as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated assignment as {@link AssignmentDto} in the response body if the deadline was successfully reset; otherwise, result depends on problem.
     */
    @PatchMapping("/update/resetDeadline/{id}")
    public ResponseEntity<AssignmentDto> resetDeadline(final @PathVariable Long id) {
        final AssignmentDto assignmentDto = assignmentService.setDeadline(id, null);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    /**
     * Represents the HTTP PATCH request method for setting the completion of the specific assignment to {@code true}.
     *
     * @param id          assignment ID as {@link Long}. Must be contained in the path as the path variable.
     * @param closingDate date when assignment was completed as {@link LocalDate}. Must be contained in the path as the path variable and be in ISO date format.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated assignment as {@link AssignmentDto} in the response body if the completion was successfully set; otherwise, result depends on problem.
     */
    @PatchMapping("/update/complete/{id}/{closingDate}")
    public ResponseEntity<AssignmentDto> complete(final @PathVariable Long id,
                                                  final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate closingDate) {
        final AssignmentDto assignmentDto = assignmentService.complete(id, closingDate);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    /**
     * Represents the HTTP PATCH request method for setting the completion of the specific assignment to {@code false}.
     *
     * @param id          assignment ID as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated assignment as {@link AssignmentDto} in the response body if the completion was successfully set; otherwise, result depends on problem.
     */
    @PatchMapping("/update/incomplete/{id}")
    public ResponseEntity<AssignmentDto> incomplete(final @PathVariable Long id) {
        final AssignmentDto assignmentDto = assignmentService.incomplete(id);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    // endregion

    // region DELETE mappings

    /**
     * Represents the HTTP DELETE request method for deleting an assignment softly.
     *
     * @param id assignment ID as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 204 "No Content" if the assignment was successfully deleted; otherwise, result depends on problem.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(final @PathVariable @Positive Long id) {
        assignmentService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // endregion
}