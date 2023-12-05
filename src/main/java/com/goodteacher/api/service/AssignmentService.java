package com.goodteacher.api.service;

import com.goodteacher.api.dto.AssignmentDTO;
import com.goodteacher.api.entity.Assignment;

import java.time.LocalDate;
import java.util.UUID;

public interface AssignmentService {
    AssignmentDTO findDTOById(UUID id);
    AssignmentDTO save(AssignmentDTO assignmentDTO);
    AssignmentDTO update(AssignmentDTO assignmentDTO);

    Assignment gradeAssignment( UUID id,Double grade);
    Assignment deleteGradeAssignment(UUID id);
    Assignment commentAssignment(UUID id,String comment);

    Assignment deleteCommentAssignment(UUID id);
    Assignment deadlineAssignment(UUID id, LocalDate deadline);
    Assignment deleteDeadlineAssignment(UUID id);
    Assignment closingDateAssignment(UUID id, LocalDate closingDate);
    Assignment deleteClosingDateAssignment(UUID id);
    void deleteById(UUID id);
    void doneAssignment(UUID id);

}