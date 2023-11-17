package com.goodteacher.api.repository;

import com.goodteacher.api.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private List<Task> tasksList = new ArrayList<>();

}
