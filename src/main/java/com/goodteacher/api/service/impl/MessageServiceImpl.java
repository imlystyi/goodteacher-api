package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.MessageDto;
import com.goodteacher.api.dto.MessageSaveDto;
import com.goodteacher.api.entity.Message;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.MessageMapper;
import com.goodteacher.api.repository.MessageRepository;
import com.goodteacher.api.service.MessageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main implementer of {@link MessageService} interface.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    // region Fields

    private final MessageRepository messageRepository;

    // endregion

    // region Methods

    /**
     * Finds a message by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found message as {@link MessageDto} if the message with the specified ID exists.
     * @throws NotFoundException if the message with the specified ID does not exist.
     */
    @Override
    public MessageDto findById(final Long id) {
        final Message messageEntity = findByIdInRepository(id);

        return MessageMapper.fromEntityToDto(messageEntity);
    }

    /**
     * Finds messages by user IDs.
     *
     * @param fromUserId ID of the user who sent the message as {@link Long}.
     * @param toUserId   ID of the user who received the message as {@link Long}.
     * @return found messages as {@link Set}{@code <}{@link MessageDto}{@code >} if any message with the specified user IDs exists.
     * @throws NotFoundException if the message with the specified user IDs does not exist.
     */
    @Override
    public Set<MessageDto> findByUserIds(final Long fromUserId, final Long toUserId) {
        final Set<Message> messagesEntity = findByFromUserIdAndToUserIdInRepository(fromUserId, toUserId);

        return messagesEntity.stream()
                             .map(MessageMapper::fromEntityToDto)
                             .collect(Collectors.toSet());
    }

    /**
     * Saves the message in the repository.
     *
     * @param messageSaveDto message to save as {@link MessageSaveDto}.
     * @return saved message as {@link MessageDto} if saved successfully.
     */
    @Override
    public MessageDto save(final MessageSaveDto messageSaveDto) {
        final Message messageEntity = MessageMapper.fromSaveDtoToEntity(messageSaveDto);

        return MessageMapper.fromEntityToDto(messageRepository.save(messageEntity));
    }

    private Message findByIdInRepository(final Long id) {
        return messageRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException(
                                        "Message with id %d not found".formatted(id)));
    }

    private Set<Message> findByFromUserIdAndToUserIdInRepository(final Long fromUserId, final Long toUserId) {
        final Set<Message> messageEntities = messageRepository.findAllByFromUserIdAndToUserId(fromUserId, toUserId);

        if (messageEntities.isEmpty()) {
            throw new NotFoundException(
                    "Messages with sender id %d and recipient id %d not found".formatted(fromUserId, toUserId));
        }

        return messageEntities;
    }

    // endregion
}
