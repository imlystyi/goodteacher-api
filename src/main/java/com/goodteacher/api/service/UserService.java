package com.goodteacher.api.service;

import com.goodteacher.api.dto.NameDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.dto.UserSaveDto;

import java.util.Set;

public interface UserService {
    UserDto findById(Long id);

    UserDto findByNickname(String nickname);

    Set<UserDto> findAllByName(NameDto nameDto);

    UserDto save(UserSaveDto userSaveDto);

    // TODO-1, Vladyslav: Provide signing in
    //UserDTO signIn(final UserDTO userDTO);

    void updateEmail(Long id, String email);

    void updatePassword(Long id, String password);

    UserDto updateName(final Long id, NameDto nameDto);

    void delete(Long id);
}
