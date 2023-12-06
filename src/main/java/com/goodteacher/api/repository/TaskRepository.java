package com.goodteacher.api.repository;

import com.goodteacher.api.dto.TaskDTO;
import com.goodteacher.api.entity.Task;
import com.goodteacher.api.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository to manage {@link Task} instances.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<TaskDTO> findByTeacher(Teacher teacher);
    List<TaskDTO> findByName(String taskName);

}
