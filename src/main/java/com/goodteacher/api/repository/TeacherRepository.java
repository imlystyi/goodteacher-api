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
