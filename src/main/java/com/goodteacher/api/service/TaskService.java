package com.goodteacher.api.service;

import com.goodteacher.api.dto.TaskDTO;

public interface TaskService {
    TaskDTO save(TaskDTO taskDTO);
    TaskDTO update(TaskDTO taskDTO);
}
