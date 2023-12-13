package com.goodteacher.api.repository;

import com.goodteacher.api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Repository to manage {@link Student} entities.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Optional<Student> findByIdAndIsActiveTrue(Long id);

    Optional<Student> findByNicknameAndIsActiveTrue(String nickname);

    Set<Student> findAllByFirstNameAndLastNameAndPatronymicAndIsActiveTrue(String firstName, String lastName,
                                                                           String patronymic);

    boolean existsByNicknameAndPasswordAndIsActiveTrue(String nickname, String password);
}
