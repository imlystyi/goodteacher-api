package com.goodteacher.api.resource;

import com.goodteacher.api.dto.NameDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.dto.UserSaveDto;
import com.goodteacher.api.entity.User;
import com.goodteacher.api.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * REST controller to manage {@link User} entities.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    // region GET mappings

    /**
     * Represents the HTTP GET request method for finding a user by their nickname.
     *
     * @param nickname The nickname of the user to find, represented as {@link String}.
     * @return If the user was successfully found, {@link ResponseEntity} with HTTP status 302 "Found" and the found
     * user as {@link UserDto} as the response body;<br>
     * otherwise, result depends on problem.</br>
     */
    @GetMapping("/find/nickname/{nickname}")
    public ResponseEntity<UserDto> findByNickname(final @PathVariable String nickname) {
        final UserDto foundUser = this.userService.findByNickname(nickname);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundUser);
    }

    /**
     * Represents the HTTP GET request method for finding a user by their name.
     *
     * @param name The name of the user to find, represented as {@link NameDto}.
     * @return If the user was successfully found, {@link ResponseEntity} with HTTP status 302 "Found" and the found
     * user as {@link UserDto} as the response body;<br>
     * otherwise, result depends on problem.</br>
     */
    @GetMapping("/find/name")
    public ResponseEntity<Set<UserDto>> findAllByName(final @RequestBody @Valid NameDto name) {
        final Set<UserDto> userDtos = this.userService.findAllByName(name);

        return ResponseEntity.status(HttpStatus.FOUND).body(userDtos);
    }

    // endregion

    // region POST mappings

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> save(final @RequestBody @Valid UserSaveDto signUpDto) {
        final UserDto savedUserDto = this.userService.save(signUpDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
    }

    // endregion

    // region PATCH mappings

    @PatchMapping("/update-email/{id}/{email}")
    public ResponseEntity<?> updateEmail(final @PathVariable @Positive Long id,
                                         final @PathVariable @Email String email) {
        this.userService.updateEmail(id, email);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PatchMapping("/update-password/{id}/{password}")
    public ResponseEntity<?> updatePassword(final @PathVariable @Positive Long id,
                                            final @PathVariable String password) {
        this.userService.updatePassword(id, password);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<UserDto> updateName(final @PathVariable @Positive Long id,
                                              final @RequestBody @Valid NameDto nameDto) {
        final UserDto updatedUserDto = this.userService.updateName(id, nameDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUserDto);
    }

    // endregion

    // region DELETE mappings

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(final @PathVariable @Positive Long id) {
        this.userService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // endregion
}
