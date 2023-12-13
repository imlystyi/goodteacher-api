package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.dto.GroupSaveDto;
import com.goodteacher.api.entity.Group;

import java.util.stream.Collectors;

public class GroupMapper {
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

    public static Group fromSaveDtoToEntity(final GroupSaveDto saveDto) {
        return Group.builder()
                    .name(saveDto.getName())
                    .about(saveDto.getAbout())
                    .build();
    }
}
