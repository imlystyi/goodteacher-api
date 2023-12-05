package com.goodteacher.api.repository;

import com.goodteacher.api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to manage {@link Student} instances.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
            Optional<Student> findById(UUID id);

}
