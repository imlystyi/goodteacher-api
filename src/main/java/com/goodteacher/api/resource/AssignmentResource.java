package com.goodteacher.api.resource;

import com.goodteacher.api.dto.AssignmentDto;
import com.goodteacher.api.service.AssignmentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

// TODO-1: Improve codestyle

@RestController
@RequestMapping("/api/assignments")
public class AssignmentResource {
    private AssignmentService assignmentService;

    @GetMapping("/find/{id}")
    public AssignmentDto findById(@PathVariable final UUID id) {
        return assignmentService.findById(id);
    }

    @PostMapping("/save")
    public AssignmentDto save(final @RequestBody AssignmentDto assignmentDTO) {
        return assignmentService.save(assignmentDTO);
    }

    @PutMapping("/update")
    public AssignmentDto update(final @RequestBody AssignmentDto assignmentDTO) {
        return assignmentService.update(assignmentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable final UUID id) {
        assignmentService.deleteById(id);
    }

    @PostMapping("/grade/{id}")
    public void doneAssignment(final @PathVariable UUID id) {
        assignmentService.doneAssignment(id);
    }

    @PostMapping("/grade/{id}/{grade}")
    public void gradeAssignment(@PathVariable UUID id, @PathVariable  Double grade) {
        assignmentService.gradeAssignment(id,grade);
    }

    @DeleteMapping("/grade/{id}")
    public void deleteGradeAssignment(@PathVariable  UUID id) {
        assignmentService.deleteGradeAssignment(id);
    }

    @PostMapping("/comment/{id}/{comment}")
    public void commentAssignment(@PathVariable final UUID id, @PathVariable  String comment) {
        assignmentService.commentAssignment(id,comment);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteCommentAssignment(@PathVariable  UUID id) {
        assignmentService.deleteCommentAssignment(id);
    }

    @PostMapping("/deadline/{id}/{deadline}")
    public void deadlineAssignment(@PathVariable UUID id, @PathVariable final LocalDate deadline) {
        assignmentService.deadlineAssignment(id,deadline);
    }

    @DeleteMapping("/deadline/{id}")
    public void deleteDeadlineAssignment(@PathVariable  UUID id) {
        assignmentService.deleteDeadlineAssignment(id);
    }

    @PostMapping("/closingDate/{id}/{closingDate}")
    public void closingDateAssignment(@PathVariable UUID id,@PathVariable LocalDate closingDate) {
        assignmentService.closingDateAssignment(id,closingDate);
    }

    @DeleteMapping("/closingDate/{id}")
    public void deleteClosingDateAssignment(@PathVariable UUID id) {
        assignmentService.deleteClosingDateAssignment(id);
    }


}