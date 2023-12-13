package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.TeacherDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Teacher;

import java.util.stream.Collectors;

/**
 * Mapper for teacher models.
 */
public class TeacherMapper {
    /**
     * Converts entity {@link Teacher} to DTO {@link TeacherDto}.
     *
     * @param entity entity to convert as {@link Teacher}.
     * @return converted entity as {@link TeacherDto}.
     */
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

    /**
     * Partially converts DTO {@link UserDto} to entity {@link Teacher}.<br>
     * Note that it uses only {@code nickname}, {@code email}, {@code password}, {@code firstName}, {@code lastName}, {@code patronymic} and {@code birthDate} fields.</br>
     *
     * @param userDto DTO to convert as {@link UserDto}.
     * @return converted entity as {@link TeacherDto}.
     */
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
