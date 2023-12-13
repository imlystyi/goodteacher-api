package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.MessageDto;
import com.goodteacher.api.dto.MessageSaveDto;
import com.goodteacher.api.entity.Message;

/**
 * Mapper for message models.
 */
public class MessageMapper {

    /**
     * Converts entity {@link Message} to DTO {@link MessageDto}.
     *
     * @param entity entity to convert as {@link Message}.
     * @return converted entity as {@link MessageDto}.
     */
    public static MessageDto fromEntityToDto(final Message entity) {

        return MessageDto.builder()
                         .id(entity.getId())
                         .fromUserId(entity.getFromUserId())
                         .toUserId(entity.getToUserId())
                         .text(entity.getText())
                         .build();

    }

    /**
     * Partially converts DTO {@link MessageSaveDto} to entity {@link Message}.<br>
     * Note that it uses only {@code fromUserId}, {@code toUserId} and {@code text} fields.</br>
     *
     * @param saveDto DTO to convert as {@link MessageSaveDto}.
     * @return converted entity as {@link MessageDto}.
     */
    public static Message fromSaveDtoToEntity(final MessageSaveDto saveDto) {
        return Message.builder()
                      .fromUserId(saveDto.getFromUserId())
                      .toUserId(saveDto.getToUserId())
                      .text(saveDto.getText())
                      .build();
    }
}
