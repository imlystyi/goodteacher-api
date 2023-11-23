package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Assignment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDTO {
    // region Fields

    private UUID id;
    private UUID taskId;
    private UUID studentId;
    private UUID teacherId;
    private Double grade;
    private LocalDate deadline;

    // endregion

    // region Methods

    public static AssignmentDTO toDTO(final Assignment assignment) {
        final AssignmentDTO assignmentDTO = new AssignmentDTO();

        assignmentDTO.setId(assignment.getId());
        assignmentDTO.setTaskId(assignment.getTask().getId());
        assignmentDTO.setStudentId(assignment.getStudent().getId());
        assignmentDTO.setTeacherId(assignment.getTeacher().getId());
        assignmentDTO.setGrade(assignment.getGrade());
        assignmentDTO.setDeadline(assignment.getDeadline());

        return assignmentDTO;
    }

    // endregion
}