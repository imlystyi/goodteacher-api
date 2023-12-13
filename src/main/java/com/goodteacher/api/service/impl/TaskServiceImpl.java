/*
 * Copyright 2023 imlystyi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.dto.TaskSaveDto;
import com.goodteacher.api.entity.Task;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.TaskMapper;
import com.goodteacher.api.repository.TaskRepository;
import com.goodteacher.api.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main implementer of {@link TaskService} interface.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    // region Fields

    private final TaskRepository taskRepository;

    // endregion

    // region Methods

    /**
     * Finds a task by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found task as {@link TaskDto} if the task with the specified ID exists.
     * @throws NotFoundException if the task with the specified ID does not exist.
     */
    @Override
    public TaskDto findById(final Long id) {
        final Task taskEntity = findByIdInRepository(id);

        return TaskMapper.fromEntityToDto(taskEntity);
    }

    /**
     * Finds a task by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found task as {@link Task} if the task with the specified ID exists.
     * @throws NotFoundException if the task with the specified ID does not exist.
     */
    @Override
    public Task findEntityById(final Long id) {
        return findByIdInRepository(id);
    }

    /**
     * Finds tasks by its name.
     *
     * @param name name to search by as {@link String}.
     * @return found tasks as {@link Set}{@code <}{@link TaskDto}{@code >} if any task with the specified name exists.
     * @throws NotFoundException if the task with the specified name does not exist.
     */
    @Override
    public Set<TaskDto> findAllByName(final String name) {
        return findAllByNameInRepository(name)
                .stream()
                .map(TaskMapper::fromEntityToDto)
                .collect(Collectors.toSet());
    }

    /**
     * Finds tasks by author name.
     *
     * @param authorName author name to search by as {@link String}.
     * @return found tasks as {@link Set}{@code <}{@link TaskDto}{@code >} if any task with the specified author name exists.
     * @throws NotFoundException if the task with the specified author name does not exist.
     */
    @Override
    public Set<TaskDto> findAllByAuthorName(final String authorName) {
        return findAllByAuthorNameInRepository(authorName)
                .stream()
                .map(TaskMapper::fromEntityToDto)
                .collect(Collectors.toSet());
    }

    /**
     * Saves the task in the repository.
     *
     * @param taskSaveDto task to save as {@link TaskSaveDto}.
     * @return saved task as {@link TaskDto} if saved successfully.
     */
    @Override
    public TaskDto save(final TaskSaveDto taskSaveDto) {
        final Task taskEntity = TaskMapper.fromSaveDtoToEntity(taskSaveDto);

        return TaskMapper.fromEntityToDto(taskRepository.save(taskEntity));
    }

    /**
     * Sets the specific task inactive.
     *
     * @param id task ID as {@link Long}.
     * @throws NotFoundException if the task with the specified ID does not exist.
     */
    @Override
    public void delete(final Long id) {
        final Task taskEntity = findByIdInRepository(id);

        taskEntity.setIsActive(Boolean.FALSE);

        taskRepository.save(taskEntity);
    }

    private Task findByIdInRepository(final Long id) {
        return taskRepository.findByIdAndIsActiveTrue(id)
                             .orElseThrow(() -> new NotFoundException(
                                     "Task with id %d not found".formatted(id)));
    }

    private Set<Task> findAllByNameInRepository(final String name) {
        final Set<Task> taskEntities = taskRepository.findAllByNameAndIsActiveTrue(name);

        if (taskEntities.isEmpty()) {
            throw new NotFoundException("Tasks with name %s not found".formatted(name));
        }

        return taskEntities;
    }

    private Set<Task> findAllByAuthorNameInRepository(final String authorName) {
        final Set<Task> taskEntities = taskRepository.findAllByAuthorNameAndIsActiveTrue(authorName);

        if (taskEntities.isEmpty()) {
            throw new NotFoundException("Tasks with author name %s not found".formatted(authorName));
        }

        return taskEntities;
    }

    // endregion
}
