package com.goodteacher.api.service;

import com.goodteacher.api.dto.AssignmentDTO;
import com.goodteacher.api.entity.Assignment;

import java.util.UUID;

public interface AssignmentService {
    Assignment findById(UUID id);
    AssignmentDTO save(AssignmentDTO assignmentDTO);
    AssignmentDTO update(AssignmentDTO assignmentDTO);
    void deleteById(UUID id);
}
