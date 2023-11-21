package com.goodteacher.api.service;

import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Teacher;

public interface GroupService {

    Group addStudentToGroup(Group group, Student student);

    Group deleteStudentFromGroup(Group group, Student student);

    Group changeTeacherInGroup(Group group, Teacher teacher);
}
