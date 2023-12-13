package com.goodteacher.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO that represents user's identity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdentityDto {
    @NotNull
    @NotBlank
    private String nickname;

    @NotNull
    @NotBlank
    private String password;
}
