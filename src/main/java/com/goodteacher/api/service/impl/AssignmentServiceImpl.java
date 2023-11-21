package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.AssignmentDTO;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.repository.AssignmentRepository;
import com.goodteacher.api.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AssignmentServiceImpl implements AssignmentService {
    private AssignmentRepository assignmentRepository;

    @Override
    public Assignment findById(final UUID id) {
        return assignmentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Assignment with id {%s} not found".formatted(id)));
    }

    @Override
    public AssignmentDTO save(final AssignmentDTO dto) {
        final Assignment toSave = new Assignment();

        toSave.setId(dto.getId());
        updateEntityFromDto(dto, toSave);

        assignmentRepository.save(toSave);

        return AssignmentDTO.toDTO(toSave);
    }

    @Override
    public AssignmentDTO update(final AssignmentDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("AssignmentDTO.id cannot be null");
        }

        final Assignment toUpdate = findById(dto.getId());
        updateEntityFromDto(dto, toUpdate);

        assignmentRepository.save(toUpdate);

        return AssignmentDTO.toDTO(toUpdate);
    }

    @Override
    public void deleteById(final UUID id) {
        final Assignment assignment = findById(id);

        assignment.setIsActive(Boolean.FALSE);

        assignmentRepository.save(assignment);
    }

    private void updateEntityFromDto(final AssignmentDTO assignmentDTO, final Assignment assignment) {
        assignment.setTask(assignmentDTO.getTask());
        assignment.setStudent(assignmentDTO.getStudent());
        assignment.setTeacher(assignmentDTO.getTeacher());
        assignment.setGrade(assignmentDTO.getGrade());
        assignment.setDeadline(assignmentDTO.getDeadline());
    }
}
