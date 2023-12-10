package com.goodteacher.api.service;

import com.goodteacher.api.dto.*;

import java.time.LocalDate;
import java.util.Set;

public interface TeacherService {
    TeacherDto findById(Long id);

    TeacherDto findByNickname(String nickname);

    Set<TeacherDto> findAllByName(NameDto nameDto);

    // TODO: Provide signing in
    TeacherDto save(final UserDto userDto);

    void updateEmail(Long id, String email);

    void updatePassword(Long id, String password);

    TeacherDto updateName(Long id, NameDto nameDto);

    TeacherDto updateBirthDate(Long id, LocalDate birthDate);

    TeacherDto updateInfo(TeacherInfoDto teacherInfoDto);

    void delete(Long id);
}
