package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Group;
import com.goodteacher.api.service.GroupService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * DTO that represents {@link Group} entity for making a save.
 *
 * @see GroupService#save(GroupSaveDto)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupSaveDto {
    @NotNull
    @NotBlank
    private String name;

    private String about;

    @NotNull
    private Long teacherId;

    @NotNull
    private Set<Long> studentIds;
}
