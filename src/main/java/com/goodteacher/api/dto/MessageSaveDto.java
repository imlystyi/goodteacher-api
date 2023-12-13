package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Message;
import com.goodteacher.api.service.MessageService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO that represents {@link Message} entity for making a save.
 *
 * @see MessageService#save(MessageSaveDto)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageSaveDto {
    @NotNull
    private Long fromUserId;

    @NotNull
    private Long toUserId;

    @NotNull
    @NotBlank
    private String text;
}