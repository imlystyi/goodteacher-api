package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.MessageDto;
import com.goodteacher.api.dto.MessageSaveDto;
import com.goodteacher.api.entity.Message;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.mapper.MessageMapper;
import com.goodteacher.api.repository.MessageRepository;
import com.goodteacher.api.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public MessageDto findById(final Long id) {
        final Message messageEntity = this.findByIdStream(id);

        return MessageMapper.fromEntityToDto(messageEntity);
    }

    @Override
    public Set<MessageDto> findByUserIds(final Long fromUserId, final Long toUserId) {
        final Set<Message> messagesEntity = this.findByFromUserIdAndToUserIdStream(fromUserId, toUserId);

        return messagesEntity.stream()
                .map(MessageMapper::fromEntityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public MessageDto save(final MessageSaveDto messageSaveDto) {
        final Message messageEntity = MessageMapper.fromSaveDtoToEntity(messageSaveDto);

        return MessageMapper.fromEntityToDto(this.messageRepository.save(messageEntity));
    }

    private Message findByIdStream(final Long id) {
        return this.messageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Message with id %d not found".formatted(id)));
    }

    private Set<Message> findByFromUserIdAndToUserIdStream(final Long fromUserId, final Long toUserId) {
        final Set<Message> messageEntities = this.messageRepository.findAllByFromUserIdAndToUserId(fromUserId, toUserId);

        if (messageEntities.isEmpty()) {
            throw new NotFoundException(
                    "Messages with sender id %d and recipient id %d not found".formatted(fromUserId, toUserId));
        }

        return messageEntities;
    }
}
