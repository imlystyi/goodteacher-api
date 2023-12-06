// BAD YET


package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.AssignmentDto;
import com.goodteacher.api.entity.*;
import com.goodteacher.api.mapper.AssignmentMapper;
import com.goodteacher.api.mapper.StudentMapper;
import com.goodteacher.api.mapper.TaskMapper;
import com.goodteacher.api.mapper.TeacherMapper;
import com.goodteacher.api.repository.AssignmentRepository;
import com.goodteacher.api.repository.StudentRepository;
import com.goodteacher.api.repository.TaskRepository;
import com.goodteacher.api.repository.TeacherRepository;
import com.goodteacher.api.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

// TODO-1, Oleksandr: Fix issues in AssignmentServiceImpl methods

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final TaskRepository taskRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public AssignmentDto findById(final UUID id) {
        final Assignment entity = this.findByIdStream(id);

        return AssignmentMapper.fromEntityToDto(entity);
    }

    @Override
    public AssignmentDto save(final AssignmentDto dto) {
        final Assignment assignment = new Assignment();

        assignment.setTitle(dto.getTitle());
        assignment.setId(dto.getId());
        assignment.setTask(TaskMapper.fromDtoToEntity(dto.getTask()));
        assignment.setStudent(StudentMapper.fromDtoToEntity(dto.getStudent()));
        assignment.setTeacher(TeacherMapper.fromDtoToEntity(dto.getTeacher()));
        assignment.setGrade(dto.getGrade());
        assignment.setComment(dto.getComment());
        assignment.setDeadline(dto.getDeadline());
        assignment.setClosingDate(dto.getClosingDate());

        this.assignmentRepository.save(assignment);

        return dto;
    }

    @Override
    public AssignmentDto update(final AssignmentDto dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Assignment.id cannot be null.");
        }

        final Assignment updateAssignment = findByIdStream(dto.getId());

        Task task = taskRepository.findById(dto.getTaskId())
                                  .orElseThrow(() -> new IllegalArgumentException("Task not found."));
        updateAssignment.setTask(task);

        Student student = studentRepository.findById(dto.getStudentId())
                                           .orElseThrow(() -> new IllegalArgumentException("Student not found."));
        updateAssignment.setStudent(student);

        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                                           .orElseThrow(() -> new IllegalArgumentException("Teacher not found."));
        updateAssignment.setTeacher(teacher);

        assignmentRepository.save(updateAssignment);

        return AssignmentDto.toDTO(updateAssignment);
    }

    public void doneAssignment(final UUID id) {
        final Assignment assignment = findByIdStream(id);
        assignment.setIsActive(Boolean.FALSE);
        assignmentRepository.save(assignment);
    }

    @Override
    public void deleteById(final UUID id) {
        assignmentRepository.deleteById(id);
    }


    public Assignment gradeAssignment(final UUID id, final Double grade) {
        final Assignment assignment = findByIdStream(id);
        assignment.setGrade(grade);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment deleteGradeAssignment(final UUID id) {
        final Assignment assignment = findByIdStream(id);
        assignment.setGrade(null);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment commentAssignment(final UUID id, final String comment) {
        final Assignment assignment = findByIdStream(id);
        assignment.setComment(comment);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment deleteCommentAssignment(final UUID id) {
        final Assignment assignment = findByIdStream(id);
        assignment.setComment(null);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment deadlineAssignment(final UUID id, final LocalDate deadline) {
        final Assignment assignment = findByIdStream(id);
        assignment.setDeadline(deadline);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment deleteDeadlineAssignment(final UUID id) {
        final Assignment assignment = findByIdStream(id);
        assignment.setDeadline(null);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment closingDateAssignment(final UUID id, final LocalDate closingDate) {
        final Assignment assignment = findByIdStream(id);
        assignment.setClosingDate(closingDate);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment deleteClosingDateAssignment(final UUID id) {
        final Assignment assignment = findByIdStream(id);
        assignment.setClosingDate(null);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment findByIdStream(final UUID id) {
        return this.assignmentRepository.findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException("Assignment not found."));
    }

}