package com.goodteacher.api.resource;

import com.goodteacher.api.dto.GroupDto;
import com.goodteacher.api.dto.GroupSaveDto;
import com.goodteacher.api.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The REST controller to handle the requests to the group resource.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups")
public class GroupResource {
    // region Fields

    private final GroupService groupService;

    // endregion

    // region POST mappings

    /**
     * Represents the HTTP POST request method for saving a group.
     *
     * @param groupSaveDto group to save as {@link GroupSaveDto}. Must be contained in the request body and be successfully validated.
     * @return {@link ResponseEntity} with HTTP status 201 "Created" and the saved group as {@link GroupSaveDto} in the response body if the group was successfully saved; otherwise, result depends on problem.
     */
    @PostMapping("/create")
    public ResponseEntity<GroupDto> create(final @RequestBody @Valid GroupSaveDto groupSaveDto) {
        final GroupDto savedGroupDto = groupService.save(groupSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroupDto);

    }

    // endregion

    // region PATCH mappings

    /**
     * Represents the HTTP PATCH request method for updating name of the group.
     *
     * @param id   group ID as {@link Long}. Must be contained in the path as the path variable.
     * @param name new name as {@link String}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated group as {@link GroupDto} in the response body if the name was successfully updated; otherwise, result depends on problem.
     */
    @PatchMapping("/update/name/{id}/{name}")
    public ResponseEntity<GroupDto> updateName(final @PathVariable Long id, final @PathVariable String name) {
        final GroupDto updatedGroupDto = groupService.updateName(id, name);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGroupDto);
    }

    /**
     * Represents the HTTP PATCH request method for updating about of the group.
     *
     * @param id    group ID as {@link Long}. Must be contained in the path as the path variable.
     * @param about new about as {@link String}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated group as {@link GroupDto} in the response body if the about was successfully updated; otherwise, result depends on problem.
     */
    @PatchMapping("/update/about/{id}/{about}")
    public ResponseEntity<GroupDto> updateAbout(final @PathVariable Long id,
                                                final @PathVariable String about) {
        final GroupDto updatedGroupDto = groupService.updateAbout(id, about);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGroupDto);
    }

    /**
     * Represents the HTTP PATCH request method for updating teacher of the group.
     *
     * @param groupId   group ID as {@link Long}. Must be contained in the path as the path variable.
     * @param teacherId teacher ID as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated group as {@link GroupDto} in the response body if the teacher was successfully updated; otherwise, result depends on problem.
     */
    @PatchMapping("/update/teacher/{groupId}/{teacherId}")
    public ResponseEntity<GroupDto> updateTeacher(final @PathVariable Long groupId,
                                                  final @PathVariable Long teacherId) {
        final GroupDto updatedGroupDto = groupService.updateTeacher(groupId, teacherId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGroupDto);
    }

    /**
     * Represents the HTTP PATCH request method for adding student to the group.
     *
     * @param groupId   group ID as {@link Long}. Must be contained in the path as the path variable.
     * @param studentId student ID as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated group as {@link GroupDto} in the response body if the student was successfully added; otherwise, result depends on problem.
     */
    @PatchMapping("/update/add-student/{groupId}/{studentId}")
    public ResponseEntity<GroupDto> addStudent(final @PathVariable Long groupId,
                                               final @PathVariable Long studentId) {
        final GroupDto updatedGroupDto = groupService.addStudent(groupId, studentId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGroupDto);
    }

    /**
     * Represents the HTTP PATCH request method for removing student from the group.
     *
     * @param groupId   group ID as {@link Long}. Must be contained in the path as the path variable.
     * @param studentId student ID as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the updated group as {@link GroupDto} in the response body if the student was successfully removed; otherwise, result depends on problem.
     */
    @PatchMapping("/update/remove-student/{groupId}/{studentId}")
    public ResponseEntity<GroupDto> removeStudent(final @PathVariable Long groupId,
                                                  final @PathVariable Long studentId) {
        final GroupDto updatedGroupDto = groupService.removeStudent(groupId, studentId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGroupDto);
    }

    // endregion

    // region DELETE mappings

    /**
     * Represents the HTTP DELETE request method for deleting a group softly.
     *
     * @param id group ID as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 204 "No Content" if the student was successfully deleted; otherwise, result depends on problem.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(final @PathVariable Long id) {
        groupService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // endregion
}
