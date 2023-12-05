package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.AssignmentDTO;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Task;
import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.repository.AssignmentRepository;
import com.goodteacher.api.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

// TODO-1, Oleksandr: Fix issues in AssignmentServiceImpl methods

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;

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

        assignment.setGrade(assignmentDTO.getGrade());
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
        updateAssignment.setGrade(assignmentDTO.getGrade());
        updateAssignment.setDeadline(assignmentDTO.getDeadline());
        assignmentRepository.save(updateAssignment);

        return AssignmentDTO.toDTO(updateAssignment);
    }

    @Override
    public void deleteById(final UUID id) {
        final Assignment assignment = findById(id);
        assignment.setIsActive(Boolean.FALSE);
        assignmentRepository.save(assignment);
    }

    private Assignment findById(final UUID id) {
        return assignmentRepository.findById(id)
                                   .orElseThrow(() -> new IllegalArgumentException("Assignment not found."));
    }
}