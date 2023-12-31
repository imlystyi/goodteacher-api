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

package com.goodteacher.api.service;

import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.dto.TaskSaveDto;
import com.goodteacher.api.entity.Task;
import com.goodteacher.api.exception.NotFoundException;

import java.util.Set;

/**
 * Interface that provides the functionality for manipulating the task models.
 */
public interface TaskService {
    /**
     * Finds a task by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found task as {@link TaskDto} if the task with the specified ID exists.
     * @throws NotFoundException if the task with the specified ID does not exist.
     */
    TaskDto findById(Long id);

    /**
     * Finds a task by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found task as {@link Task} if the task with the specified ID exists.
     * @throws NotFoundException if the task with the specified ID does not exist.
     */
    Task findEntityById(Long id);

    /**
     * Finds tasks by its name.
     *
     * @param name name to search by as {@link String}.
     * @return found tasks as {@link Set}{@code <}{@link TaskDto}{@code >} if any task with the specified name exists.
     * @throws NotFoundException if the task with the specified name does not exist.
     */
    Set<TaskDto> findAllByName(String name);

    /**
     * Finds tasks by author name.
     *
     * @param authorName author name to search by as {@link String}.
     * @return found tasks as {@link Set}{@code <}{@link TaskDto}{@code >} if any task with the specified author name exists.
     * @throws NotFoundException if the task with the specified author name does not exist.
     */
    Set<TaskDto> findAllByAuthorName(String authorName);

    /**
     * Saves the task in the repository.
     *
     * @param taskSaveDto task to save as {@link TaskSaveDto}.
     * @return saved task as {@link TaskDto} if saved successfully.
     */
    TaskDto save(TaskSaveDto taskSaveDto);

    /**
     * Sets the specific task inactive.
     *
     * @param id task ID as {@link Long}.
     * @throws NotFoundException if the task with the specified ID does not exist.
     */
    void delete(Long id);
}
