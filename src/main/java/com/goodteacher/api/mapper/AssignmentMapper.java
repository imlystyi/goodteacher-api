package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.AssignmentDto;
import com.goodteacher.api.dto.StudentDto;
import com.goodteacher.api.dto.TaskDTO;
import com.goodteacher.api.dto.TeacherDto;
import com.goodteacher.api.entity.Assignment;

public class AssignmentMapper {
    public static AssignmentDto fromEntityToDto(final Assignment entity) {
        final TaskDTO task = TaskMapper.fromEntityToDto(entity.getTask());
        final StudentDto student = StudentMapper.fromEntityToDto(entity.getStudent());
        final TeacherDto teacher = TeacherMapper.fromEntityToDto(entity.getTeacher());

        return new AssignmentDto(entity.getId(), entity.getTitle(), task, student,
                                 teacher, entity.getGrade(), entity.getComment(), entity.getDeadline(),
                                 entity.getClosingDate());
    }
}
