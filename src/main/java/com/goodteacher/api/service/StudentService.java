package com.goodteacher.api.service;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.exception.ConflictException;
import com.goodteacher.api.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Set;

/**
 * Interface that provides the functionality for manipulating the student models.
 */
public interface StudentService {
    /**
     * Finds a student by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found student as {@link StudentDto} if the student with the specified ID exists.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    StudentDto findById(Long id);

    /**
     * Finds a student by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found student as {@link Student} if the student with the specified ID exists.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    Student findEntityById(Long id);

    /**
     * Finds a student by nickname.
     *
     * @param nickname nickname to search by as {@link String}.
     * @return found student as {@link Student} if the student with the specified nickname exists.
     * @throws NotFoundException if the student with the specified nickname does not exist.
     */
    StudentDto findByNickname(String nickname);

    /**
     * Finds students by name.
     *
     * @param nameDto name to search by as {@link NameDto}.
     * @return found students as {@link Set}{@code <}{@link StudentDto}{@code >} if any student with the specified name exists.
     * @throws NotFoundException if the student with the specified name does not exist.
     */
    Set<StudentDto> findAllByName(NameDto nameDto);

    /**
     * Signing student in.
     *
     * @param identityDto identity to signing in by as {@link IdentityDto}.
     * @return {@code true} if the signing in was successful; otherwise, {@code false}.
     */
    boolean signIn(IdentityDto identityDto);

    /**
     * Saves the student in the repository.
     *
     * @param userDto student to save as {@link UserDto}.
     * @return saved student as {@link StudentDto} if saved successfully.
     * @throws ConflictException if the student with the specified email or nickname already exists.
     */
    StudentDto save(UserDto userDto);

    /**
     * Updates the email of the specified student.
     *
     * @param id    student ID as {@link Long}.
     * @param email new email as {@link String}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     * @throws ConflictException if the student with the specified email already exists.
     */
    void updateEmail(Long id, String email);

    /**
     * Updates the password of the specified student.
     *
     * @param id       student ID as {@link Long}.
     * @param password new password as {@link String}.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    void updatePassword(Long id, String password);

    /**
     * Updates the name of the specified student.
     *
     * @param id      student ID as {@link Long}.
     * @param nameDto new name as {@link NameDto}.
     * @return updated student as {@link StudentDto} if updated successfully.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    StudentDto updateName(Long id, NameDto nameDto);

    /**
     * Updates the birthdate of the specified student.
     *
     * @param id        student ID as {@link Long}.
     * @param birthDate new birthdate as {@link LocalDate}.
     * @return updated student as {@link StudentDto} if updated successfully.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    StudentDto updateBirthDate(Long id, LocalDate birthDate);

    /**
     * Adds an assignment to the assignment list of the specified student.
     *
     * @param id               student ID as {@link Long}.
     * @param assignmentEntity assignment to add as {@link Assignment}.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    void addAssignment(Long id, Assignment assignmentEntity);

    /**
     * Adds a group to the group list of the specified student.
     *
     * @param id          student ID as {@link Long}.
     * @param groupEntity group to add as {@link Group}.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    void addGroup(Long id, Group groupEntity);

    /**
     * Removes a group from the group list of the specified student.
     *
     * @param studentId student ID as {@link Long}.
     * @param groupId   ID of the group to remove as {@link Long}.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    void removeGroup(Long studentId, Long groupId);

    /**
     * Sets the specific student inactive.
     *
     * @param id student ID as {@link Long}.
     * @throws NotFoundException if the student with the specified ID does not exist.
     */
    void delete(Long id);
}
