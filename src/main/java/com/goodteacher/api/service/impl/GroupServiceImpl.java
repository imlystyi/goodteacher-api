package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.dto.GroupSaveDto;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.GroupMapper;
import com.goodteacher.api.repository.GroupRepository;
import com.goodteacher.api.service.GroupService;
import com.goodteacher.api.service.StudentService;
import com.goodteacher.api.service.TeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    // region Fields

    private final GroupRepository groupRepository;

    private final TeacherService teacherService;
    private final StudentService studentService;

    // endregion

    // region Methods

    @Override
    public GroupDto findById(final Long id) {
        final Group groupEntity = findByIdInRepository(id);

        return GroupMapper.fromEntityToDto(groupEntity);
    }

    @Override
    public GroupDto save(final GroupSaveDto groupSaveDto) {
        final Group groupEntity = GroupMapper.fromSaveDtoToEntity(groupSaveDto);

        final Long teacherId = groupSaveDto.getTeacherId();
        final Set<Long> studentIds = groupSaveDto.getStudentIds();

        groupEntity.setTeacher(teacherService.findEntityById(teacherId));
        groupEntity.setStudents(studentIds.stream()
                                          .map(studentService::findEntityById)
                                          .collect(java.util.stream.Collectors.toList()));

        final Group savedGroupEntity = groupRepository.save(groupEntity);

        teacherService.addGroup(teacherId, savedGroupEntity);
        studentIds.forEach(i -> studentService.addGroup(i, savedGroupEntity));

        return GroupMapper.fromEntityToDto(savedGroupEntity);
    }

    @Override
    public GroupDto updateName(final Long id, final String name) {
        final Group groupEntity = findByIdInRepository(id);

        groupEntity.setName(name);

        return GroupMapper.fromEntityToDto(groupRepository.save(groupEntity));
    }

    @Override
    public GroupDto updateAbout(final Long id, final String about) {
        final Group groupEntity = findByIdInRepository(id);

        groupEntity.setAbout(about);

        return GroupMapper.fromEntityToDto(groupRepository.save(groupEntity));
    }

    @Override
    public GroupDto updateTeacher(final Long groupId, final Long teacherId) {
        final Group groupEntity = findByIdInRepository(groupId);
        final Teacher teacherEntity = teacherService.findEntityById(teacherId);

        teacherService.removeGroup(groupEntity.getTeacher().getId(), groupEntity);

        groupEntity.setTeacher(teacherEntity);

        teacherService.addGroup(teacherId, groupEntity);

        return GroupMapper.fromEntityToDto(groupRepository.save(groupEntity));
    }

    @Override
    public GroupDto addStudent(final Long groupId, final Long studentId) {
        final Group groupEntity = findByIdInRepository(groupId);
        final Student studentEntity = studentService.findEntityById(studentId);

        groupEntity.getStudents().add(studentEntity);

        studentService.addGroup(studentId, groupEntity);

        return GroupMapper.fromEntityToDto(groupRepository.save(groupEntity));
    }

    @Override
    public GroupDto removeStudent(final Long id, final Long studentId) {
        final Group groupEntity = findByIdInRepository(id);

        groupEntity.getStudents().removeIf(s -> s.getId().equals(studentId));

        studentService.removeGroup(studentId, groupEntity);

        return GroupMapper.fromEntityToDto(groupRepository.save(groupEntity));
    }

    @Override
    public void remove(final Long id) {
        final Group groupEntity = findByIdInRepository(id);

        groupEntity.setIsActive(Boolean.FALSE);

        groupRepository.save(groupEntity);
    }

    private Group findByIdInRepository(final Long id) {
        return groupRepository.findByIdAndIsActiveTrue(id)
                                   .orElseThrow(() -> new NotFoundException(
                                           "Group with id %d not found".formatted(id)));
    }

    // endregion
}
