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

package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Task;
import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.AssignmentMapper;
import com.goodteacher.api.repository.AssignmentRepository;
import com.goodteacher.api.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main implementer of {@link AssignmentService} interface.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService {
    // region Fields

    private final AssignmentRepository assignmentRepository;

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final GroupService groupService;
    private final TaskService taskService;

    // endregion

    // region Methods

    /**
     * Finds an assignment by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found assignment as {@link AssignmentDto} if the assignment with specified ID exists.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    @Override
    public AssignmentDto findById(final Long id) {
        final Assignment assignmentEntity = findByIdInRepository(id);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    /**
     * Finds assignments by title.
     *
     * @param title title to search by as {@link String}.
     * @return found assignments as {@link Set}{@code <}{@link AssignmentDto}{@code >} if any assignment with specified title exists.
     * @throws NotFoundException if the assignment with the specified title does not exist.
     */
    @Override
    public Set<AssignmentDto> findByTitle(final String title) {
        return findAllByTitleInRepository(title)
                .stream()
                .map(AssignmentMapper::fromEntityToDto)
                .collect(Collectors.toSet());
    }

    /**
     * Saves the assignment in the repository.
     *
     * @param assignmentSaveDto assignment to save as {@link AssignmentSaveDto}.
     * @return saved assignment as {@link AssignmentDto} if saved successfully.
     */
    @Override
    public AssignmentDto save(final AssignmentSaveDto assignmentSaveDto) {
        final Assignment assignmentEntity = AssignmentMapper.fromSaveDtoToEntity(assignmentSaveDto);

        final Long taskId = assignmentSaveDto.getTaskId();
        final Long teacherId = assignmentSaveDto.getTeacherId();
        final Long studentId = assignmentSaveDto.getStudentId();

        assignmentEntity.setTask(taskService.findEntityById(taskId));
        assignmentEntity.setTeacher(teacherService.findEntityById(teacherId));
        assignmentEntity.setStudent(studentService.findEntityById(studentId));

        final Assignment savedAssignmentEntity = assignmentRepository.save(assignmentEntity);

        studentService.addAssignment(studentId, savedAssignmentEntity);

        return AssignmentMapper.fromEntityToDto(savedAssignmentEntity);
    }

    /**
     * Saves the assignment to all students in the specific group.
     *
     * @param assignmentGroupSaveDto assignment to save as {@link AssignmentGroupSaveDto}.
     * @param groupId                group ID as {@link Long}.
     * @throws NotFoundException if the group with the specified ID does not exist.
     */
    @Override
    public void groupSave(final AssignmentGroupSaveDto assignmentGroupSaveDto, final Long groupId) {
        final GroupDto groupDto = groupService.findById(groupId);

        final Task taskEntity = taskService.findEntityById(assignmentGroupSaveDto.getTaskId());
        final Teacher teacherEntity = teacherService.findEntityById(groupDto.getTeacher().getId());

        for (final StudentDto s : groupDto.getStudents()) {
            final Long studentId = s.getId();

            final Assignment assignmentEntity = AssignmentMapper.fromSaveGroupDtoToEntity(assignmentGroupSaveDto);
            final Student studentEntity = studentService.findEntityById(studentId);

            assignmentEntity.setTask(taskEntity);
            assignmentEntity.setTeacher(teacherEntity);
            assignmentEntity.setStudent(studentEntity);

            final Assignment savedAssignmentEntity = assignmentRepository.save(assignmentEntity);

            studentService.addAssignment(studentId, savedAssignmentEntity);
        }
    }

    /**
     * Updates the grade of the specific assignment.
     *
     * @param id    assignment ID as {@link Long}.
     * @param grade new grade as {@link Double}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    @Override
    public AssignmentDto updateGrade(final Long id, final Double grade) {
        final Assignment assignmentEntity = findByIdInRepository(id);

        assignmentEntity.setGrade(grade);

        assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    /**
     * Updates the comment of the specific assignment.
     *
     * @param id      assignment ID as {@link Long}.
     * @param comment new comment as {@link String}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    @Override
    public AssignmentDto updateComment(final Long id, final String comment) {
        final Assignment assignmentEntity = findByIdInRepository(id);

        assignmentEntity.setComment(comment);

        assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    /**
     * Updates the deadline of the specific assignment.
     *
     * @param id       assignment ID as {@link Long}.
     * @param deadline new deadline as {@link LocalDate}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    @Override
    public AssignmentDto setDeadline(final Long id, final LocalDate deadline) {
        final Assignment assignmentEntity = findByIdInRepository(id);

        assignmentEntity.setDeadline(deadline);

        assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    /**
     * Updates the completion of the specific assignment to {@code true}.
     *
     * @param id          assignment ID as {@link Long}.
     * @param closingDate date when assignment was completed as {@link LocalDate}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    @Override
    public AssignmentDto complete(final Long id, final LocalDate closingDate) {
        final Assignment assignmentEntity = findByIdInRepository(id);

        assignmentEntity.setClosingDate(closingDate);
        assignmentEntity.setIsClosed(Boolean.TRUE);

        assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    /**
     * Updates the completion of the specific assignment to {@code false}.
     *
     * @param id assignment ID as {@link Long}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    @Override
    public AssignmentDto incomplete(final Long id) {
        final Assignment assignmentEntity = findByIdInRepository(id);

        assignmentEntity.setClosingDate(null);
        assignmentEntity.setIsClosed(Boolean.FALSE);

        assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    /**
     * Sets the specific assignment inactive.
     *
     * @param id assignment ID as {@link Long}.
     * @throws NotFoundException if the assignment with specified ID does not exist.
     */
    @Override
    public void delete(final Long id) {
        final Assignment assignmentEntity = findByIdInRepository(id);

        assignmentEntity.setIsActive(Boolean.FALSE);

        assignmentRepository.save(assignmentEntity);
    }

    private Assignment findByIdInRepository(final Long id) {
        return assignmentRepository.findByIdAndIsActiveTrue(id)
                                   .orElseThrow(() -> new NotFoundException(
                                           "Assignment with id %d not found".formatted(id)));
    }

    private Set<Assignment> findAllByTitleInRepository(final String title) {
        final Set<Assignment> assignmentEntities = assignmentRepository.findAllByTitleAndIsActiveTrue(title);

        if (assignmentEntities.isEmpty()) {
            throw new NotFoundException("Assignments with %s title not found".formatted(title));
        }

        return assignmentEntities;
    }

    // endregion
}