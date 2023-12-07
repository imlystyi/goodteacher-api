package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.dto.UserSaveDto;
import com.goodteacher.api.entity.User;

public class UserMapper {
    public static UserDto fromEntityToDto(final User entity) {
        return UserDto.builder()
                      .id(entity.getId())
                      .nickname(entity.getNickname())
                      .firstName(entity.getFirstName())
                      .lastName(entity.getLastName())
                      .patronymic(entity.getPatronymic())
                      .birthDate(entity.getBirthDate())
                      .build();
    }

//    public static void fromEntityToSignUpDto(final User entity) {
//
//    }
//
//    public static void fromDtoToEntity(final UserDto dto) {
//
//    }

    public static User fromSignUpDtoToEntity(final UserSaveDto signUpDto) {
        return User.builder()
                   .nickname(signUpDto.getNickname())
                   .email(signUpDto.getEmail())
                   .password(signUpDto.getPassword())
                   .firstName(signUpDto.getFirstName())
                   .lastName(signUpDto.getLastName())
                   .patronymic(signUpDto.getPatronymic())
                   .birthDate(signUpDto.getBirthDate())
                   .build();
    }
}
