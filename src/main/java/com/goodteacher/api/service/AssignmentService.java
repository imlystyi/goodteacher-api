package com.goodteacher.api.service;

import com.goodteacher.api.dto.AssignmentDTO;

import java.util.UUID;

public interface AssignmentService {
    AssignmentDTO findDTOById(UUID id);
    AssignmentDTO save(AssignmentDTO assignmentDTO);
    AssignmentDTO update(AssignmentDTO assignmentDTO);
    void deleteById(UUID id);
}
