package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.dto.UserSignUpDto;
import com.goodteacher.api.entity.User;

public class UserMapper {
    public static UserDto fromEntityToDto(final User entity) {
        return new UserDto(entity.getId(), entity.getNickname(), entity.getFirstName(), entity.getLastName(),
                           entity.getPatronymic(), entity.getBirthDate());
    }

    public static void fromEntityToSignUpDto(final User entity) {

    }

    public static void fromDtoToEntity(final UserDto dto) {

    }

    public static User fromSignUpDtoToEntity(final UserSignUpDto signUpDto) {
        return new User(signUpDto.getNickname(), signUpDto.getEmail(), signUpDto.getPassword(),
                        signUpDto.getFirstName(), signUpDto.getLastName(), signUpDto.getPatronymic(),
                        signUpDto.getBirthDate());
    }
}
