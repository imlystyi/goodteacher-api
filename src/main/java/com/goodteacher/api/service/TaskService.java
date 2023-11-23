package com.goodteacher.api.service;

import com.goodteacher.api.dto.TaskDTO;
import com.goodteacher.api.entity.Task;

import java.util.UUID;

public interface TaskService {

    TaskDTO findDTOById( UUID id);
    TaskDTO save(final TaskDTO taskDTO);
    TaskDTO update(final TaskDTO taskDTO);
    void deleteById(final UUID id);

}
