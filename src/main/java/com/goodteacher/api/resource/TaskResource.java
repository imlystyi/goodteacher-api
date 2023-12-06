package com.goodteacher.api.resource;

import com.goodteacher.api.dto.TaskDTO;
import com.goodteacher.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskResource {

    private  TaskService taskService;
    @GetMapping("/find/{id}")
    public TaskDTO findById(final @PathVariable UUID id){
        return taskService.findDTOById(id);
    }
    @PostMapping("/create")
    public TaskDTO createTask(final @RequestBody TaskDTO task){
        return taskService.save(task);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(final @PathVariable UUID id){
        taskService.deleteById(id);
    }

    @GetMapping("/find/{author}")
    public List<TaskDTO> getTasksByTeacherName(final @RequestParam String teacherName) {
        return taskService.findTasksByTeacherName(teacherName);
    }

    @GetMapping("/find/{taskName}")
    public List<TaskDTO> getTasksByName(final @RequestParam String taskName) {
        return taskService.findTaskByName(taskName);
    }
}