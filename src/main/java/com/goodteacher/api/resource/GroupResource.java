package com.goodteacher.api.resource;

import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.dto.GroupSaveDto;
import com.goodteacher.api.dto.StudentDto;
import com.goodteacher.api.dto.TeacherDto;
import com.goodteacher.api.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupResource {
    private final GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<GroupDto> save(final @RequestBody @Valid GroupSaveDto groupSaveDto) {
        final GroupDto savedGroupDto = this.groupService.save(groupSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroupDto);

    }

    @PatchMapping("/update-name/{id}/{name}")
    public ResponseEntity<GroupDto> updateName(final @PathVariable Long id, final @PathVariable String name) {
        final GroupDto updatedGroupDto = this.groupService.updateName(id, name);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGroupDto);
    }

    @PatchMapping("/update-teacher/{id}")
    public ResponseEntity<GroupDto> updateTeacher(final @PathVariable Long id,
                                                  final @RequestBody @Valid TeacherDto teacherDto) {
        final GroupDto updatedGroupDto = this.groupService.updateTeacher(id, teacherDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGroupDto);
    }

    @PatchMapping("/update-about/{id}/{about}")
    public ResponseEntity<GroupDto> updateAbout(final @PathVariable Long id,
                                                final @PathVariable String about) {
        final GroupDto updatedGroupDto = this.groupService.updateAbout(id, about);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGroupDto);
    }

    @PatchMapping("/add-student/{groupId}")
    public ResponseEntity<GroupDto> addStudent(final @PathVariable Long groupId,
                                               final @RequestBody @Valid StudentDto student) {
        final GroupDto updatedGroupDto = this.groupService.addStudent(groupId, student);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGroupDto);
    }

    @PatchMapping("/delete-student/{groupId}")
    public ResponseEntity<GroupDto> deleteStudent(final @PathVariable Long groupId,
                                                  final @RequestBody @Valid StudentDto student) {
        final GroupDto updatedGroupDto = this.groupService.deleteStudent(groupId, student);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGroupDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(final @PathVariable Long id) {
        this.groupService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
