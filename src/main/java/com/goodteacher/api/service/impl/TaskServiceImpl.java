package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.TaskDTO;
import com.goodteacher.api.entity.Task;
import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.repository.TaskRepository;
import com.goodteacher.api.repository.TeacherRepository;
import com.goodteacher.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private TeacherRepository teacherRepository;

    private Task findById(final UUID id) {
        return taskRepository.findById(id).orElseThrow(); //TODO: EXECPTION!!!
    }

    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        final Task task = new Task();
        UUID uuid = UUID.randomUUID();
        task.setId(uuid);
        task.setName(taskDTO.getName());
        task.setText(taskDTO.getText());
        task.setQuiz(taskDTO.getQuiz());
        task.setDate(taskDTO.getDate());

        taskRepository.save(task);

        return TaskDTO.toDTO(task);
    }

    @Override
    public TaskDTO findDTOById(UUID id) {
        final Task task = findById(id);

        return TaskDTO.toDTO(task);
    }

    @Override
    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDTO> findTasksByTeacherName(String teacherName) {
        Teacher teacher = teacherRepository.findByName(teacherName);
        if (teacher != null) {
            return taskRepository.findByTeacher(teacher);
        }
        return Collections.emptyList();
    }

    @Override
    public List<TaskDTO> findTaskByName(String taskName) {
        return taskRepository.findByName(taskName);
    }
}
