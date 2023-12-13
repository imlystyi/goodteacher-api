package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.StudentDto;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;

import java.util.stream.Collectors;

public class StudentMapper {
    public static StudentDto fromEntityToDto(final Student entity) {
        return StudentDto.builder()
                         .id(entity.getId())
                         .nickname(entity.getNickname())
                         .email(entity.getEmail())
                         .password(entity.getPassword())
                         .firstName(entity.getFirstName())
                         .lastName(entity.getLastName())
                         .patronymic(entity.getPatronymic())
                         .birthDate(entity.getBirthDate())
                         .assignments(entity.getAssignments()
                                            .stream()
                                            .map(AssignmentMapper::fromEntityToDto)
                                            .collect(Collectors.toSet()))
                         .groupIds(entity.getGroups()
                                         .stream()
                                         .map(Group::getId)
                                         .collect(Collectors.toSet()))
                         .build();
    }

    public static Student fromDtoToEntity(final StudentDto dto) {
        return Student.builder()
                      .id(dto.getId())
                      .nickname(dto.getNickname())
                      .email(dto.getEmail())
                      .password(dto.getPassword())
                      .firstName(dto.getFirstName())
                      .lastName(dto.getLastName())
                      .patronymic(dto.getPatronymic())
                      .birthDate(dto.getBirthDate())
                      .build();
    }
}
