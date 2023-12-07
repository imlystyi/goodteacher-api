package com.goodteacher.api.resource;

import com.goodteacher.api.dto.AssignmentDto;
import com.goodteacher.api.dto.AssignmentSaveDto;
import com.goodteacher.api.service.AssignmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentResource {
    private final AssignmentService assignmentService;

    // region GET mappings

    @GetMapping("/find/id/{id}")
    public ResponseEntity<AssignmentDto> findById(final @PathVariable @Positive Long id) {
        final AssignmentDto assignmentDto = this.assignmentService.findById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(assignmentDto);
    }

    @GetMapping("/find/title/{title}")
    public ResponseEntity<Set<AssignmentDto>> findByTitle(final @PathVariable String title) {
        final Set<AssignmentDto> assignmentDtos = this.assignmentService.findAllByTitle(title);

        return ResponseEntity.status(HttpStatus.FOUND).body(assignmentDtos);
    }

    // endregion

    // region POST mappings

    @PostMapping("/save")
    public ResponseEntity<AssignmentDto> save(final @RequestBody @Valid AssignmentSaveDto assignmentSaveDto) {
        final AssignmentDto assignmentDto = this.assignmentService.save(assignmentSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentDto);
    }

    // endregion

    // region PATCH mappings

    @PatchMapping("/grade/{id}/{grade}")
    public ResponseEntity<AssignmentDto> grade(final @PathVariable @Positive Long id,
                                               final @PathVariable Double grade) {
        final AssignmentDto assignmentDto = this.assignmentService.grade(id, grade);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    @PatchMapping("/resetGrade/{id}")
    public ResponseEntity<AssignmentDto> resetGrade(final @PathVariable @Positive Long id) {
        final AssignmentDto assignmentDto = this.assignmentService.grade(id, null);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    @PatchMapping("/comment/{id}/{comment}")
    public ResponseEntity<AssignmentDto> comment(final @PathVariable @Positive Long id,
                                                 final @PathVariable String comment) {
        final AssignmentDto assignmentDto = this.assignmentService.comment(id, comment);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    @PatchMapping("/resetComment/{id}")
    public ResponseEntity<AssignmentDto> resetComment(final @PathVariable @Positive Long id) {
        final AssignmentDto assignmentDto = this.assignmentService.comment(id, null);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    @PatchMapping("/setDeadline/{id}/{deadline}")
    public ResponseEntity<AssignmentDto> setDeadline(final @PathVariable @Positive Long id,
                                                     final @PathVariable LocalDate deadline) {
        final AssignmentDto assignmentDto = this.assignmentService.setDeadline(id, deadline);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    @PatchMapping("/resetDeadline/{id}")
    public ResponseEntity<AssignmentDto> resetDeadline(final @PathVariable @Positive Long id) {
        final AssignmentDto assignmentDto = this.assignmentService.setDeadline(id, null);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    @PatchMapping("/complete/{id}/{closingDate}")
    public ResponseEntity<AssignmentDto> complete(final @PathVariable @Positive Long id,
                                                  final @PathVariable LocalDate closingDate) {
        final AssignmentDto assignmentDto = this.assignmentService.complete(id, closingDate);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    @PatchMapping("/incomplete/{id}")
    public ResponseEntity<AssignmentDto> incomplete(final @PathVariable @Positive Long id) {
        final AssignmentDto assignmentDto = this.assignmentService.incomplete(id);

        return ResponseEntity.status(HttpStatus.OK).body(assignmentDto);
    }

    // endregion

    // region DELETE mappings

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(final @PathVariable @Positive Long id) {
        this.assignmentService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // endregion
}