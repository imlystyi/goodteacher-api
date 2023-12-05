package com.goodteacher.api.service.impl;


import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.repository.GroupRepository;
import com.goodteacher.api.repository.StudentRepository;
import com.goodteacher.api.repository.TeacherRepository;
import com.goodteacher.api.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private GroupRepository groupRepository;

    public GroupDto findDTOById(final UUID id) {
        final Group group = findById(id);

        return GroupDto.toDTO(group);
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        final Group group = new Group();
        group.setId(groupDto.getId());
        group.setName(groupDto.getName());
        group.setAbout(groupDto.getAbout());
        return GroupDto.toDTO(group);

    }

    @Override
    public void deleteGroupById(final UUID groupId){
        groupRepository.getReferenceById(groupId).setIsActive(false);
    }

    @Override
    public void addStudentToGroup(final UUID groupId, final UUID studentId){
        Student student = studentRepository.getReferenceById(studentId);
        groupRepository.getReferenceById(groupId).getStudents().add(student);
    }

    @Override
    public void deleteStudentFromGroup(final UUID groupId, final UUID studentId){
        Student student = studentRepository.getReferenceById(studentId);
        groupRepository.getReferenceById(groupId).getStudents().remove(student);
    }

    @Override
    public void changeTeacherInGroup(final UUID groupId, final Teacher teacher) {
        groupRepository.getReferenceById(groupId).setTeacher(teacher);
    }
    @Override
    public void changeGroupAbout(final UUID groupId, final String about){
        groupRepository.getReferenceById(groupId).setAbout(about);
    }
    @Override
    public void createAssignmentForAllStudentsInGroup(final UUID groupId, final Assignment assignment){
        groupRepository.getReferenceById(groupId).getStudents().forEach(student ->
                student.getAssignments().add(assignment));
    }
    @Override
    public void updateAssignmentForAllStudentsInGroup(final UUID groupId, final Assignment assignment, final UUID assignmentId){

        groupRepository.getReferenceById(groupId).getStudents().forEach(student ->{
            Assignment assignment1 =
                    student.getAssignments().stream().
                            filter(ass -> ass.getId().equals(assignmentId)).findAny().orElse(null);

            student.getAssignments().remove(assignment1);
            student.getAssignments().add(assignment);
        });
    }
    @Override
    public void deleteAssignmentForAllStudentsInGroup(final UUID groupId, final UUID assignmentId){
        groupRepository.getReferenceById(groupId).getStudents().forEach(student -> {
            Assignment assignment =
                    student.getAssignments().stream().
                            filter(ass -> ass.getId().equals(assignmentId)).findAny().orElse(null);

            student.getAssignments().remove(assignment);
        });
    }
    private Group findById(final UUID id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group not found."));
    }
}
