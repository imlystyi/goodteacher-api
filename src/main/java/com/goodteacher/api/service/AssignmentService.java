package com.goodteacher.api.service;

import com.goodteacher.api.dto.AssignmentDTO;
import com.goodteacher.api.entity.Assignment;

import java.util.UUID;

public interface AssignmentService {
    Assignment findById(final UUID id);
    AssignmentDTO save(final AssignmentDTO assignmentDTO);
    AssignmentDTO update(final AssignmentDTO assignmentDTO);
    void deleteById(final UUID id);
}
