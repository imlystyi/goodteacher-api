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
    public MessageDto save(final MessageSaveDto messageSaveDto) {
        final Message messageEntity = MessageMapper.fromSaveDtoToEntity(messageSaveDto);

        return MessageMapper.fromEntityToDto(messageRepository.save(messageEntity));
    }

    @Override
    public MessageDto findById(Long id) {
        final Message messageEntity = this.findByIdStream(id);


        return MessageMapper.fromEntityToDto(messageEntity);
    }

    @Override
    public Set<MessageDto> findByUserId(final Long userId) {
        final Set<Message> messagesEntity = findAllStream();

        return messagesEntity.stream()
                .filter(e -> e.getToUserId().equals(userId))
                .map(MessageMapper::fromEntityToDto)
                .collect(Collectors.toSet());
    }

    private Message findByIdStream(Long id) {
        return this.messageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Message with id %d not found".formatted(id)));
    }

    private Set<Message> findAllStream() {
        final Set<Message> messagesEntity = this.messageRepository.findAllByIsActiveTrue();

        if (messagesEntity.isEmpty()) {
            throw new NotFoundException("Message not found");
        }
        return messagesEntity;
    }
}
