package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.MessageDto;
import com.goodteacher.api.dto.MessageSaveDto;
import com.goodteacher.api.entity.Message;

public class MessageMapper {

    public static MessageDto fromEntityToDto(final Message entity) {

        return MessageDto.builder()
                .id(entity.getId())
                .fromUserId(entity.getFromUserId())
                .toUserId(entity.getToUserId())
                .text(entity.getText())
                .build();

    }

    public static Message fromDtoToEntity(final MessageDto dto) {

        return Message.builder()
                .id(dto.getId())
                .fromUserId(dto.getFromUserId())
                .toUserId(dto.getToUserId())
                .text(dto.getText())
                .build();

    }

    public static Message fromSaveDtoToEntity(MessageSaveDto saveDto) {
        return Message.builder()
                .fromUserId(saveDto.getFromUserId())
                .toUserId(saveDto.getToUserId())
                .text(saveDto.getText())
                .build();
    }
}
