package com.goodteacher.api.service;

import com.goodteacher.api.dto.AssignmentDto;
import com.goodteacher.api.dto.AssignmentGroupSaveDto;
import com.goodteacher.api.dto.AssignmentSaveDto;
import com.goodteacher.api.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Set;

/**
 * Interface that provides the functionality for manipulating the assignment models.
 */
public interface AssignmentService {
    /**
     * Finds an assignment by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found assignment as {@link AssignmentDto} if the assignment with specified ID exists.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    AssignmentDto findById(Long id);

    /**
     * Finds assignments by title.
     *
     * @param title title to search by as {@link String}.
     * @return found assignments as {@link Set}{@code <}{@link AssignmentDto}{@code >} if any assignment with specified title exists.
     * @throws NotFoundException if the assignment with the specified title does not exist.
     */
    Set<AssignmentDto> findByTitle(String title);

    /**
     * Saves the assignment in the repository.
     *
     * @param assignmentSaveDto assignment to save as {@link AssignmentSaveDto}.
     * @return saved assignment as {@link AssignmentDto} if saved successfully.
     */
    AssignmentDto save(AssignmentSaveDto assignmentSaveDto);

    /**
     * Saves the assignment to all students in the specific group.
     *
     * @param assignmentGroupSaveDto assignment to save as {@link AssignmentGroupSaveDto}.
     * @param groupId                group ID as {@link Long}.
     * @throws NotFoundException if the group with the specified ID does not exist.
     */
    void groupSave(AssignmentGroupSaveDto assignmentGroupSaveDto, Long groupId);

    /**
     * Updates the grade of the specific assignment.
     *
     * @param id    assignment ID as {@link Long}.
     * @param grade new grade as {@link Double}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    AssignmentDto updateGrade(Long id, Double grade);

    /**
     * Updates the comment of the specific assignment.
     *
     * @param id      assignment ID as {@link Long}.
     * @param comment new comment as {@link String}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    AssignmentDto updateComment(Long id, String comment);

    /**
     * Updates the deadline of the specific assignment.
     *
     * @param id       assignment ID as {@link Long}.
     * @param deadline new deadline as {@link LocalDate}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    AssignmentDto setDeadline(Long id, LocalDate deadline);

    /**
     * Updates the completion of the specific assignment to {@code true}.
     *
     * @param id          assignment ID as {@link Long}.
     * @param closingDate date when assignment was completed as {@link LocalDate}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    AssignmentDto complete(Long id, LocalDate closingDate);

    /**
     * Updates the completion of the specific assignment to {@code false}.
     *
     * @param id assignment ID as {@link Long}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    AssignmentDto incomplete(Long id);

    /**
     * Sets the specific assignment inactive.
     *
     * @param id assignment ID as {@link Long}.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    void delete(Long id);
}