package com.goodteacher.api.repository;

import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to manage {@link Teacher} instances.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

     Optional<Teacher> findById(UUID id);

}
