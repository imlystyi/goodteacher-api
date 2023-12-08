package com.goodteacher.api.resource;

import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskResource {

    private  TaskService taskService;
    @GetMapping("/find/id/{id}")
    public TaskDto findById(final @PathVariable Long id){

        return taskService.findDTOById(id);
    }
    @PostMapping("/create")
    public TaskDto createTask(final @RequestBody TaskDto task){
        return taskService.save(task);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(final @PathVariable Long id){
        taskService.deleteById(id);
    }

/*    @GetMapping("/find/{author}")
    public List<TaskDto> getTasksByTeacherName(final @RequestParam String teacherName) {
        return taskService.findTasksByTeacherName(teacherName);
    }

    @GetMapping("/find/{taskName}")
    public List<TaskDto> getTasksByName(final @RequestParam String taskName) {
        return taskService.findTaskByName(taskName);
    }*/
}