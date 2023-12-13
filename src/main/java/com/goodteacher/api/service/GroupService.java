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

import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.dto.GroupSaveDto;
import com.goodteacher.api.exception.NotFoundException;

/**
 * Interface that provides the functionality for manipulating the group models.
 */
public interface GroupService {
    /**
     * Finds a group by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found group as {@link GroupDto} if the group with specified ID exists.
     * @throws NotFoundException if the group with specified ID does not exist.
     */
    GroupDto findById(Long id);

    /**
     * Saves the assignment in the repository.
     *
     * @param groupSaveDto group to save as {@link GroupDto}.
     * @return saved assignment as {@link GroupDto} if saved successfully.
     */
    GroupDto save(GroupSaveDto groupSaveDto);

    /**
     * Updates the name of the specific group.
     *
     * @param id   group ID as {@link Long}.
     * @param name new name as {@link String}.
     * @return updated group as {@link GroupDto} if updated successfully.
     * @throws NotFoundException if the group with specified ID does not exist.
     */
    GroupDto updateName(Long id, String name);

    /**
     * Updates the about of the specific group.
     *
     * @param id    group ID as {@link Long}.
     * @param about new about as {@link String}.
     * @return updated group as {@link GroupDto} if updated successfully.
     * @throws NotFoundException if the group with specified ID does not exist.
     */
    GroupDto updateAbout(Long id, String about);

    /**
     * Updates the teacher of the specific group.
     *
     * @param groupId   group ID as {@link Long}.
     * @param teacherId teacher ID as {@link Long}.
     * @return updated group as {@link GroupDto} if updated successfully.
     * @throws NotFoundException if the group/teacher with specified ID does not exist.
     */
    GroupDto updateTeacher(Long groupId, Long teacherId);

    /**
     * Adds the student to the specific group.
     *
     * @param groupId  group ID as {@link Long}.
     * @param studentId student ID as {@link Long}.
     * @return updated group as {@link GroupDto} if updated successfully.
     * @throws NotFoundException if the group/student with specified ID does not exist.
     */
    GroupDto addStudent(Long groupId, Long studentId);

    /**
     * Removes the student from the specific group.
     *
     * @param groupId  group ID as {@link Long}.
     * @param studentId student ID as {@link Long}.
     * @return updated group as {@link GroupDto} if updated successfully.
     * @throws NotFoundException if the group/student with specified ID does not exist.
     */
    GroupDto removeStudent(Long groupId, Long studentId);

    /**
     * Sets the specific group inactive.
     *
     * @param id group ID as {@link Long}.
     * @throws NotFoundException if the group with specified ID does not exist.
     */
    void delete(Long id);
}
