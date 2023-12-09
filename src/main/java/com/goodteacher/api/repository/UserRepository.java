// TODO: Delete this file

//package com.goodteacher.api.repository;
//
//import com.goodteacher.api.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//import java.util.Set;
//
///**
// * Repository to manage {@link User} instances.
// */
//@Repository
//interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByIdAndIsActiveTrue(final Long id);
//
//    Optional<User> findByNicknameAndIsActiveTrue(final String nickname);
//
//    Optional<User> findByEmailAndIsActiveTrue(final String email);
//
//    Set<User> findAllByFirstNameAndLastNameAndPatronymicAndIsActiveTrue(final String firstName, final String lastName,
//                                                                        final String patronymic);
//}
