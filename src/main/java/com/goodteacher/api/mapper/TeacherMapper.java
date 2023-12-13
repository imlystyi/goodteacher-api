package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.TeacherDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.entity.Group;
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
                         .groupIds(entity.getGroups()
                                         .stream()
                                         .map(Group::getId)
                                         .collect(Collectors.toSet()))
                         .build();
    }

    public static Teacher fromUserDtoToEntity(final UserDto userDto) {
        return Teacher.builder()
                      .nickname(userDto.getNickname())
                      .email(userDto.getEmail())
                      .password(userDto.getPassword())
                      .firstName(userDto.getFirstName())
                      .lastName(userDto.getLastName())
                      .patronymic(userDto.getPatronymic())
                      .birthDate(userDto.getBirthDate())
                      .build();
    }
}
