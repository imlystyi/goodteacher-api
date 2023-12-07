package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.NameDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.dto.UserSaveDto;
import com.goodteacher.api.entity.User;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.exception.UserAlreadyExistsException;
import com.goodteacher.api.mapper.UserMapper;
import com.goodteacher.api.repository.UserRepository;
import com.goodteacher.api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto findById(final Long id) {
        final User userEntity = this.findByIdStream(id);

        return UserMapper.fromEntityToDto(userEntity);
    }

    @Override
    public UserDto findByNickname(final String nickname) {
        final User userEntity = this.findByNicknameStream(nickname, true);

        return UserMapper.fromEntityToDto(userEntity);
    }

    @Override
    public Set<UserDto> findAllByName(final NameDto nameDto) {
        return this.findAllByNameStream(nameDto.getFirstName(), nameDto.getLastName(), nameDto.getPatronymic())
                   .stream()
                   .map(UserMapper::fromEntityToDto)
                   .collect(Collectors.toSet());
    }

    @Override
    public UserDto save(final UserSaveDto userSaveDto) {
        if (this.findByNicknameStream(userSaveDto.getNickname(), false) != null) {
            throw new UserAlreadyExistsException("User with nickname \"%s\" already exists".formatted(
                    userSaveDto.getNickname()));
        } else if (this.findByEmailStream(userSaveDto.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with email \"%s\" already exists".formatted(
                    userSaveDto.getEmail()));
        }

        final User userEntity = this.userRepository.save(UserMapper.fromSignUpDtoToEntity(userSaveDto));

        return UserMapper.fromEntityToDto(userEntity);
    }

    @Override
    public void updateEmail(final Long id, final String email) {
        final User userEntity = this.findByIdStream(id);

        userEntity.setEmail(email);

        this.userRepository.save(userEntity);
    }

    @Override
    public void updatePassword(final Long id, final String password) {
        final User userEntity = this.findByIdStream(id);

        userEntity.setPassword(password);

        this.userRepository.save(userEntity);
    }

    @Override
    public UserDto updateName(final Long id, final NameDto nameDto) {
        final User userEntity = this.findByIdStream(id);

        userEntity.setFirstName(nameDto.getFirstName());
        userEntity.setLastName(nameDto.getLastName());
        userEntity.setPatronymic(nameDto.getPatronymic());

        this.userRepository.save(userEntity);

        return UserMapper.fromEntityToDto(userEntity);
    }

    @Override
    public void delete(final Long id) {
        final User userEntity = this.findByIdStream(id);

        userEntity.setIsActive(Boolean.FALSE);

        this.userRepository.save(userEntity);
    }

    private User findByIdStream(final Long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id %d not found".formatted(id)));
    }

    private User findByEmailStream(final String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    private User findByNicknameStream(final String nickname, final boolean throwExceptionIfNotFound) {

        return throwExceptionIfNotFound
               ? this.userRepository.findByNickname(nickname).orElseThrow(() -> new NotFoundException(
                "User with nickname \"%s\" not found".formatted(nickname)))
               : this.userRepository.findByNickname(nickname).orElse(null);
    }

    private Set<User> findAllByNameStream(final String firstName, final String lastName, final String patronymic) {
        final Set<User> userEntities = this.userRepository.findAllByFirstNameAndLastNameAndPatronymic(firstName,
                                                                                                      lastName,
                                                                                                      patronymic);

        if (userEntities.isEmpty()) {
            throw new NotFoundException("Users with name \"%s %s %s\" not found".formatted(firstName,
                                                                                                 lastName,
                                                                                                 patronymic));
        }

        return userEntities;
    }
}
