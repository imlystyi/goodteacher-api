package com.goodteacher.api.resource;

import com.goodteacher.api.dto.AssignmentDto;
import com.goodteacher.api.dto.AssignmentSaveDto;
import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.dto.TaskSaveDto;
import com.goodteacher.api.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskResource {
    private final TaskService taskService;

    @GetMapping("/find/id/{id}")
    public ResponseEntity<TaskDto> findById(final @PathVariable @Positive Long id) {
        final TaskDto taskDto = this.taskService.findById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(taskDto);
    }
    @GetMapping("/find/name/{name}")
    public ResponseEntity<TaskDto> findByName(final @RequestBody String name){
        final TaskDto foundTask = this.taskService.findByName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(foundTask);
    }

    @GetMapping("/find/author-name/{authorname}")
    public ResponseEntity<TaskDto> findByAuthorName(final @RequestBody String authorname){
        final TaskDto foundTask = this.taskService.findByAuthorName(authorname);
        return ResponseEntity.status(HttpStatus.FOUND).body(foundTask);
    }

    @GetMapping("/find/alltasks")
    public ResponseEntity<List<TaskDto>> findAllByName(final @RequestBody String name){
        final List<TaskDto> foundTasks = this.taskService.findAllByName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(foundTasks);
    }

    @PostMapping("/save")
    public ResponseEntity<TaskDto> save(final @RequestBody @Valid TaskDto taskSaveDto) {
        final TaskDto taskDto = this.taskService.save(taskSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(final @PathVariable @Positive Long id) {
        this.taskService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
