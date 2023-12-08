// BAD YET

package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.AssignmentDto;
import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.dto.TaskSaveDto;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.entity.Task;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.AssignmentMapper;
import com.goodteacher.api.mapper.TaskMapper;
import com.goodteacher.api.repository.TaskRepository;
import com.goodteacher.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskDto findById(Long id) {
        final Task taskEntity = this.findByIdStream(id);
        return TaskMapper.fromEntityToDto(taskEntity);
    }

    @Override
    public TaskDto findByName(final String name) {

        final Task taskEntity = this.findByNameStream(name, true);

        return TaskMapper.fromEntityToDto(taskEntity);
    }

    @Override
    public TaskDto findByAuthorName(final String authorName) {

        final Task taskEntity = this.findByAuthorNameStream(authorName, true);

        return TaskMapper.fromEntityToDto(taskEntity);
    }

    @Override
    public List<TaskDto> findAllByName(final String name) {
        return this.findAllByNameStream(name)
                .stream()
                .map(TaskMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto save(TaskDto taskDto) {
        final Task taskEntity = TaskMapper.fromDtoToEntity(taskDto);
        this.taskRepository.save(taskEntity);
        return TaskMapper.fromEntityToDto(taskEntity);
    }

    @Override
    public void delete(Long id) {
        final Task taskEntity = this.findByIdStream(id);
        taskEntity.setIsActive(Boolean.FALSE);
        this.taskRepository.save(taskEntity);
    }

    private Task findByIdStream(final Long id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Assignment with id %d not found".formatted(id)));
    }

    private Task findByNameStream(final String name, final boolean throwExceptionIfNotFound) {
        return throwExceptionIfNotFound
                ? this.taskRepository.findByName(name).orElseThrow(() -> new NotFoundException(
                "Task with name \"%s\" not found".formatted(name)))
                : this.taskRepository.findByName(name).orElse(null);
    }

    private Task findByAuthorNameStream(final String authorName, final boolean throwExceptionIfNotFound) {
        return throwExceptionIfNotFound
                ? this.taskRepository.findByAuthorName(authorName).orElseThrow(() -> new NotFoundException(
                "Task with name \"%s\" not found".formatted(authorName)))
                : this.taskRepository.findByAuthorName(authorName).orElse(null);
    }

    private List<Task> findAllByNameStream(final String name) {
        final List<Task> taskEntities = this.taskRepository.findAllByName(name);

        if (taskEntities.isEmpty()) {
            throw new NotFoundException("Tasks with name \"%s %s %s\" not found".formatted(name));
        }

        return taskEntities;
    }
}
