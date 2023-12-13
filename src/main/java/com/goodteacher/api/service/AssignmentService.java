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
     * @throws NotFoundException if no assignment with specified title exists.
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
     * @throws NotFoundException if no group with specified ID exists.
     */
    void groupSave(AssignmentGroupSaveDto assignmentGroupSaveDto, Long groupId);

    /**
     * Sets grade to the specific assignment.
     *
     * @param id    assignment ID as {@link Long}.
     * @param grade grade as {@link Double}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if no assignment with specified ID exists.
     */
    AssignmentDto grade(Long id, Double grade);

    /**
     * Sets comment to the specific assignment.
     *
     * @param id      assignment ID as {@link Long}.
     * @param comment comment as {@link String}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if no assignment with specified ID exists.
     */
    AssignmentDto comment(Long id, String comment);

    /**
     * Sets deadline to the specific assignment.
     *
     * @param id       assignment ID as {@link Long}.
     * @param deadline deadline as {@link LocalDate}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if no assignment with specified ID exists.
     */
    AssignmentDto setDeadline(Long id, LocalDate deadline);

    /**
     * Completes the specific assignment.
     *
     * @param id          assignment ID as {@link Long}.
     * @param closingDate date when assignment was completed as {@link LocalDate}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if no assignment with specified ID exists.
     */
    AssignmentDto complete(Long id, LocalDate closingDate);

    /**
     * Incompletes the specific assignment.
     *
     * @param id assignment ID as {@link Long}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if no assignment with specified ID exists.
     */
    AssignmentDto incomplete(Long id);

    /**
     * Sets the specific assignment as inactive.
     *
     * @param id assignment ID as {@link Long}.
     * @throws NotFoundException if no assignment with specified ID exists.
     */
    void delete(Long id);
}