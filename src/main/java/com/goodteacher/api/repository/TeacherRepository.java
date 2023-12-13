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

package com.goodteacher.api.repository;

import com.goodteacher.api.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Repository to manage {@link Teacher} entities.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsByNicknameAndPasswordAndIsActiveTrue(String nickname, String password);

    Optional<Teacher> findByIdAndIsActiveTrue(Long id);

    Optional<Teacher> findByNicknameAndIsActiveTrue(String nickname);

    Set<Teacher> findAllByFirstNameAndLastNameAndPatronymicAndIsActiveTrue(String firstName, String lastName,
                                                                           String patronymic);
}
