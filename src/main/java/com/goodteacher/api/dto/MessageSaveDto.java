package com.goodteacher.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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