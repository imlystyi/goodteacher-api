package com.goodteacher.api.service;

import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Teacher;

import java.util.UUID;

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
    GroupDto createGroup(GroupDto groupDto);

    void deleteGroupById(final UUID id);
    void addStudentToGroup(final UUID groupId, final UUID studentId);

    void deleteStudentFromGroup(final UUID groupId, final UUID studentId);

    void changeTeacherInGroup(final UUID groupId, final Teacher teacher);

    void changeGroupAbout(final UUID groupId, final String about);

    void createAssignmentForAllStudentsInGroup(final UUID groupId, final Assignment assignment);
    void updateAssignmentForAllStudentsInGroup(final UUID groupId, final Assignment assignment, final UUID assignmentId);
    void deleteAssignmentForAllStudentsInGroup(final UUID groupId, final UUID assignmentId);


}
