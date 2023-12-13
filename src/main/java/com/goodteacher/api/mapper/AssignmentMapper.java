package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.AssignmentDto;
import com.goodteacher.api.dto.AssignmentGroupSaveDto;
import com.goodteacher.api.dto.AssignmentSaveDto;
import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.entity.Assignment;

/**
 * Mapper for assignment models.
 */
public class AssignmentMapper {
    /**
     * Converts entity {@link Assignment} to DTO {@link AssignmentDto}.
     *
     * @param entity entity to convert as {@link Assignment}.
     * @return converted entity as {@link AssignmentDto}.
     */
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

    /**
     * Partially converts DTO {@link AssignmentSaveDto} to entity {@link Assignment}.<br>
     * Note that it uses only {@code title} and {@code deadline} fields.</br>
     *
     * @param saveDto DTO to convert as {@link AssignmentSaveDto}.
     * @return converted entity as {@link AssignmentDto}.
     */
    public static Assignment fromSaveDtoToEntity(final AssignmentSaveDto saveDto) {
        return Assignment.builder()
                         .title(saveDto.getTitle())
                         .deadline(saveDto.getDeadline())
                         .build();
    }

    /**
     * Partially converts DTO {@link AssignmentGroupSaveDto} to entity {@link Assignment}.<br>
     * Note that it uses only {@code title} and {@code deadline} fields.</br>
     *
     * @param groupSaveDto DTO to convert as {@link AssignmentGroupSaveDto}.
     * @return converted entity as {@link AssignmentDto}.
     */
    public static Assignment fromSaveGroupDtoToEntity(final AssignmentGroupSaveDto groupSaveDto) {
        return Assignment.builder()
                         .title(groupSaveDto.getTitle())
                         .deadline(groupSaveDto.getDeadline())
                         .build();
    }
}
