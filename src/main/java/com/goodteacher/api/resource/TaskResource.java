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

package com.goodteacher.api.resource;

import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.dto.TaskSaveDto;
import com.goodteacher.api.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * The REST controller to handle the requests to the task resource.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskResource {
    // region Fields

    private final TaskService taskService;

    // endregion

    // region GET mappings

    /**
     * Represents the HTTP GET request method for finding a task by its ID.
     *
     * @param id ID to search by as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the found task as {@link TaskDto} in the response body if the task was successfully found; otherwise, result depends on problem.
     */
    @GetMapping("/find/id/{id}")
    public ResponseEntity<TaskDto> findById(final @PathVariable Long id) {
        final TaskDto foundTaskDto = taskService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(foundTaskDto);
    }

    /**
     * Represents the HTTP GET request method for finding tasks by their name.
     *
     * @param name name to search by as {@link String}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the found tasks as {@link Set}{@code <}{@link TaskDto}{@code >} in the response body if any task was successfully found; otherwise, result depends on problem.
     */
    @GetMapping("/find/name/{name}")
    public ResponseEntity<Set<TaskDto>> findAllByName(final @PathVariable String name) {
        final Set<TaskDto> foundTaskDtos = taskService.findAllByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(foundTaskDtos);
    }

    /**
     * Represents the HTTP GET request method for finding tasks by their author's ID.
     *
     * @param authorName author name to search by as {@link String}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the found tasks as {@link Set}{@code <}{@link TaskDto}{@code >} in the response body if any task was successfully found; otherwise, result depends on problem.
     */
    @GetMapping("/find/author-name/{authorName}")
    public ResponseEntity<Set<TaskDto>> findAllByAuthorName(final @PathVariable String authorName) {
        final Set<TaskDto> foundTaskDtos = taskService.findAllByAuthorName(authorName);

        return ResponseEntity.status(HttpStatus.OK).body(foundTaskDtos);
    }

    // endregion

    // region POST mappings

    /**
     * Represents the HTTP POST request method for saving a task.
     *
     * @param taskSaveDto task to save as {@link TaskSaveDto}. Must be contained in the request body and be successfully validated.
     * @return {@link ResponseEntity} with HTTP status 201 "Created" and the saved task as {@link TaskDto} in the response body if the task was successfully saved; otherwise, result depends on problem.
     */
    @PostMapping("/create")
    public ResponseEntity<TaskDto> save(final @RequestBody @Valid TaskSaveDto taskSaveDto) {
        final TaskDto savedTaskDto = taskService.save(taskSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTaskDto);
    }

    // endregion

    // region DELETE mappings


    /**
     * Represents the HTTP DELETE request method for deleting a task by its ID.
     *
     * @param id task ID as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 204 "No Content" if the task was successfully deleted; otherwise, result depends on problem.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(final @PathVariable Long id) {
        taskService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // endregion
}
