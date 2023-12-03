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

    private TaskRepository taskRepository;



    private Task findById(final UUID id) {
        return taskRepository.findById(id).orElseThrow(); //TODO: EXECPTION!!!
    }
    @Override
    public TaskDTO findDTOById(UUID id) {
        final Task task = findById(id);

        return TaskDTO.toDTO(task);
    }

    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        final Task task = new Task();

        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setText(taskDTO.getText());
        task.setQuiz(taskDTO.getQuiz());
        taskRepository.save(task);

        return TaskDTO.toDTO(task);
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO) {
        final Task task = new Task();

        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setText(taskDTO.getText());
        task.setQuiz(taskDTO.getQuiz());
        taskRepository.save(task);

        return TaskDTO.toDTO(task);
    }

    @Override
    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }
}
