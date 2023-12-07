package com.goodteacher.api.repository;

import com.goodteacher.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Repository to manage {@link User} instances.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(final String nickname);

    Optional<User> findByEmail(final String email);

    Set<User> findAllByFirstNameAndLastNameAndPatronymic(final String firstName, final String lastName,
                                                                   final String patronymic);
}
