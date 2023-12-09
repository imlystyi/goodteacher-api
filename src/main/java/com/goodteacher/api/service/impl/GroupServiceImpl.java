package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.dto.StudentDto;
import com.goodteacher.api.dto.TeacherDto;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.GroupMapper;
import com.goodteacher.api.mapper.StudentMapper;
import com.goodteacher.api.mapper.TeacherMapper;
import com.goodteacher.api.repository.GroupRepository;
import com.goodteacher.api.service.GroupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public GroupDto findById(final Long id) {
        final Group groupEntity = this.findByIdStream(id);

        return GroupMapper.fromEntityToDto(groupEntity);
    }

    @Override
    public GroupDto save(final GroupDto groupDto) {
        final Group groupEntity = this.groupRepository.save(GroupMapper.fromDtoToEntity(groupDto));

        groupDto.setId(groupEntity.getId());

        return groupDto;
    }

    @Override
    public GroupDto updateName(final Long id, final String name) {
        final Group groupEntity = this.findByIdStream(id);

        groupEntity.setName(name);

        return GroupMapper.fromEntityToDto(this.groupRepository.save(groupEntity));
    }

    @Override
    public GroupDto updateTeacher(final Long id, final TeacherDto teacherDto) {
        final Group groupEntity = this.findByIdStream(id);

        groupEntity.setTeacher(TeacherMapper.fromDtoToEntity(teacherDto));

        return GroupMapper.fromEntityToDto(this.groupRepository.save(groupEntity));
    }

    @Override
    public GroupDto updateAbout(final Long id, final String about) {
        final Group groupEntity = this.findByIdStream(id);

        groupEntity.setAbout(about);

        return GroupMapper.fromEntityToDto(this.groupRepository.save(groupEntity));
    }

    @Override
    public GroupDto addStudent(final Long id, final StudentDto studentDto) {
        final Group groupEntity = this.findByIdStream(id);

        final Student studentEntity = StudentMapper.fromDtoToEntity(studentDto);

        groupEntity.getStudents().add(studentEntity);

        return GroupMapper.fromEntityToDto(this.groupRepository.save(groupEntity));
    }

    @Override
    public GroupDto deleteStudent(final Long id, final StudentDto studentDto) {
        final Group groupEntity = this.findByIdStream(id);

        final Student studentEntity = StudentMapper.fromDtoToEntity(studentDto);

        groupEntity.getStudents().remove(studentEntity);

        return GroupMapper.fromEntityToDto(this.groupRepository.save(groupEntity));
    }

    @Override
    public void delete(final Long id) {
        final Group groupEntity = this.findByIdStream(id);

        groupEntity.setIsActive(Boolean.FALSE);

        this.groupRepository.save(groupEntity);
    }

    private Group findByIdStream(final Long id) {
        return this.groupRepository.findByIdAndIsActiveTrue(id)
                                     .orElseThrow(() -> new NotFoundException(
                                             "Group with id %d not found".formatted(id)));
    }
}
