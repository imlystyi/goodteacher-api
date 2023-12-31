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

package com.goodteacher.api.service.impl;

import com.goodteacher.api.repository.StudentRepository;
import com.goodteacher.api.repository.TeacherRepository;
import com.goodteacher.api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Main implementer of {@link UserService} interface.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {
    // region Fields

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    // endregion

    // region Methods

    /**
     * Checks if any user with the specified email exists.
     *
     * @param email email to search by as {@link String}.
     * @return {@code true} if any user with the specified email exists; otherwise, {@code false}.
     */
    @Override
    public boolean anyByEmail(final String email) {
        return studentRepository.existsByEmail(email) || teacherRepository.existsByEmail(email);
    }

    /**
     * Checks if any user with the specified nickname exists.
     *
     * @param nickname email to search by as {@link String}.
     * @return {@code true} if any user with the specified email exists; otherwise, {@code false}.
     */
    @Override
    public boolean anyByNickname(final String nickname) {
        return studentRepository.existsByNickname(nickname) || teacherRepository.existsByNickname(nickname);
    }

    // endregion
}
