package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.dto.TaskSaveDto;
import com.goodteacher.api.entity.Task;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.TaskMapper;
import com.goodteacher.api.repository.TaskRepository;
import com.goodteacher.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskDto findById(final Long id) {
        final Task taskEntity = this.findByIdStream(id);

        return TaskMapper.fromEntityToDto(taskEntity);
    }

    @Override
    public Set<TaskDto> findAllByName(final String name) {
        return this.findAllByNameStream(name)
                   .stream()
                   .map(TaskMapper::fromEntityToDto)
                   .collect(Collectors.toSet());
    }

    @Override
    public Set<TaskDto> findAllByAuthorName(final String authorName) {
        return this.findAllByAuthorNameStream(authorName)
                   .stream()
                   .map(TaskMapper::fromEntityToDto)
                   .collect(Collectors.toSet());
    }

    @Override
    public TaskDto save(final TaskSaveDto taskSaveDto) {
        final Task taskEntity = TaskMapper.fromSaveDtoToEntity(taskSaveDto);

        this.taskRepository.save(taskEntity);

        return TaskMapper.fromEntityToDto(taskEntity);
    }

    @Override
    public void delete(final Long id) {
        final Task taskEntity = this.findByIdStream(id);

        taskEntity.setIsActive(Boolean.FALSE);

        this.taskRepository.save(taskEntity);
    }

    private Task findByIdStream(final Long id) {
        return this.taskRepository.findById(id)
                                  .orElseThrow(() -> new NotFoundException(
                                          "Assignment with id %d not found".formatted(id)));
    }

    private Set<Task> findAllByNameStream(final String name) {
        final Set<Task> taskEntities = this.taskRepository.findAllByNameAndIsActiveTrue(name);

        if (taskEntities.isEmpty()) {
            throw new NotFoundException("Tasks with name %s not found".formatted(name));
        }

        return taskEntities;
    }

    private Set<Task> findAllByAuthorNameStream(final String authorName) {
        final Set<Task> taskEntities = this.taskRepository.findAllByAuthorNameAndIsActiveTrue(authorName);

        if (taskEntities.isEmpty()) {
            throw new NotFoundException("Tasks with author name %s not found".formatted(authorName));
        }

        return taskEntities;
    }
}
