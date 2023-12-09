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
    /**
     * @param email Email to search by.
     * @return {@code true} if {@link Teacher} with specified email exists, {@code false} otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * @param nickname Nickname to search by.
     * @return {@code true} if {@link Teacher} with specified nickname exists, {@code false} otherwise.
     */
    boolean existsByNickname(String nickname);

    Optional<Teacher> findByIdAndIsActiveTrue(Long id);

    Optional<Teacher> findByNicknameAndIsActiveTrue(String nickname);

    Set<Teacher> findAllByFirstNameAndLastNameAndPatronymicAndIsActiveTrue(String firstName, String lastName,
                                                                           String patronymic);
}
