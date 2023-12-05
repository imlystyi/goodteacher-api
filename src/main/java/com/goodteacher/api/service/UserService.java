package com.goodteacher.api.service;

import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.dto.UserSignUpDto;

import java.util.UUID;

public interface UserService {
    UserDto findByNickname(final String nickname);
    UserDto findByName(final String firstName, final String lastName, final String patronymic);

    UserDto signUp(final UserSignUpDto userSignUpDTO);

    // TODO-1, Vladyslav: Provide signing in
    //UserDTO signIn(final UserDTO userDTO);

    UserDto updateInfo(final UserDto userDTO);
    void updateEmail(final UUID id, final String email);
    void updatePassword(final UUID id, final String password);

    void deleteById(final UUID id);
}
