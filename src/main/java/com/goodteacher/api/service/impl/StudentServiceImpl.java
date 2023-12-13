package com.goodteacher.api.service.impl;

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

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    private final UserService userService;

    @Override
    public StudentDto findById(final Long id) {
        final Student studentEntity = findByIdStream(id);

        return StudentMapper.fromEntityToDto(studentEntity);
    }

    @Override
    public Student findEntityById(final Long id) {
        return findByIdStream(id);
    }

    @Override
    public StudentDto findByNickname(final String nickname) {
        final Student studentEntity = findByNicknameStream(nickname);

        return StudentMapper.fromEntityToDto(studentEntity);
    }

    @Override
    public Set<StudentDto> findAllByName(final NameDto nameDto) {
        return findAllByNameStream(nameDto.getFirstName(), nameDto.getLastName(), nameDto.getPatronymic())
                   .stream()
                   .map(StudentMapper::fromEntityToDto)
                   .collect(Collectors.toSet());
    }


    @Override
    public StudentDto save(final UserDto userDto) {
        if (userService.anyByNickname(userDto.getNickname())) {
            throw new ConflictException("User with nickname %s already exists".formatted(userDto.getNickname()));
        } else if (userService.anyByEmail(userDto.getEmail())) {
            throw new ConflictException("User with email %s already exists".formatted(userDto.getEmail()));
        }

        final StudentDto studentDto = StudentDto.builder()
                                                .nickname(userDto.getNickname())
                                                .email(userDto.getEmail())
                                                .password(userDto.getPassword())
                                                .firstName(userDto.getFirstName())
                                                .lastName(userDto.getLastName())
                                                .patronymic(userDto.getPatronymic())
                                                .birthDate(userDto.getBirthDate())
                                                .build();

        final Student studentEntity = studentRepository.save(StudentMapper.fromDtoToEntity(studentDto));

        studentDto.setId(studentEntity.getId());

        return studentDto;
    }

    @Override
    public void updateEmail(final Long id, final String email) {
        if (userService.anyByEmail(email)) {
            throw new ConflictException("User with email %s already exists".formatted(email));
        }

        final Student studentEntity = findByIdStream(id);

        studentEntity.setEmail(email);

        studentRepository.save(studentEntity);
    }

    @Override
    public void updatePassword(final Long id, final String password) {
        final Student studentEntity = findByIdStream(id);

        studentEntity.setPassword(password);

        studentRepository.save(studentEntity);
    }

    @Override
    public StudentDto updateName(final Long id, final NameDto nameDto) {
        final Student studentEntity = findByIdStream(id);

        studentEntity.setFirstName(nameDto.getFirstName());
        studentEntity.setLastName(nameDto.getLastName());
        studentEntity.setPatronymic(nameDto.getPatronymic());

        return StudentMapper.fromEntityToDto(studentRepository.save(studentEntity));
    }

    @Override
    public StudentDto updateBirthDate(final Long id, final LocalDate birthDate) {
        final Student studentEntity = findByIdStream(id);

        studentEntity.setBirthDate(birthDate);

        return StudentMapper.fromEntityToDto(studentRepository.save(studentEntity));
    }

    @Override
    public void addAssignment(final Long studentId, final Assignment assignmentEntity) {
        final Student studentEntity = findByIdStream(studentId);

        studentEntity.getAssignments().add(assignmentEntity);

        studentRepository.save(studentEntity);
    }

    @Override
    public void addGroup(final Long studentId, final Group groupEntity) {
        final Student studentEntity = findByIdStream(studentId);

        studentEntity.getGroups().add(groupEntity);

        studentRepository.save(studentEntity);
    }

    @Override
    public void removeGroup(final Long studentId, final Group groupEntity) {
        final Student studentEntity = findByIdStream(studentId);

        studentEntity.getGroups().removeIf(g -> g.getId().equals(groupEntity.getId()));

        studentRepository.save(studentEntity);
    }

    @Override
    public void remove(final Long id) {
        final Student studentEntity = findByIdStream(id);

        studentEntity.setIsActive(Boolean.FALSE);

        studentRepository.save(studentEntity);
    }

    private Student findByIdStream(final Long id) {
        return studentRepository.findByIdAndIsActiveTrue(id)
                                     .orElseThrow(() -> new NotFoundException(
                                             "Student with id %d not found".formatted(id)));
    }

    private Student findByNicknameStream(final String nickname) {
        return studentRepository.findByNicknameAndIsActiveTrue(nickname)
                                     .orElseThrow(() -> new NotFoundException(
                                             "Student with nickname %s not found".formatted(nickname)));
    }

    private Set<Student> findAllByNameStream(final String firstName, final String lastName, final String patronymic) {
        final Set<Student> studentEntities =
                studentRepository.findAllByFirstNameAndLastNameAndPatronymicAndIsActiveTrue(firstName, lastName,
                                                                                                 patronymic);

        if (studentEntities.isEmpty()) {
            throw new NotFoundException(
                    "Students with name %s %s %s not found".formatted(firstName, lastName, patronymic));
        }

        return studentEntities;
    }
}
