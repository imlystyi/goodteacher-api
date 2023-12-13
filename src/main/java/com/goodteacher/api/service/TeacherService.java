package com.goodteacher.api.service;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.entity.Group;
import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.exception.ConflictException;
import com.goodteacher.api.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Set;

/**
 * Interface that provides the functionality for manipulating the teacher models.
 */
public interface TeacherService {
    /**
     * Finds a teacher by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found teacher as {@link TeacherDto} if the teacher with the specified ID exists.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    TeacherDto findById(Long id);

    /**
     * Finds a teacher by ID.
     *
     * @param id ID to search by as {@link Long}.
     * @return found teacher as {@link Teacher} if the teacher with the specified ID exists.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    Teacher findEntityById(Long id);

    /**
     * Finds a teacher by nickname.
     *
     * @param nickname nickname to search by as {@link String}.
     * @return found teacher as {@link Teacher} if the teacher with the specified nickname exists.
     * @throws NotFoundException if the teacher with the specified nickname does not exist.
     */
    TeacherDto findByNickname(String nickname);

    /**
     * Finds teachers by name.
     *
     * @param nameDto name to search by as {@link NameDto}.
     * @return found teachers as {@link Set}{@code <}{@link TeacherDto}{@code >} if any teacher with the specified name exists.
     * @throws NotFoundException if the teacher with the specified name does not exist.
     */
    Set<TeacherDto> findAllByName(NameDto nameDto);

    /**
     * Signing teacher in.
     *
     * @param identityDto identity to signing in by as {@link IdentityDto}.
     * @return {@code true} if the signing in was successful; otherwise, {@code false}.
     */
    boolean signIn(IdentityDto identityDto);

    /**
     * Saves the teacher in the repository.
     *
     * @param userDto teacher to save as {@link UserDto}.
     * @return saved teacher as {@link TeacherDto} if saved successfully.
     * @throws ConflictException if the student with the specified email or nickname already exists.
     */
    TeacherDto save(UserDto userDto);

    /**
     * Updates the email of the specified teacher.
     *
     * @param id    teacher ID as {@link Long}.
     * @param email new email as {@link String}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     * @throws ConflictException if the student with the specified email or nickname already exists.
     */
    void updateEmail(Long id, String email);

    /**
     * Updates the password of the specified teacher.
     *
     * @param id       teacher ID as {@link Long}.
     * @param password new password as {@link String}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    void updatePassword(Long id, String password);

    /**
     * Updates the name of the specified teacher.
     *
     * @param id      teacher ID as {@link Long}.
     * @param nameDto new name as {@link NameDto}.
     * @return updated teacher as {@link TeacherDto} if updated successfully.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    TeacherDto updateName(Long id, NameDto nameDto);

    /**
     * Updates the birthdate of the specified teacher.
     *
     * @param id        teacher ID as {@link Long}.
     * @param birthDate new birthdate as {@link LocalDate}.
     * @return updated teacher as {@link TeacherDto} if updated successfully.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    TeacherDto updateBirthDate(Long id, LocalDate birthDate);

    /**
     * Updates the main info of the specified teacher ({@code about} and {@code status}).
     *
     * @param teacherInfoDto new teacher info as {@link TeacherInfoDto}.
     * @return updated teacher as {@link TeacherDto} if updated successfully.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    TeacherDto updateInfo(TeacherInfoDto teacherInfoDto);

    /**
     * Adds the group to the group list of the specified teacher.
     *
     * @param id          teacher ID as {@link Long}.
     * @param groupEntity group to add as {@link Group}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    void addGroup(Long id, Group groupEntity);

    /**
     * Removes the group from the group list of the specified teacher.
     *
     * @param teacherId teacher ID as {@link Long}.
     * @param groupId   group to remove ID as {@link Long}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    void removeGroup(Long teacherId, Long groupId);

    /**
     * Sets the specific teacher inactive.
     *
     * @param id teacher ID as {@link Long}.
     * @throws NotFoundException if the teacher with the specified ID does not exist.
     */
    void delete(Long id);
}
