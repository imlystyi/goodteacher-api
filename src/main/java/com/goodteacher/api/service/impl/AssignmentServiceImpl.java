package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.*;
import com.goodteacher.api.repository.AssignmentRepository;
import com.goodteacher.api.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;

    private final TaskService taskService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final GroupService groupService;

    @Override
    public AssignmentDto findById(final Long id) {
        final Assignment assignmentEntity = this.findByIdStream(id);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    @Override
    public Set<AssignmentDto> findByTitle(final String title) {
        return this.findAllByTitleStream(title)
                   .stream()
                   .map(AssignmentMapper::fromEntityToDto)
                   .collect(Collectors.toSet());
    }

    @Override
    public AssignmentDto save(final AssignmentSaveDto assignmentSaveDto) {
        final Assignment assignmentEntity = AssignmentMapper.fromSaveDtoToEntity(assignmentSaveDto);

        final Long taskId = assignmentSaveDto.getTaskId();
        final Long teacherId = assignmentSaveDto.getTeacherId();
        final Long studentId = assignmentSaveDto.getStudentId();

        assignmentEntity.setTask(TaskMapper.fromDtoToEntity(this.taskService.findById(taskId)));
        assignmentEntity.setTeacher(TeacherMapper.fromDtoToEntity(this.teacherService.findById(teacherId)));
        assignmentEntity.setStudent(StudentMapper.fromDtoToEntity(this.studentService.findById(studentId)));

        return AssignmentMapper.fromEntityToDto(this.assignmentRepository.save(assignmentEntity));
    }

    @Override
    public void saveGroup(final AssignmentGroupSaveDto assignmentGroupSaveDto, final Long groupId) {
        final GroupDto groupDto = this.groupService.findById(groupId);

        for (final StudentDto s : groupDto.getStudents()) {
            final Assignment assignmentEntity = AssignmentMapper.fromSaveGroupDtoToEntity(assignmentGroupSaveDto);

            final Long taskId = assignmentGroupSaveDto.getTaskId();
            assignmentEntity.setTask(TaskMapper.fromDtoToEntity(this.taskService.findById(taskId)));
            assignmentEntity.setTeacher(TeacherMapper.fromDtoToEntity(groupDto.getTeacher()));
            assignmentEntity.setStudent(StudentMapper.fromDtoToEntity(s));

            this.assignmentRepository.save(assignmentEntity);
        }
    }

    @Override
    public AssignmentDto grade(final Long id, final Double grade) {
        final Assignment assignmentEntity = this.findByIdStream(id);

        assignmentEntity.setGrade(grade);

        this.assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    @Override
    public AssignmentDto comment(final Long id, final String comment) {
        final Assignment assignmentEntity = this.findByIdStream(id);

        assignmentEntity.setComment(comment);

        this.assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    @Override
    public AssignmentDto setDeadline(final Long id, final LocalDate deadline) {
        final Assignment assignmentEntity = this.findByIdStream(id);

        assignmentEntity.setDeadline(deadline);

        this.assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    @Override
    public AssignmentDto complete(final Long id, final LocalDate closingDate) {
        final Assignment assignmentEntity = this.findByIdStream(id);

        assignmentEntity.setClosingDate(closingDate);
        assignmentEntity.setIsClosed(Boolean.TRUE);

        this.assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    @Override
    public AssignmentDto incomplete(final Long id) {
        final Assignment assignmentEntity = this.findByIdStream(id);

        assignmentEntity.setClosingDate(null);
        assignmentEntity.setIsClosed(Boolean.FALSE);

        this.assignmentRepository.save(assignmentEntity);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    @Override
    public void delete(final Long id) {
        final Assignment assignmentEntity = this.findByIdStream(id);

        assignmentEntity.setIsActive(Boolean.FALSE);

        this.assignmentRepository.save(assignmentEntity);
    }

    private Assignment findByIdStream(final Long id) {
        return this.assignmentRepository.findByIdAndIsActiveTrue(id)
                                        .orElseThrow(() -> new NotFoundException(
                                                "Assignment with id %d not found".formatted(id)));
    }

    private Set<Assignment> findAllByTitleStream(final String title) {
        final Set<Assignment> assignmentEntities = this.assignmentRepository.findAllByTitleAndIsActiveTrue(title);

        if (assignmentEntities.isEmpty()) {
            throw new NotFoundException("Assignments with %s title not found.".formatted(title));
        }

        return assignmentEntities;
    }
}