package com.goodteacher.api.service;

import com.goodteacher.api.dto.NameDto;
import com.goodteacher.api.dto.StudentDto;
import com.goodteacher.api.dto.UserDto;

import java.time.LocalDate;
import java.util.Set;

public interface StudentService {
    StudentDto findById(Long id);

    StudentDto findByNickname(String nickname);

    Set<StudentDto> findAllByName(NameDto nameDto);

    StudentDto save(final UserDto userDto);

    void updateEmail(Long id, String email);

    void updatePassword(Long id, String password);

    StudentDto updateName(Long id, NameDto nameDto);

    StudentDto updateBirthDate(Long id, LocalDate birthDate);

    void delete(Long id);
}
