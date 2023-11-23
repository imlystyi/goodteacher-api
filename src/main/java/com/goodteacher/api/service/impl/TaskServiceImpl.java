package com.goodteacher.api.service.impl;

import com.goodteacher.api.entity.Task;
import com.goodteacher.api.dto.TaskDTO;
import com.goodteacher.api.repository.TaskRepository;
import com.goodteacher.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    private Task findById(final UUID id) {
        return taskRepository.findById(id).orElseThrow(); //TODO: EXECPTION!!!
    }

    public TaskDTO findDTOById( UUID id) {
        final Task task = findById(id);

        return TaskDTO.toDTO(task);
    }

    public TaskDTO save(final TaskDTO taskDTO){
        final Task task = new Task();

        task.setText(taskDTO.getText());
        task.setQuiz(taskDTO.getQuiz());
        taskRepository.save(task);

        return TaskDTO.toDTO(task);
    }

    public TaskDTO update(final TaskDTO taskDTO){
        if (taskDTO.getId() == null){
            throw new RuntimeException(); //TODO: EXECPTION!!!
        }
        final Task savedTask = findById(taskDTO.getId());

        savedTask.setText(taskDTO.getText());
        savedTask.setQuiz(taskDTO.getQuiz());
        taskRepository.save(savedTask);

        return TaskDTO.toDTO(savedTask);
    }
    public void deleteById(final UUID id){
        taskRepository.deleteById(id);
    }
}
