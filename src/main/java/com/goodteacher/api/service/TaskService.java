package com.goodteacher.api.service;

import com.goodteacher.api.dto.TaskDTO;

public interface TaskService {
    TaskDTO save(final TaskDTO taskDTO);
    TaskDTO update(final TaskDTO taskDTO);
}
