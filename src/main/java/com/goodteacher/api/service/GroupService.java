package com.goodteacher.api.service;

import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Teacher;

public interface GroupService {
    // TODO: 23-11-2023: Yaroslav: Build service for group
    // TODO: 23-11-2023: Yaroslav: Build resource (RestController) for group and test it

    /* Methods:
     * - to create group;
     * - to delete group (soft);
     * - to add student to group;
     * - to delete student from group;
     * - to change teacher in group;
     * - to change group about;
     * - to create assignment to all students in group;
     * - to update assignment for all students in group;
     * - to delete assignment from all students in group;
     */

    void addStudentToGroup(Group group, Student student);

    void deleteStudentFromGroup(Group group, Student student);

    void changeTeacherInGroup(Group group, Teacher teacher);
}
