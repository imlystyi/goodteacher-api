package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.service.AssignmentService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * DTO that represents {@link Assignment} entity for making a group save.
 *
 * @see AssignmentService#groupSave(AssignmentGroupSaveDto, Long)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentGroupSaveDto {
    @NotNull
    private String title;

    @NotNull
    private Long taskId;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deadline;
}
