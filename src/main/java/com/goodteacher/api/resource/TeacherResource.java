package com.goodteacher.api.resource;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.service.TeacherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherResource {
    private final TeacherService teacherService;

    @GetMapping("/find/id/{id}")
    public ResponseEntity<TeacherDto> findById(final @PathVariable Long id) {
        final TeacherDto foundTeacherDto = this.teacherService.findById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundTeacherDto);
    }

    @GetMapping("/find/nickname/{nickname}")
    public ResponseEntity<TeacherDto> findByNickname(final @PathVariable String nickname) {
        final TeacherDto foundTeacherDto = this.teacherService.findByNickname(nickname);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundTeacherDto);
    }

    @GetMapping("/find/name")
    public ResponseEntity<Set<TeacherDto>> findAllByName(final @RequestBody @Valid NameDto nameDto) {
        final Set<TeacherDto> foundTeacherDtos = this.teacherService.findAllByName(nameDto);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundTeacherDtos);
    }

    @PostMapping("/create")
    public ResponseEntity<TeacherDto> save(final @RequestBody @Valid UserDto userDto) {
        final TeacherDto savedTeacherDto = this.teacherService.save(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacherDto);
    }

    @PatchMapping("/update-email/{id}/{email}")
    public ResponseEntity<Void> updateEmail(final @PathVariable Long id,
                                            final @PathVariable @Email String email) {
        this.teacherService.updateEmail(id, email);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/update-password/{id}/{password}")
    public ResponseEntity<Void> updatePassword(final @PathVariable Long id,
                                               final @PathVariable String password) {
        this.teacherService.updatePassword(id, password);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<TeacherDto> updateName(final @PathVariable Long id,
                                                 final @RequestBody @Valid NameDto nameDto) {
        final TeacherDto updatedTeacherDto = this.teacherService.updateName(id, nameDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedTeacherDto);
    }

    @PatchMapping("/update-birth-date/{id}/{birthDate}")
    public ResponseEntity<TeacherDto> updateBirthDate(final @PathVariable Long id,
                                                      final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                      Date birthDate) {
        final LocalDate parsedBirthDate = LocalDate.ofInstant(birthDate.toInstant(), ZoneId.systemDefault());

        final TeacherDto updatedTeacherDto = this.teacherService.updateBirthDate(id, parsedBirthDate);

        return ResponseEntity.status(HttpStatus.OK).body(updatedTeacherDto);
    }

    @PutMapping("/update-info")
    public ResponseEntity<TeacherDto> updateInfo(final @RequestBody TeacherInfoDto teacherInfoDto) {
        final TeacherDto updatedTeacherDto = this.teacherService.updateInfo(teacherInfoDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedTeacherDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(final @PathVariable Long id) {
        this.teacherService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
