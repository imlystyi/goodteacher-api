package com.goodteacher.api.resource;

import com.goodteacher.api.dto.TaskDTO;
import com.goodteacher.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskResource {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public TaskDTO findById(final @PathVariable UUID id){
        return taskService.findDTOById(id);
    }

    @PostMapping
    public TaskDTO createTask(final @RequestBody TaskDTO task){
        return taskService.save(task);
    }

    @PutMapping
    public TaskDTO updateTask(final @RequestBody TaskDTO task){
        return taskService.update(task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(final @PathVariable UUID id){
        taskService.deleteById(id);
    }
}
