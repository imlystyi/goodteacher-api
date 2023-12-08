package com.goodteacher.api.service.impl;
import com.goodteacher.api.exception.NotFoundException;
import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.entity.Task;
import com.goodteacher.api.repository.TaskRepository;
import com.goodteacher.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    private Task findById(final Long id) {

        final Task taskEntity = this.findByIdStream(id);

        return AssignmentMapper.fromEntityToDto(assignmentEntity);
    }

    @Override
    public TaskDto save(TaskDto taskDTO) {
        final Task task = new Task();
/*        UUID uuid = UUID.randomUUID();
        task.setId(uuid);
        task.setName(taskDTO.getName());
        task.setText(taskDTO.getText());
        task.setQuiz(taskDTO.getQuiz());
        task.setCreationDate(taskDTO.getDate());

        taskRepository.save(task);*/

        return TaskDto.toDto(task);
    }

    @Override
    public TaskDto findDTOById(Long id) {
        final Task task = findById(id);

        return TaskDto.toDto(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

/*    @Override
    public List<TaskDto> findTasksByTeacherName(String teacherName) {
        Teacher teacher = teacherRepository.findByName(teacherName);
        if (teacher != null) {
            return taskRepository.findByTeacher(teacher);
        }
        return Collections.emptyList();
    }*/

    @Override
    public List<TaskDto> findTaskByName(String taskName) {
        return taskRepository.findByName(taskName);
    }

    private Task findByIdStream(final Long id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException(
                        "Task with id %d not found".formatted(id)));
    }
}
