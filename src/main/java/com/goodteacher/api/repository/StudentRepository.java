package com.goodteacher.api.repository;

import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

}
