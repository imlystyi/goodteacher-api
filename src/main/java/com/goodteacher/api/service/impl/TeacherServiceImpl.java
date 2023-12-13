package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.NameDto;
import com.goodteacher.api.dto.TeacherDto;
import com.goodteacher.api.dto.TeacherInfoDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.exception.ConflictException;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.TeacherMapper;
import com.goodteacher.api.repository.TeacherRepository;
import com.goodteacher.api.service.TeacherService;
import com.goodteacher.api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    private final UserService userService;

    @Override
    public TeacherDto findById(final Long id) {
        final Teacher teacherEntity = findByIdStream(id);

        return TeacherMapper.fromEntityToDto(teacherEntity);
    }

    @Override
    public Teacher findEntityById(final Long id) {
        return findByIdStream(id);
    }

    @Override
    public TeacherDto findByNickname(final String nickname) {
        final Teacher teacherEntity = findByNicknameStream(nickname);

        return TeacherMapper.fromEntityToDto(teacherEntity);
    }

    @Override
    public Set<TeacherDto> findAllByName(final NameDto nameDto) {
        return findAllByNameStream(nameDto.getFirstName(), nameDto.getLastName(), nameDto.getPatronymic())
                   .stream()
                   .map(TeacherMapper::fromEntityToDto)
                   .collect(Collectors.toSet());
    }

    @Override
    public TeacherDto save(final UserDto userDto) {
        if (userService.anyByNickname(userDto.getNickname())) {
            throw new ConflictException("User with nickname %s already exists".formatted(userDto.getNickname()));
        } else if (userService.anyByEmail(userDto.getEmail())) {
            throw new ConflictException("User with email %s already exists".formatted(userDto.getEmail()));
        }

        final TeacherDto teacherDto = TeacherDto.builder()
                                                .nickname(userDto.getNickname())
                                                .email(userDto.getEmail())
                                                .password(userDto.getPassword())
                                                .firstName(userDto.getFirstName())
                                                .lastName(userDto.getLastName())
                                                .patronymic(userDto.getPatronymic())
                                                .birthDate(userDto.getBirthDate())
                                                .build();

        final Teacher teacherEntity = teacherRepository.save(TeacherMapper.fromDtoToEntity(teacherDto));

        teacherDto.setId(teacherEntity.getId());

        return teacherDto;
    }

    @Override
    public void updateEmail(final Long id, final String email) {
        if (userService.anyByEmail(email)) {
            throw new ConflictException("User with email %s already exists".formatted(email));
        }

        final Teacher teacherEntity = findByIdStream(id);

        teacherEntity.setEmail(email);

        teacherRepository.save(teacherEntity);
    }

    @Override
    public void updatePassword(final Long id, final String password) {
        final Teacher teacherEntity = findByIdStream(id);

        teacherEntity.setPassword(password);

        teacherRepository.save(teacherEntity);
    }

    @Override
    public TeacherDto updateName(final Long id, final NameDto nameDto) {
        final Teacher teacherEntity = findByIdStream(id);

        teacherEntity.setFirstName(nameDto.getFirstName());
        teacherEntity.setLastName(nameDto.getLastName());
        teacherEntity.setPatronymic(nameDto.getPatronymic());

        return TeacherMapper.fromEntityToDto(teacherRepository.save(teacherEntity));
    }

    @Override
    public TeacherDto updateBirthDate(final Long id, final LocalDate birthDate) {
        final Teacher teacherEntity = findByIdStream(id);

        teacherEntity.setBirthDate(birthDate);

        return TeacherMapper.fromEntityToDto(teacherRepository.save(teacherEntity));
    }

    @Override
    public TeacherDto updateInfo(final TeacherInfoDto teacherInfoDto) {
        final Teacher teacherEntity = findByIdStream(teacherInfoDto.getId());

        teacherEntity.setAbout(teacherInfoDto.getAbout());
        teacherEntity.setStatus(teacherInfoDto.getStatus());

        return TeacherMapper.fromEntityToDto(teacherRepository.save(teacherEntity));
    }

    @Override
    public void addGroup(final Long teacherId, final Group groupEntity) {
        final Teacher teacherEntity = findByIdStream(teacherId);

        teacherEntity.getGroups().add(groupEntity);

        teacherRepository.save(teacherEntity);
    }

    @Override
    public void removeGroup(final Long id, final Group groupEntity) {
        final Teacher teacherEntity = findByIdStream(id);

        teacherEntity.getGroups().remove(groupEntity);

        teacherRepository.save(teacherEntity);
    }

    @Override
    public void delete(final Long id) {
        final Teacher teacherEntity = findByIdStream(id);

        teacherEntity.setIsActive(Boolean.FALSE);

        teacherRepository.save(teacherEntity);
    }

    private Teacher findByIdStream(final Long id) {
        return teacherRepository.findByIdAndIsActiveTrue(id)
                                     .orElseThrow(() -> new NotFoundException(
                                             "Teacher with id %d not found".formatted(id)));
    }

    private Teacher findByNicknameStream(final String nickname) {
        return teacherRepository.findByNicknameAndIsActiveTrue(nickname)
                                     .orElseThrow(() -> new NotFoundException(
                                             "Teacher with nickname %s not found".formatted(nickname)));
    }

    private Set<Teacher> findAllByNameStream(final String firstName, final String lastName, final String patronymic) {
        final Set<Teacher> teacherEntities =
                teacherRepository.findAllByFirstNameAndLastNameAndPatronymicAndIsActiveTrue(firstName, lastName,
                                                                                                 patronymic);

        if (teacherEntities.isEmpty()) {
            throw new NotFoundException(
                    "Teachers with name %s %s %s not found".formatted(firstName, lastName, patronymic));
        }

        return teacherEntities;
    }
}
