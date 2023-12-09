package com.goodteacher.api.service;

import com.goodteacher.api.dto.AssignmentDto;
import com.goodteacher.api.dto.AssignmentGroupSaveDto;
import com.goodteacher.api.dto.AssignmentSaveDto;

import java.time.LocalDate;
import java.util.Set;

public interface AssignmentService {
    AssignmentDto findById(Long id);

    Set<AssignmentDto> findByTitle(String title);

    AssignmentDto saveOne(AssignmentSaveDto assignmentSaveDto);

    void saveGroup(AssignmentGroupSaveDto assignmentGroupSaveDto, Long groupId);

    AssignmentDto grade(Long id, Double grade);

    AssignmentDto comment(Long id, String comment);

    AssignmentDto setDeadline(Long id, LocalDate deadline);

    AssignmentDto complete(Long id, LocalDate closingDate);

    AssignmentDto incomplete(Long id);

    void delete(Long id);
}