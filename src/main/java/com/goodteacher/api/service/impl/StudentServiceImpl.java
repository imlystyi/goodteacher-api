package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.IdentityDto;
import com.goodteacher.api.dto.NameDto;
import com.goodteacher.api.dto.StudentDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.exception.ConflictException;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.StudentMapper;
import com.goodteacher.api.repository.StudentRepository;
import com.goodteacher.api.service.StudentService;
import com.goodteacher.api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main implementer of {@link StudentService} interface.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    // region Fields

    private final StudentRepository studentRepository;

    private final UserService userService;

    // endregion

    // region Methods

    /**
     * Finds a student by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found student as {@link StudentDto} if the student with the specified ID exists.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    @Override
    public StudentDto findById(final Long id) {
        final Student studentEntity = findByIdInRepository(id);

        return StudentMapper.fromEntityToDto(studentEntity);
    }

    /**
     * Finds a student by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found student as {@link Student} if the student with the specified ID exists.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    @Override
    public Student findEntityById(final Long id) {
        return findByIdInRepository(id);
    }

    /**
     * Finds a student by nickname.
     *
     * @param nickname nickname to search by as {@link String}.
     * @return found student as {@link Student} if the student with the specified nickname exists.
     * @throws NotFoundException if the student with the specified nickname does not exist.
     */
    @Override
    public StudentDto findByNickname(final String nickname) {
        final Student studentEntity = findByNicknameInRepository(nickname);

        return StudentMapper.fromEntityToDto(studentEntity);
    }

    /**
     * Finds students by name.
     *
     * @param nameDto name to search by as {@link NameDto}.
     * @return found students as {@link Set}{@code <}{@link StudentDto}{@code >} if any student with the specified name exists.
     * @throws NotFoundException if the student with the specified name does not exist.
     */
    @Override
    public Set<StudentDto> findAllByName(final NameDto nameDto) {
        return findAllByNameInRepository(nameDto.getFirstName(), nameDto.getLastName(), nameDto.getPatronymic())
                .stream()
                .map(StudentMapper::fromEntityToDto)
                .collect(Collectors.toSet());
    }

    /**
     * Signing student in.
     *
     * @param identityDto identity to signing in by as {@link IdentityDto}.
     * @return {@code true} if the signing in was successful; otherwise, {@code false}.
     */
    @Override
    public boolean signIn(final IdentityDto identityDto) {
        return studentRepository.existsByNicknameAndPasswordAndIsActiveTrue(identityDto.getNickname(),
                                                                            identityDto.getPassword());
    }

    /**
     * Saves the student in the repository.
     *
     * @param userDto student to save as {@link UserDto}.
     * @return saved student as {@link StudentDto} if saved successfully.
     * @throws ConflictException if the student with the specified email or nickname already exists.
     */
    @Override
    public StudentDto save(final UserDto userDto) {
        if (userService.anyByNickname(userDto.getNickname())) {
            throw new ConflictException("User with nickname %s already exists".formatted(userDto.getNickname()));
        } else if (userService.anyByEmail(userDto.getEmail())) {
            throw new ConflictException("User with email %s already exists".formatted(userDto.getEmail()));
        }

        final Student studentEntity = StudentMapper.fromUserDtoToEntity(userDto);
        final Student savedStudentEntity = studentRepository.save(studentEntity);

        return StudentMapper.fromEntityToDto(savedStudentEntity);
    }

    /**
     * Updates the email of the specified student.
     *
     * @param id    student ID as {@link Long}.
     * @param email new email as {@link String}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     * @throws ConflictException if the student with the specified email already exists.
     */
    @Override
    public void updateEmail(final Long id, final String email) {
        if (userService.anyByEmail(email)) {
            throw new ConflictException("User with email %s already exists".formatted(email));
        }

        final Student studentEntity = findByIdInRepository(id);

        studentEntity.setEmail(email);

        studentRepository.save(studentEntity);
    }

    /**
     * Updates the password of the specified student.
     *
     * @param id       student ID as {@link Long}.
     * @param password new password as {@link String}.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    @Override
    public void updatePassword(final Long id, final String password) {
        final Student studentEntity = findByIdInRepository(id);

        studentEntity.setPassword(password);

        studentRepository.save(studentEntity);
    }

    /**
     * Updates the name of the specified student.
     *
     * @param id      student ID as {@link Long}.
     * @param nameDto new name as {@link NameDto}.
     * @return updated student as {@link StudentDto} if updated successfully.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    @Override
    public StudentDto updateName(final Long id, final NameDto nameDto) {
        final Student studentEntity = findByIdInRepository(id);

        studentEntity.setFirstName(nameDto.getFirstName());
        studentEntity.setLastName(nameDto.getLastName());
        studentEntity.setPatronymic(nameDto.getPatronymic());

        return StudentMapper.fromEntityToDto(studentRepository.save(studentEntity));
    }

    /**
     * Updates the birthdate of the specified student.
     *
     * @param id        student ID as {@link Long}.
     * @param birthDate new birthdate as {@link LocalDate}.
     * @return updated student as {@link StudentDto} if updated successfully.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    @Override
    public StudentDto updateBirthDate(final Long id, final LocalDate birthDate) {
        final Student studentEntity = findByIdInRepository(id);

        studentEntity.setBirthDate(birthDate);

        return StudentMapper.fromEntityToDto(studentRepository.save(studentEntity));
    }

    /**
     * Adds an assignment to the assignment list of the specified student.
     *
     * @param id               student ID as {@link Long}.
     * @param assignmentEntity assignment to add as {@link Assignment}.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    @Override
    public void addAssignment(final Long id, final Assignment assignmentEntity) {
        final Student studentEntity = findByIdInRepository(id);

        studentEntity.getAssignments().add(assignmentEntity);

        studentRepository.save(studentEntity);
    }

    /**
     * Adds a group to the group list of the specified student.
     *
     * @param id          student ID as {@link Long}.
     * @param groupEntity group to add as {@link Group}.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    @Override
    public void addGroup(final Long id, final Group groupEntity) {
        final Student studentEntity = findByIdInRepository(id);

        studentEntity.getGroups().add(groupEntity);

        studentRepository.save(studentEntity);
    }

    /**
     * Removes a group from the group list of the specified student.
     *
     * @param studentId student ID as {@link Long}.
     * @param groupId   ID of the group to remove as {@link Long}.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    @Override
    public void removeGroup(final Long studentId, final Long groupId) {
        final Student studentEntity = findByIdInRepository(studentId);

        studentEntity.getGroups().removeIf(g -> g.getId().equals(groupId));

        studentRepository.save(studentEntity);
    }

    /**
     * Sets the specific student inactive.
     *
     * @param id student ID as {@link Long}.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    @Override
    public void delete(final Long id) {
        final Student studentEntity = findByIdInRepository(id);

        studentEntity.setIsActive(Boolean.FALSE);

        studentRepository.save(studentEntity);
    }

    private Student findByIdInRepository(final Long id) {
        return studentRepository.findByIdAndIsActiveTrue(id)
                                .orElseThrow(() -> new NotFoundException(
                                        "Student with id %d not found".formatted(id)));
    }

    private Student findByNicknameInRepository(final String nickname) {
        return studentRepository.findByNicknameAndIsActiveTrue(nickname)
                                .orElseThrow(() -> new NotFoundException(
                                        "Student with nickname %s not found".formatted(nickname)));
    }

    private Set<Student> findAllByNameInRepository(final String firstName, final String lastName,
                                                   final String patronymic) {
        final Set<Student> studentEntities =
                studentRepository.findAllByFirstNameAndLastNameAndPatronymicAndIsActiveTrue(firstName, lastName,
                                                                                            patronymic);

        if (studentEntities.isEmpty()) {
            throw new NotFoundException(
                    "Students with name %s %s %s not found".formatted(firstName, lastName, patronymic));
        }

        return studentEntities;
    }

    // endregion
}
