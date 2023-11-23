package com.goodteacher.api.service.impl;

import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    @Override
    public void addStudentToGroup(Group group, Student student){
        group.getStudents().add(student);
    }

    @Override
    public void deleteStudentFromGroup(Group group, Student student){
        group.getStudents().remove(student);
    }

    @Override
    public void changeTeacherInGroup(Group group, Teacher teacher) {

    }
}
