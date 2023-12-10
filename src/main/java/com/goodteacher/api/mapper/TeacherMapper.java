package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.TeacherDto;
import com.goodteacher.api.entity.Teacher;

import java.util.stream.Collectors;

public class TeacherMapper {
    public static TeacherDto fromEntityToDto(final Teacher entity) {
        return TeacherDto.builder()
                         .id(entity.getId())
                         .nickname(entity.getNickname())
                         .email(entity.getEmail())
                         .password(entity.getPassword())
                         .firstName(entity.getFirstName())
                         .lastName(entity.getLastName())
                         .patronymic(entity.getPatronymic())
                         .birthDate(entity.getBirthDate())
                         .about(entity.getAbout())
                         .status(entity.getStatus())
                         .groups(entity.getGroups()
                                       .stream()
                                       .map(GroupMapper::fromEntityToDto)
                                       .collect(Collectors.toSet()))
                         .build();
    }

    public static Teacher fromDtoToEntity(final TeacherDto dto) {
        return Teacher.builder()
                      .id(dto.getId())
                      .nickname(dto.getNickname())
                      .email(dto.getEmail())
                      .password(dto.getPassword())
                      .firstName(dto.getFirstName())
                      .lastName(dto.getLastName())
                      .patronymic(dto.getPatronymic())
                      .birthDate(dto.getBirthDate())
                      .about(dto.getAbout())
                      .status(dto.getStatus())
                      .groups(dto.getGroups()
                                 .stream()
                                 .map(GroupMapper::fromDtoToEntity)
                                 .collect(Collectors.toList()))
                      .build();
    }
}
