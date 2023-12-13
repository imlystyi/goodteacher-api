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
