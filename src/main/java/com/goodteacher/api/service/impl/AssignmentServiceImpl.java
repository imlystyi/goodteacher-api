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
     * @throws NotFoundException if no assignment with specified title exists.
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
     * @throws NotFoundException if no group with specified ID exists.
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
     * Sets grade to the specific assignment.
     *
     * @param id    assignment ID as {@link Long}.
     * @param grade grade as {@link Double}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if no assignment with specified ID exists.
     */
    @Override
    public AssignmentDto grade(final Long id, final Double grade) {
        final Assignment assignmentEntity = findByIdInRepository(id);

        assignmentEntity.setGrade(grade);

        assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    /**
     * Sets comment to the specific assignment.
     *
     * @param id      assignment ID as {@link Long}.
     * @param comment comment as {@link String}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if no assignment with specified ID exists.
     */
    @Override
    public AssignmentDto comment(final Long id, final String comment) {
        final Assignment assignmentEntity = findByIdInRepository(id);

        assignmentEntity.setComment(comment);

        assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    /**
     * Sets deadline to the specific assignment.
     *
     * @param id       assignment ID as {@link Long}.
     * @param deadline deadline as {@link LocalDate}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if no assignment with specified ID exists.
     */
    @Override
    public AssignmentDto setDeadline(final Long id, final LocalDate deadline) {
        final Assignment assignmentEntity = findByIdInRepository(id);

        assignmentEntity.setDeadline(deadline);

        assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    /**
     * Completes the specific assignment.
     *
     * @param id          assignment ID as {@link Long}.
     * @param closingDate date when assignment was completed as {@link LocalDate}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if no assignment with specified ID exists.
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
     * Incompletes the specific assignment.
     *
     * @param id assignment ID as {@link Long}.
     * @return updated assignment as {@link AssignmentDto} if updated successfully.
     * @throws NotFoundException if no assignment with specified ID exists.
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
     * Sets the specific assignment as inactive.
     *
     * @param id assignment ID as {@link Long}.
     * @throws NotFoundException if no assignment with specified ID exists.
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