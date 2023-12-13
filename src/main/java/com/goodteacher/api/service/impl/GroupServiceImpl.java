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

/**
 * Main implementer of {@link GroupService} interface.
 */
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

    /**
     * Finds a group by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found group as {@link GroupDto} if the group with specified ID exists.
     * @throws NotFoundException if the group with specified ID does not exist.
     */
    @Override
    public GroupDto findById(final Long id) {
        final Group groupEntity = findByIdInRepository(id);

        return GroupMapper.fromEntityToDto(groupEntity);
    }

    /**
     * Saves the assignment in the repository.
     *
     * @param groupSaveDto group to save as {@link GroupDto}.
     * @return saved assignment as {@link GroupDto} if saved successfully.
     */
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

    /**
     * Updates the name of the specific group.
     *
     * @param id   group ID as {@link Long}.
     * @param name new name as {@link String}.
     * @return updated group as {@link GroupDto} if updated successfully.
     * @throws NotFoundException if the group with specified ID does not exist.
     */
    @Override
    public GroupDto updateName(final Long id, final String name) {
        final Group groupEntity = findByIdInRepository(id);

        groupEntity.setName(name);

        return GroupMapper.fromEntityToDto(groupRepository.save(groupEntity));
    }

    /**
     * Updates the about of the specific group.
     *
     * @param id    group ID as {@link Long}.
     * @param about new about as {@link String}.
     * @return updated group as {@link GroupDto} if updated successfully.
     * @throws NotFoundException if the group with specified ID does not exist.
     */
    @Override
    public GroupDto updateAbout(final Long id, final String about) {
        final Group groupEntity = findByIdInRepository(id);

        groupEntity.setAbout(about);

        return GroupMapper.fromEntityToDto(groupRepository.save(groupEntity));
    }

    /**
     * Updates the teacher of the specific group.
     *
     * @param groupId   group ID as {@link Long}.
     * @param teacherId teacher ID as {@link Long}.
     * @return updated group as {@link GroupDto} if updated successfully.
     * @throws NotFoundException if the group/teacher with specified ID does not exist.
     */
    @Override
    public GroupDto updateTeacher(final Long groupId, final Long teacherId) {
        final Group groupEntity = findByIdInRepository(groupId);
        final Teacher teacherEntity = teacherService.findEntityById(teacherId);

        teacherService.removeGroup(groupEntity.getTeacher().getId(), groupId);

        groupEntity.setTeacher(teacherEntity);

        teacherService.addGroup(teacherId, groupEntity);

        return GroupMapper.fromEntityToDto(groupRepository.save(groupEntity));
    }

    /**
     * Adds the student to the specific group.
     *
     * @param groupId  group ID as {@link Long}.
     * @param studentId student ID as {@link Long}.
     * @return updated group as {@link GroupDto} if updated successfully.
     * @throws NotFoundException if the group/student with specified ID does not exist.
     */
    @Override
    public GroupDto addStudent(final Long groupId, final Long studentId) {
        final Group groupEntity = findByIdInRepository(groupId);
        final Student studentEntity = studentService.findEntityById(studentId);

        groupEntity.getStudents().add(studentEntity);

        studentService.addGroup(studentId, groupEntity);

        return GroupMapper.fromEntityToDto(groupRepository.save(groupEntity));
    }

    /**
     * Removes the student from the specific group.
     *
     * @param groupId  group ID as {@link Long}.
     * @param studentId student ID as {@link Long}.
     * @return updated group as {@link GroupDto} if updated successfully.
     * @throws NotFoundException if the group/student with specified ID does not exist.
     */
    @Override
    public GroupDto removeStudent(final Long groupId, final Long studentId) {
        final Group groupEntity = findByIdInRepository(groupId);

        groupEntity.getStudents().removeIf(s -> s.getId().equals(studentId));

        studentService.removeGroup(studentId, groupId);

        return GroupMapper.fromEntityToDto(groupRepository.save(groupEntity));
    }

    /**
     * Sets the specific group inactive.
     *
     * @param id group ID as {@link Long}.
     * @throws NotFoundException if the group with specified ID does not exist.
     */
    @Override
    public void delete(final Long id) {
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
