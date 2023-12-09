package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.GroupDto;
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

    public static Group fromDtoToEntity(final GroupDto dto) {
        return Group.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .about(dto.getAbout())
                    .teacher(TeacherMapper.fromDtoToEntity(dto.getTeacher()))
                    .students(dto.getStudents()
                                 .stream()
                                 .map(StudentMapper::fromDtoToEntity)
                                 .collect(Collectors.toSet()))
                    .build();

    }
}
