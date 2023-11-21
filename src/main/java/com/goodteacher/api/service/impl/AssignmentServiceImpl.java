package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.AssignmentDTO;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.repository.AssignmentRepository;
import com.goodteacher.api.service.AssignmentService;

import java.util.UUID;

public class AssignmentServiceImpl implements AssignmentService {
    private AssignmentRepository assignmentRepository;

    @Override
    public AssignmentDTO findDTOById(final UUID id) {
        final Assignment assignment = findById(id);

        return AssignmentDTO.toDTO(assignment);
    }

    @Override
    public AssignmentDTO save(final AssignmentDTO assignmentDTO) {
        final Assignment assignment = new Assignment();

        assignment.setId(assignmentDTO.getId());
        assignment.setTask(assignmentDTO.getTask());
        assignment.setStudent(assignmentDTO.getStudent());
        assignment.setTeacher(assignmentDTO.getTeacher());
        assignment.setGrade(assignmentDTO.getGrade());
        assignment.setDeadline(assignmentDTO.getDeadline());

        return AssignmentDTO.toDTO(assignment);
    }

    @Override
    public AssignmentDTO update(final AssignmentDTO assignmentDTO) {
        if (assignmentDTO.getId() == null) {
            throw new IllegalArgumentException("Assignment id cannot be null");
        }

        final Assignment updateAssignment = findById(assignmentDTO.getId());

        updateAssignment.setTask(assignmentDTO.getTask());
        updateAssignment.setStudent(assignmentDTO.getStudent());
        updateAssignment.setTeacher(assignmentDTO.getTeacher());
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
                                   .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));
    }
}