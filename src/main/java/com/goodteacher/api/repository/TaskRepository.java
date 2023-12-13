package com.goodteacher.api.repository;

import com.goodteacher.api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Repository to manage {@link Task} entities.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByIdAndIsActiveTrue(Long id);

    Set<Task> findAllByNameAndIsActiveTrue(String name);

    Set<Task> findAllByAuthorNameAndIsActiveTrue(String authorName);
}
