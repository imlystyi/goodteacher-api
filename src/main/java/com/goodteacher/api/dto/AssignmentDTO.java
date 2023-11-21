package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.entity.Task;
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
    private Task task;
    private Student student;
    private Teacher teacher;
    private Double grade;
    private LocalDate deadline;

    // endregion

    // region Methods

    public static AssignmentDTO toDTO(final Assignment assignment){
        final AssignmentDTO assignmentDTO = new AssignmentDTO();

        assignmentDTO.setId(assignment.getId());
        assignmentDTO.setTask(assignment.getTask());
        assignmentDTO.setStudent(assignment.getStudent());
        assignmentDTO.setTeacher(assignment.getTeacher());
        assignmentDTO.setGrade(assignment.getGrade());
        assignmentDTO.setDeadline(assignment.getDeadline());

        return assignmentDTO;
    }

    // endregion
}