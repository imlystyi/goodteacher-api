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
