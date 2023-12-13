package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.*;
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

/**
 * Main implementer of {@link TeacherService} interface.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    // region Fields

    private final TeacherRepository teacherRepository;

    private final UserService userService;

    // endregion

    // region Methods

    /**
     * Finds a teacher by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found teacher as {@link TeacherDto} if the teacher with the specified ID exists.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    @Override
    public TeacherDto findById(final Long id) {
        final Teacher teacherEntity = findByIdInRepository(id);

        return TeacherMapper.fromEntityToDto(teacherEntity);
    }

    /**
     * Finds a teacher by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found teacher as {@link Teacher} if the teacher with the specified ID exists.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    @Override
    public Teacher findEntityById(final Long id) {
        return findByIdInRepository(id);
    }

    /**
     * Finds a teacher by nickname.
     *
     * @param nickname nickname to search by as {@link String}.
     * @return found teacher as {@link Teacher} if the teacher with the specified nickname exists.
     * @throws NotFoundException if the teacher with the specified nickname does not exist.
     */
    @Override
    public TeacherDto findByNickname(final String nickname) {
        final Teacher teacherEntity = findByNicknameInRepository(nickname);

        return TeacherMapper.fromEntityToDto(teacherEntity);
    }

    /**
     * Finds teachers by name.
     *
     * @param nameDto name to search by as {@link NameDto}.
     * @return found teachers as {@link Set}{@code <}{@link TeacherDto}{@code >} if any teacher with the specified name exists.
     * @throws NotFoundException if the teacher with the specified name does not exist.
     */
    @Override
    public Set<TeacherDto> findAllByName(final NameDto nameDto) {
        return findAllByNameInRepository(nameDto.getFirstName(), nameDto.getLastName(), nameDto.getPatronymic())
                .stream()
                .map(TeacherMapper::fromEntityToDto)
                .collect(Collectors.toSet());
    }

    /**
     * Signing teacher in.
     *
     * @param identityDto identity to signing in by as {@link IdentityDto}.
     * @return {@code true} if the signing in was successful; otherwise, {@code false}.
     */
    @Override
    public boolean signIn(final IdentityDto identityDto) {
        return teacherRepository.existsByNicknameAndPasswordAndIsActiveTrue(identityDto.getNickname(),
                                                                            identityDto.getPassword());
    }

    /**
     * Saves the teacher in the repository.
     *
     * @param userDto teacher to save as {@link UserDto}.
     * @return saved teacher as {@link TeacherDto} if saved successfully.
     * @throws ConflictException if the student with the specified email or nickname already exists.
     */
    @Override
    public TeacherDto save(final UserDto userDto) {
        if (userService.anyByNickname(userDto.getNickname())) {
            throw new ConflictException("User with nickname %s already exists".formatted(userDto.getNickname()));
        } else if (userService.anyByEmail(userDto.getEmail())) {
            throw new ConflictException("User with email %s already exists".formatted(userDto.getEmail()));
        }

        final Teacher teacherEntity = TeacherMapper.fromUserDtoToEntity(userDto);
        final Teacher savedTeacherEntity = teacherRepository.save(teacherEntity);

        return TeacherMapper.fromEntityToDto(savedTeacherEntity);
    }

    /**
     * Updates the email of the specified teacher.
     *
     * @param id    teacher ID as {@link Long}.
     * @param email new email as {@link String}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     * @throws ConflictException if the student with the specified email or nickname already exists.
     */
    @Override
    public void updateEmail(final Long id, final String email) {
        if (userService.anyByEmail(email)) {
            throw new ConflictException("User with email %s already exists".formatted(email));
        }

        final Teacher teacherEntity = findByIdInRepository(id);

        teacherEntity.setEmail(email);

        teacherRepository.save(teacherEntity);
    }

    /**
     * Updates the password of the specified teacher.
     *
     * @param id       teacher ID as {@link Long}.
     * @param password new password as {@link String}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    @Override
    public void updatePassword(final Long id, final String password) {
        final Teacher teacherEntity = findByIdInRepository(id);

        teacherEntity.setPassword(password);

        teacherRepository.save(teacherEntity);
    }

    /**
     * Updates the name of the specified teacher.
     *
     * @param id      teacher ID as {@link Long}.
     * @param nameDto new name as {@link NameDto}.
     * @return updated teacher as {@link TeacherDto} if updated successfully.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    @Override
    public TeacherDto updateName(final Long id, final NameDto nameDto) {
        final Teacher teacherEntity = findByIdInRepository(id);

        teacherEntity.setFirstName(nameDto.getFirstName());
        teacherEntity.setLastName(nameDto.getLastName());
        teacherEntity.setPatronymic(nameDto.getPatronymic());

        return TeacherMapper.fromEntityToDto(teacherRepository.save(teacherEntity));
    }

    /**
     * Updates the birthdate of the specified teacher.
     *
     * @param id        teacher ID as {@link Long}.
     * @param birthDate new birthdate as {@link LocalDate}.
     * @return updated teacher as {@link TeacherDto} if updated successfully.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    @Override
    public TeacherDto updateBirthDate(final Long id, final LocalDate birthDate) {
        final Teacher teacherEntity = findByIdInRepository(id);

        teacherEntity.setBirthDate(birthDate);

        return TeacherMapper.fromEntityToDto(teacherRepository.save(teacherEntity));
    }

    /**
     * Updates the main info of the specified teacher ({@code about} and {@code status}).
     *
     * @param teacherInfoDto new teacher info as {@link TeacherInfoDto}.
     * @return updated teacher as {@link TeacherDto} if updated successfully.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    @Override
    public TeacherDto updateInfo(final TeacherInfoDto teacherInfoDto) {
        final Teacher teacherEntity = findByIdInRepository(teacherInfoDto.getId());

        teacherEntity.setAbout(teacherInfoDto.getAbout());
        teacherEntity.setStatus(teacherInfoDto.getStatus());

        return TeacherMapper.fromEntityToDto(teacherRepository.save(teacherEntity));
    }

    /**
     * Adds the group to the group list of the specified teacher.
     *
     * @param id          teacher ID as {@link Long}.
     * @param groupEntity group to add as {@link Group}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    @Override
    public void addGroup(final Long id, final Group groupEntity) {
        final Teacher teacherEntity = findByIdInRepository(id);

        teacherEntity.getGroups().add(groupEntity);

        teacherRepository.save(teacherEntity);
    }

    /**
     * Removes the group from the group list of the specified teacher.
     *
     * @param teacherId teacher ID as {@link Long}.
     * @param groupId   group to remove ID as {@link Long}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    @Override
    public void removeGroup(final Long teacherId, final Long groupId) {
        final Teacher teacherEntity = findByIdInRepository(teacherId);

        teacherEntity.getGroups().removeIf(g -> g.getId().equals(groupId));

        teacherRepository.save(teacherEntity);
    }

    /**
     * Sets the specific teacher inactive.
     *
     * @param id teacher ID as {@link Long}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    @Override
    public void delete(final Long id) {
        final Teacher teacherEntity = findByIdInRepository(id);

        teacherEntity.setIsActive(Boolean.FALSE);

        teacherRepository.save(teacherEntity);
    }

    private Teacher findByIdInRepository(final Long id) {
        return teacherRepository.findByIdAndIsActiveTrue(id)
                                .orElseThrow(() -> new NotFoundException(
                                        "Teacher with id %d not found".formatted(id)));
    }

    private Teacher findByNicknameInRepository(final String nickname) {
        return teacherRepository.findByNicknameAndIsActiveTrue(nickname)
                                .orElseThrow(() -> new NotFoundException(
                                        "Teacher with nickname %s not found".formatted(nickname)));
    }

    private Set<Teacher> findAllByNameInRepository(final String firstName, final String lastName,
                                                   final String patronymic) {
        final Set<Teacher> teacherEntities =
                teacherRepository.findAllByFirstNameAndLastNameAndPatronymicAndIsActiveTrue(firstName, lastName,
                                                                                            patronymic);

        if (teacherEntities.isEmpty()) {
            throw new NotFoundException(
                    "Teachers with name %s %s %s not found".formatted(firstName, lastName, patronymic));
        }

        return teacherEntities;
    }

    // endregion
}
