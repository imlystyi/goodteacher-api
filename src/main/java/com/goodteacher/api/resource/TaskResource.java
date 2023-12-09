package com.goodteacher.api.resource;

import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.dto.TaskSaveDto;
import com.goodteacher.api.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskResource {
    private final TaskService taskService;

    @GetMapping("/find/id/{id}")
    public ResponseEntity<TaskDto> findById(final @PathVariable Long id) {
        final TaskDto foundTaskDto = this.taskService.findById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundTaskDto);
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<Set<TaskDto>> findAllByName(final @PathVariable String name) {
        final Set<TaskDto> foundTaskDtos = this.taskService.findAllByName(name);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundTaskDtos);
    }

    @GetMapping("/find/author-name/{authorName}")
    public ResponseEntity<Set<TaskDto>> findAllByAuthorName(final @PathVariable String authorName) {
        final Set<TaskDto> foundTaskDtos = this.taskService.findAllByAuthorName(authorName);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundTaskDtos);
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> save(final @RequestBody @Valid TaskSaveDto taskSaveDto) {
        final TaskDto savedTaskDto = this.taskService.save(taskSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTaskDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(final @PathVariable Long id) {
        this.taskService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
