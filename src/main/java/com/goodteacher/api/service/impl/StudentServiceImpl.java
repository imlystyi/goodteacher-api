package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.NameDto;
import com.goodteacher.api.dto.StudentDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.exception.ConflictException;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.StudentMapper;
import com.goodteacher.api.repository.StudentRepository;
import com.goodteacher.api.service.StudentService;
import com.goodteacher.api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    private final UserService userService;

    @Override
    public StudentDto findById(final Long id) {
        final Student studentEntity = this.findByIdStream(id);

        return StudentMapper.fromEntityToDto(studentEntity);
    }

    @Override
    public StudentDto findByNickname(final String nickname) {
        final Student studentEntity = this.findByNicknameStream(nickname);

        return StudentMapper.fromEntityToDto(studentEntity);
    }

    @Override
    public Set<StudentDto> findAllByName(final NameDto nameDto) {
        return this.findAllByNameStream(nameDto.getFirstName(), nameDto.getLastName(), nameDto.getPatronymic())
                   .stream()
                   .map(StudentMapper::fromEntityToDto)
                   .collect(Collectors.toSet());
    }


    @Override
    public StudentDto save(final UserDto userDto) {
        if (this.userService.anyByNickname(userDto.getNickname())) {
            throw new ConflictException("User with nickname %s already exists".formatted(userDto.getNickname()));
        } else if (this.userService.anyByEmail(userDto.getEmail())) {
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

        final Student studentEntity = this.studentRepository.save(StudentMapper.fromDtoToEntity(studentDto));

        studentDto.setId(studentEntity.getId());

        return studentDto;
    }

    @Override
    public void updateEmail(final Long id, final String email) {
        if (this.userService.anyByEmail(email)) {
            throw new ConflictException("User with email %s already exists".formatted(email));
        }

        final Student studentEntity = this.findByIdStream(id);

        studentEntity.setEmail(email);

        this.studentRepository.save(studentEntity);
    }

    @Override
    public void updatePassword(final Long id, final String password) {
        final Student studentEntity = this.findByIdStream(id);

        studentEntity.setPassword(password);

        this.studentRepository.save(studentEntity);
    }

    @Override
    public StudentDto updateName(final Long id, final NameDto nameDto) {
        final Student studentEntity = this.findByIdStream(id);

        studentEntity.setFirstName(nameDto.getFirstName());
        studentEntity.setLastName(nameDto.getLastName());
        studentEntity.setPatronymic(nameDto.getPatronymic());

        return StudentMapper.fromEntityToDto(this.studentRepository.save(studentEntity));
    }

    @Override
    public StudentDto updateBirthDate(final Long id, final LocalDate birthDate) {
        final Student studentEntity = this.findByIdStream(id);

        studentEntity.setBirthDate(birthDate);

        return StudentMapper.fromEntityToDto(this.studentRepository.save(studentEntity));
    }

    @Override
    public void delete(final Long id) {
        final Student studentEntity = this.findByIdStream(id);

        studentEntity.setIsActive(Boolean.FALSE);

        this.studentRepository.save(studentEntity);
    }

    private Student findByIdStream(final Long id) {
        return this.studentRepository.findByIdAndIsActiveTrue(id)
                                     .orElseThrow(
                                             () -> new NotFoundException("Student with id %d not found".formatted(id)));
    }

    private Student findByNicknameStream(final String nickname) {
        return this.studentRepository.findByNicknameAndIsActiveTrue(nickname)
                                     .orElseThrow(() -> new NotFoundException(
                                             "Student with nickname %s not found".formatted(nickname)));
    }

    private Set<Student> findAllByNameStream(final String firstName, final String lastName, final String patronymic) {
        final Set<Student> studentEntities =
                this.studentRepository.findAllByFirstNameAndLastNameAndPatronymicAndIsActiveTrue(firstName, lastName,
                                                                                                 patronymic);

        if (studentEntities.isEmpty()) {
            throw new NotFoundException(
                    "Students with name %s %s %s not found".formatted(firstName, lastName, patronymic));
        }

        return studentEntities;
    }
}
