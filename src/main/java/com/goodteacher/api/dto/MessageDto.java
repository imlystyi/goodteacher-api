package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO that represents {@link Message} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Long id;

    private Long fromUserId;

    private Long toUserId;

    private String text;
}