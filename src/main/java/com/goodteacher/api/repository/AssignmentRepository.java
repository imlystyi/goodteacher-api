package com.goodteacher.api.repository;

import com.goodteacher.api.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Repository to manage {@link Assignment} entities.
 */
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findByIdAndIsActiveTrue(final Long id);

    Set<Assignment> findAllByTitleAndIsActiveTrue(String title);
}
