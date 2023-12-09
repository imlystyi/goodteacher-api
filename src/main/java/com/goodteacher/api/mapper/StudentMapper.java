package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.StudentDto;
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
                         .groups(entity.getGroups()
                                       .stream()
                                       .map(GroupMapper::fromEntityToDto)
                                       .collect(Collectors.toSet()))
                         .build();
    }

    public static Student fromDtoToEntity(final StudentDto dto) {
        return Student.builder()
                      .nickname(dto.getNickname())
                      .email(dto.getEmail())
                      .password(dto.getPassword())
                      .firstName(dto.getFirstName())
                      .lastName(dto.getLastName())
                      .patronymic(dto.getPatronymic())
                      .birthDate(dto.getBirthDate())
                      .assignments(dto.getAssignments()
                                      .stream()
                                      .map(AssignmentMapper::fromDtoToEntity)
                                      .collect(Collectors.toSet()))
                      .groups(dto.getGroups()
                                 .stream()
                                 .map(GroupMapper::fromDtoToEntity)
                                 .collect(Collectors.toSet()))
                      .build();
    }
}
