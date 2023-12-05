package com.goodteacher.api.resource;

import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.dto.UserSignUpDto;
import com.goodteacher.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {
    private final UserService service;

    // region GET mappings

    @GetMapping("/find/nickname/{nickname}")
    public UserDto findByNickname(@PathVariable final String nickname) {
        return this.service.findByNickname(nickname);
    }

    @GetMapping("/find/name/{name}")
    public UserDto findByName(@PathVariable final String name) {
        final String[] parts = name.split(" ");

        // TODO-1: Provide custom exception
        if (parts.length != 3) {
            throw new IllegalArgumentException("Name must contain 3 parts: first name, last name and patronymic");
        } else {
            return this.service.findByName(parts[0], parts[1], parts[2]);
        }
    }

    // endregion

    // region POST mappings

    @PostMapping("/sign-up")
    public UserDto signUp(@RequestBody final UserSignUpDto signUpDto) {
        return this.service.signUp(signUpDto);
    }

    // endregion

    // region PUT mappings

    @PutMapping("/update-info")
    public UserDto updateInfo(@RequestBody final UserDto dto) {
        return this.service.updateInfo(dto);
    }

    // endregion

    // region PATCH mappings

    @PatchMapping("/update-email/{id}/{email}")
    public void updateEmail(@PathVariable final String id, @PathVariable final String email) {
        this.service.updateEmail(UUID.fromString(id), email);
    }

    @PatchMapping("/update-password/{id}/{password}")
    public void updatePassword(@PathVariable final String id, @PathVariable final String password) {
        this.service.updatePassword(UUID.fromString(id), password);
    }

    // endregion

    // region DELETE mappings

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable final String id) {
        this.service.deleteById(UUID.fromString(id));
    }

    // endregion
}
