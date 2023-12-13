package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.StudentDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;

import java.util.stream.Collectors;

/**
 * Mapper for student models.
 */
public class StudentMapper {
    /**
     * Converts entity {@link Student} to DTO {@link StudentDto}.
     *
     * @param entity entity to convert as {@link Student}.
     * @return converted entity as {@link StudentDto}.
     */
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

    /**
     * Partially converts DTO {@link UserDto} to entity {@link Student}.<br>
     * Note that it uses only {@code nickname}, {@code email}, {@code password}, {@code firstName}, {@code lastName}, {@code patronymic} and {@code birthDate} fields.</br>
     *
     * @param userDto DTO to convert as {@link UserDto}.
     * @return converted entity as {@link StudentDto}.
     */
    public static Student fromUserDtoToEntity(final UserDto userDto) {
        return Student.builder()
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
