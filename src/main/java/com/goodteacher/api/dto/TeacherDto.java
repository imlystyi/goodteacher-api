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

import com.goodteacher.api.entity.Teacher;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

/**
 * DTO that represents {@link Teacher} entity.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String patronymic;

    private LocalDate birthDate;

    private String about;

    private String status;

    @Builder.Default
    private Set<Long> groupIds = Set.of();
}
