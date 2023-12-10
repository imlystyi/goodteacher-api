package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.entity.Task;

public class AssignmentMapper {
    public static AssignmentDto fromEntityToDto(final Assignment entity) {
        final TaskDto task = TaskMapper.fromEntityToDto(entity.getTask());

        return AssignmentDto.builder()
                            .id(entity.getId())
                            .title(entity.getTitle())
                            .task(task)
                            .studentId(entity.getStudent().getId())
                            .teacherId(entity.getTeacher().getId())
                            .grade(entity.getGrade())
                            .comment(entity.getComment())
                            .deadline(entity.getDeadline())
                            .closingDate(entity.getClosingDate())
                            .isClosed(entity.getIsClosed())
                            .build();
    }

    public static Assignment fromDtoToEntity(final AssignmentDto dto) {
        final Task task = TaskMapper.fromDtoToEntity(dto.getTask());

        return Assignment.builder()
                         .id(dto.getId())
                         .title(dto.getTitle())
                         .task(task)
                         .grade(dto.getGrade())
                         .comment(dto.getComment())
                         .deadline(dto.getDeadline())
                         .closingDate(dto.getClosingDate())
                         .isClosed(dto.getIsClosed())
                         .build();
    }

    public static Assignment fromSaveDtoToEntity(final AssignmentSaveDto saveDto) {
        return Assignment.builder()
                         .title(saveDto.getTitle())
                         .deadline(saveDto.getDeadline())
                         .build();
    }

    public static Assignment fromSaveGroupDtoToEntity(final AssignmentGroupSaveDto groupSaveDto) {
        return Assignment.builder()
                         .title(groupSaveDto.getTitle())
                         .deadline(groupSaveDto.getDeadline())
                         .build();
    }
}
