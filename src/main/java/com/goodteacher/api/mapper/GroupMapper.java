package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.dto.GroupSaveDto;
import com.goodteacher.api.entity.Group;

import java.util.stream.Collectors;

/**
 * Mapper for group models.
 */
public class GroupMapper {
    /**
     * Converts entity {@link Group} to DTO {@link GroupDto}.
     *
     * @param entity entity to convert as {@link Group}.
     * @return converted entity as {@link GroupDto}.
     */
    public static GroupDto fromEntityToDto(final Group entity) {
        return GroupDto.builder()
                       .id(entity.getId())
                       .name(entity.getName())
                       .about(entity.getAbout())
                       .teacher(TeacherMapper.fromEntityToDto(entity.getTeacher()))
                       .students(entity.getStudents()
                                       .stream()
                                       .map(StudentMapper::fromEntityToDto)
                                       .collect(Collectors.toSet()))
                       .build();
    }

    /**
     * Partially converts DTO {@link GroupSaveDto} to entity {@link Group}.<br>
     * Note that it uses only {@code name} and {@code about} fields.</br>
     *
     * @param saveDto DTO to convert as {@link GroupSaveDto}.
     * @return converted entity as {@link GroupDto}.
     */
    public static Group fromSaveDtoToEntity(final GroupSaveDto saveDto) {
        return Group.builder()
                    .name(saveDto.getName())
                    .about(saveDto.getAbout())
                    .build();
    }
}
