package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.AssignmentDTO;
import com.goodteacher.api.entity.*;
import com.goodteacher.api.repository.AssignmentRepository;
import com.goodteacher.api.repository.StudentRepository;
import com.goodteacher.api.repository.TaskRepository;
import com.goodteacher.api.repository.TeacherRepository;
import com.goodteacher.api.service.AssignmentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

// TODO-1, Oleksandr: Fix issues in AssignmentServiceImpl methods

@Service
@RequiredArgsConstructor
public class  AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final TaskRepository taskRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public AssignmentDTO findDTOById(final UUID id) {
        final Assignment assignment = findById(id);

        return AssignmentDTO.toDTO(assignment);
    }


    @Override
    public AssignmentDTO save(final AssignmentDTO assignmentDTO) {
        final Assignment assignment = new Assignment();

        assignment.setId(assignmentDTO.getId());
        Task task = taskRepository.findById(assignmentDTO.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found."));
        assignment.setTask(task);

        Student student = studentRepository.findById(assignmentDTO.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found."));
        assignment.setStudent(student);

        Teacher teacher = teacherRepository.findById(assignmentDTO.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found."));
        assignment.setTeacher(teacher);

        assignment.setDeadline(assignmentDTO.getDeadline());

        return AssignmentDTO.toDTO(assignment);
    }

    @Override
    public AssignmentDTO update(final AssignmentDTO assignmentDTO) {
        if (assignmentDTO.getId() == null) {
            throw new IllegalArgumentException("Assignment.id cannot be null.");
        }

        final Assignment updateAssignment = findById(assignmentDTO.getId());

        Task task = taskRepository.findById(assignmentDTO.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found."));
        updateAssignment.setTask(task);

        Student student = studentRepository.findById(assignmentDTO.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found."));
        updateAssignment.setStudent(student);

        Teacher teacher = teacherRepository.findById(assignmentDTO.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found."));
        updateAssignment.setTeacher(teacher);

        assignmentRepository.save(updateAssignment);

        return AssignmentDTO.toDTO(updateAssignment);
    }

    public void doneAssignment(final UUID id) {
        final Assignment assignment = findById(id);
        assignment.setIsActive(Boolean.FALSE);
        assignmentRepository.save(assignment);
    }
    @Override
    public void deleteById(final UUID id) {
        assignmentRepository.deleteById(id);
    }

    public Assignment findById(final UUID id) {
        return assignmentRepository.findById(id)
                                   .orElseThrow(() -> new IllegalArgumentException("Assignment not found."));
    }

    public Assignment gradeAssignment(final UUID id, final Double grade) {
        final Assignment assignment = findById(id);
        assignment.setGrade(grade);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment deleteGradeAssignment(final UUID id) {
        final Assignment assignment = findById(id);
        assignment.setGrade(null);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment commentAssignment(final UUID id, final String comment) {
        final Assignment assignment = findById(id);
        assignment.setComment(comment);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment deleteCommentAssignment(final UUID id) {
        final Assignment assignment = findById(id);
        assignment.setComment(null);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment deadlineAssignment(final UUID id, final LocalDate deadline) {
        final Assignment assignment = findById(id);
        assignment.setDeadline(deadline);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment deleteDeadlineAssignment(final UUID id) {
        final Assignment assignment = findById(id);
        assignment.setDeadline(null);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment closingDateAssignment(final UUID id, final LocalDate closingDate) {
        final Assignment assignment = findById(id);
        assignment.setClosingDate(closingDate);
        assignmentRepository.save(assignment);
        return assignment;
    }

    public Assignment deleteClosingDateAssignment(final UUID id) {
        final Assignment assignment = findById(id);
        assignment.setClosingDate(null);
        assignmentRepository.save(assignment);
        return assignment;
    }



}