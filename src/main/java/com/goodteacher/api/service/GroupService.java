package com.goodteacher.api.service;

import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.dto.GroupSaveDto;
import com.goodteacher.api.dto.StudentDto;
import com.goodteacher.api.dto.TeacherDto;

public interface GroupService {
    GroupDto findById(Long id);

    GroupDto save(GroupSaveDto groupSaveDto);

    GroupDto updateName(Long id, String name);

    GroupDto updateTeacher(Long id, TeacherDto teacherDto);

    GroupDto updateAbout(Long id, String about);

    GroupDto addStudent(Long groupId, StudentDto studentDto);

    GroupDto deleteStudent(Long groupId, StudentDto studentDto);

    void delete(Long id);
}
