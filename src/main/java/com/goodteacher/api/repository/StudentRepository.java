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
    /**
     * @param email Email to search by.
     * @return {@code true} if {@link Student} with specified email exists, {@code false} otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * @param nickname Nickname to search by.
     * @return {@code true} if {@link Student} with specified nickname exists, {@code false} otherwise.
     */
    boolean existsByNickname(String nickname);

    /**
     * @param id ID to search by.
     * @return Present {@link Optional}{@code <}{@link Student}{@code >} with the found student if the student with specified ID exists and is active; otherwise, empty {@link Optional}.
     */
    Optional<Student> findByIdAndIsActiveTrue(Long id);

    /**
     * @param nickname Nickname to search by.
     * @return Present {@link Optional}{@code <}{@link Student}{@code >} with the found student if the student with specified nickname exists and is active; otherwise, empty {@link Optional}.
     */
    Optional<Student> findByNicknameAndIsActiveTrue(String nickname);

    /**
     * @param firstName  First name to search by.
     * @param lastName   Last name to search by.
     * @param patronymic Patronymic to search by.
     * @return Present {@link Optional}{@code <}{@link Student}{@code >} with the found student if the student with specified name exists and is active; otherwise, empty {@link Optional}.
     */
    Set<Student> findAllByFirstNameAndLastNameAndPatronymicAndIsActiveTrue(String firstName, String lastName,
                                                                           String patronymic);
}
