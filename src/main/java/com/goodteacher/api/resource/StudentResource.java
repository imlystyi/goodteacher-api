package com.goodteacher.api.resource;

import com.goodteacher.api.dto.NameDto;
import com.goodteacher.api.dto.StudentDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.service.StudentService;
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
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentResource {
    private final StudentService studentService;

    @GetMapping("/find/id/{id}")
    public ResponseEntity<StudentDto> findById(final @PathVariable Long id) {
        final StudentDto foundStudentDto = this.studentService.findById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundStudentDto);
    }

    @GetMapping("/find/nickname/{nickname}")
    public ResponseEntity<StudentDto> findByNickname(final @PathVariable String nickname) {
        final StudentDto foundStudentDto = this.studentService.findByNickname(nickname);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundStudentDto);
    }

    @GetMapping("/find/name")
    public ResponseEntity<Set<StudentDto>> findAllByName(final @RequestBody @Valid NameDto nameDto) {
        final Set<StudentDto> foundStudentDtos = this.studentService.findAllByName(nameDto);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundStudentDtos);
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDto> save(final @RequestBody @Valid UserDto userDto) {
        final StudentDto foundStudentDto = this.studentService.save(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(foundStudentDto);
    }

    @PatchMapping("/update-email/{id}/{email}")
    public ResponseEntity<Void> updateEmail(final @PathVariable Long id,
                                            final @PathVariable @Email String email) {
        this.studentService.updateEmail(id, email);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/update-password/{id}/{password}")
    public ResponseEntity<Void> updatePassword(final @PathVariable Long id,
                                               final @PathVariable String password) {
        this.studentService.updatePassword(id, password);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/update-birth-date/{id}/{birthDate}")
    public ResponseEntity<StudentDto> updateBirthDate(final @PathVariable Long id,
                                                      final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                      Date birthDate) {
        final LocalDate parsedBirthDate = LocalDate.ofInstant(birthDate.toInstant(), ZoneId.systemDefault());

        final StudentDto updatedStudentDto = this.studentService.updateBirthDate(id, parsedBirthDate);

        return ResponseEntity.status(HttpStatus.OK).body(updatedStudentDto);
    }

    @PutMapping("/update-name/{id}")
    public ResponseEntity<StudentDto> updateName(final @PathVariable Long id,
                                                 final @RequestBody @Valid NameDto nameDto) {
        final StudentDto updatedStudentDto = this.studentService.updateName(id, nameDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedStudentDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(final @PathVariable Long id) {
        this.studentService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
