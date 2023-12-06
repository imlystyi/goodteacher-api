package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.dto.UserSignUpDto;
import com.goodteacher.api.entity.User;
import com.goodteacher.api.mapper.UserMapper;
import com.goodteacher.api.repository.UserRepository;
import com.goodteacher.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public UserDto findById(final UUID id) {
        final User entity = this.findByIdStream(id);

        return UserMapper.fromEntityToDto(entity);
    }

    @Override
    public UserDto findByNickname(final String nickname) {
        final User entity = this.findByNicknameStream(nickname);

        return UserMapper.fromEntityToDto(entity);
    }

    @Override
    public UserDto findByName(final String firstName, final String lastName, final String patronymic) {
        final User entity = this.findByNameStream(firstName, lastName, patronymic);

        return UserMapper.fromEntityToDto(entity);
    }

    @Override
    public UserDto signUp(final UserSignUpDto userSignUpDTO) {
        final User entity = this.repository.save(UserMapper.fromSignUpDtoToEntity(userSignUpDTO));

        return UserMapper.fromEntityToDto(entity);
    }

    @Override
    public UserDto updateInfo(final UserDto dto) {
        final User entity = this.findByIdStream(dto.getId());

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPatronymic(dto.getPatronymic());

        this.repository.save(entity);

        return UserMapper.fromEntityToDto(entity);
    }

    @Override
    public void updateEmail(final UUID id, final String email) {
        final User entity = this.findByIdStream(id);

        entity.setEmail(email);

        this.repository.save(entity);
    }

    @Override
    public void updatePassword(final UUID id, final String password) {
        final User entity = this.findByIdStream(id);

        entity.setPassword(password);

        this.repository.save(entity);
    }

    @Override
    public void deleteById(final UUID id) {
        final User entity = this.findByIdStream(id);

        entity.setIsActive(false);

        this.repository.save(entity);
    }

    // TODO-1, Vladyslav: Provide custom exceptions
    private User findByIdStream(final UUID id) {
        return this.repository.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException(
                                      "User with id {%s} not found".formatted(id.toString())));
    }

    private User findByNicknameStream(final String nickname) {
        return this.repository.findByNickname(nickname)
                              .orElseThrow(() -> new IllegalArgumentException(
                                      "User with nickname {%s} not found".formatted(nickname)));
    }

    private User findByNameStream(final String firstName, final String lastName, final String patronymic) {
        return this.repository.findByFirstNameAndLastNameAndPatronymic(firstName, lastName, patronymic)
                              .orElseThrow(() -> new IllegalArgumentException(
                                      "User with name {%s} {%s} {%s} not found".formatted(firstName, lastName,
                                                                                          patronymic)));
    }
}
