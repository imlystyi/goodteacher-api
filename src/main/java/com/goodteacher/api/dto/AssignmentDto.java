/*
 * Copyright 2023 imlystyi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Assignment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO that represents {@link Assignment} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentDto {
    private Long id;

    private String title;

    private TaskDto task;

    private Long teacherId;

    private Long studentId;

    private Double grade;

    private String comment;

    private LocalDate deadline;

    private LocalDate closingDate;

    private Boolean isClosed;
}