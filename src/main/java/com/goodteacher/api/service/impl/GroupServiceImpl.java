package com.goodteacher.api.service.impl;

import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;


public class GroupServiceImpl {

    public void addStudentToGroup(Group group, Student student){
        group.getStudents().add(student);
    }

    public void deleteStudentFromGroup(Group group, Student student){
        group.getStudents().remove(student);
    }
}
