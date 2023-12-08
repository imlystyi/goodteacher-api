package com.goodteacher.api.repository;

import com.goodteacher.api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository to manage {@link Task} instances.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByName(final String name);
    Optional<Task> findByAuthorName(final String authorName);

    List<Task> findAllByName(final String name);

}
