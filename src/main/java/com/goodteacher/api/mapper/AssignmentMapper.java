package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Task;
import com.goodteacher.api.entity.Teacher;

public class AssignmentMapper {
    public static AssignmentDto fromEntityToDto(final Assignment entity) {
        final TaskDTO task = TaskMapper.fromEntityToDto(entity.getTask());
        final StudentDto student = StudentMapper.fromEntityToDto(entity.getStudent());
        final TeacherDto teacher = TeacherMapper.fromEntityToDto(entity.getTeacher());

        return AssignmentDto.builder()
                            .id(entity.getId())
                            .title(entity.getTitle())
                            .task(task)
                            .student(student)
                            .teacher(teacher)
                            .grade(entity.getGrade())
                            .comment(entity.getComment())
                            .deadline(entity.getDeadline())
                            .closingDate(entity.getClosingDate())
                            .isClosed(entity.getIsClosed())
                            .build();
    }

    public static Assignment fromSaveDtoToEntity(final AssignmentSaveDto saveDto) {
        final Task task = TaskMapper.fromDtoToEntity(saveDto.getTask());
        final Student student = StudentMapper.fromDtoToEntity(saveDto.getStudent());
        final Teacher teacher = TeacherMapper.fromDtoToEntity(saveDto.getTeacher());

        return Assignment.builder()
                         .title(saveDto.getTitle())
                         .task(task)
                         .student(student)
                         .teacher(teacher)
                         .grade(saveDto.getGrade())
                         .comment(saveDto.getComment())
                         .deadline(saveDto.getDeadline())
                         .closingDate(saveDto.getClosingDate())
                         .isClosed(saveDto.getIsClosed())
                         .build();
    }
}
