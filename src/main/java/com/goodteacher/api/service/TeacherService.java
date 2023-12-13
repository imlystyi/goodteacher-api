package com.goodteacher.api.service;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Teacher;

import java.time.LocalDate;
import java.util.Set;

public interface TeacherService {
    TeacherDto findById(Long id);

    Teacher findEntityById(Long id);

    TeacherDto findByNickname(String nickname);

    Set<TeacherDto> findAllByName(NameDto nameDto);

    // TODO: Provide signing in
    TeacherDto save(final UserDto userDto);

    void updateEmail(Long id, String email);

    void updatePassword(Long id, String password);

    TeacherDto updateName(Long id, NameDto nameDto);

    TeacherDto updateBirthDate(Long id, LocalDate birthDate);

    TeacherDto updateInfo(TeacherInfoDto teacherInfoDto);

    void addGroup(Long teacherId, Group groupEntity);

    /**
     * @param id         teacher id
     * @param groupEntity
     */
    void removeGroup(Long id, Group groupEntity);

    void delete(Long id);
}
