package com.goodteacher.api.repository;

import com.goodteacher.api.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository to manage {@link Teacher} instances.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    Teacher findByName(String teacherName);
}
