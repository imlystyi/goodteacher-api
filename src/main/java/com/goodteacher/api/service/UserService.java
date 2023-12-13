package com.goodteacher.api.service;

/**
 * Interface that provides the functionality for manipulating the user models.
 */
public interface UserService {
    /**
     * Checks if any user with the specified email exists.
     *
     * @param email email to search by as {@link String}.
     * @return {@code true} if any user with the specified email exists; otherwise, {@code false}.
     */
    boolean anyByEmail(String email);

    /**
     * Checks if any user with the specified nickname exists.
     *
     * @param nickname email to search by as {@link String}.
     * @return {@code true} if any user with the specified email exists; otherwise, {@code false}.
     */
    boolean anyByNickname(String nickname);
}
