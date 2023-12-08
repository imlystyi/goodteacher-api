package com.goodteacher.api.service;

import com.goodteacher.api.dto.AssignmentDto;
import com.goodteacher.api.dto.AssignmentSaveDto;
import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.dto.TaskSaveDto;
import com.goodteacher.api.entity.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface TaskService {

    TaskDto findById(Long id);

    TaskDto findByName(final String name);

    TaskDto findByAuthorName(final String authorName);

    List<TaskDto> findAllByName(final String name);

    TaskDto save(TaskDto taskDto);

    void delete(Long id);
}
