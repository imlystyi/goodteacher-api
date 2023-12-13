package com.goodteacher.api.service;

import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.dto.GroupSaveDto;

public interface GroupService {
    GroupDto findById(Long id);

    GroupDto save(GroupSaveDto groupSaveDto);

    GroupDto updateName(Long id, String name);

    GroupDto updateAbout(Long id, String about);

    GroupDto updateTeacher(Long groupId, Long teacherId);

    GroupDto addStudent(Long groupId, Long studentId);

    GroupDto removeStudent(Long groupId, Long studentId);

    void remove(Long id);
}
