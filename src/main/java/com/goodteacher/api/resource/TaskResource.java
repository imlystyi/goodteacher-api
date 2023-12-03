package com.goodteacher.api.resource;

import com.goodteacher.api.dto.TaskDTO;
import com.goodteacher.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskResource {
    private final TaskService taskService;

    @GetMapping("/find/{id}")
    public TaskDTO findById(final @PathVariable UUID id){
        return taskService.findDTOById(id);
    }

    @PostMapping("/create")
    public TaskDTO createTask(final @RequestBody TaskDTO task){
        return taskService.save(task);
    }

    @PutMapping("/edit")
    public TaskDTO updateTask(final @RequestBody TaskDTO task){
        return taskService.update(task);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(final @PathVariable UUID id){
        taskService.deleteById(id);
    }
}
