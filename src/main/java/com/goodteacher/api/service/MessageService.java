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

import com.goodteacher.api.dto.*;
import com.goodteacher.api.exception.NotFoundException;

import java.util.Set;

/**
 * Interface that provides the functionality for manipulating the message models.
 */
public interface MessageService {
    /**
     * Finds a message by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found message as {@link MessageDto} if the message with the specified ID exists.
     * @throws NotFoundException if the message with the specified ID does not exist.
     */
    MessageDto findById(Long id);

    /**
     * Finds messages by user IDs.
     *
     * @param fromUserid ID of the user who sent the message as {@link Long}.
     * @param toUserId ID of the user who received the message as {@link Long}.
     * @return found messages as {@link Set}{@code <}{@link MessageDto}{@code >} if any message with the specified user IDs exists.
     * @throws NotFoundException if the message with the specified user IDs does not exist.
     */
    Set<MessageDto> findByUserIds(Long fromUserid, Long toUserId);

    /**
     * Saves the message in the repository.
     *
     * @param messageSaveDto message to save as {@link MessageSaveDto}.
     * @return saved message as {@link MessageDto} if saved successfully.
     */
    MessageDto save(MessageSaveDto messageSaveDto);
}
