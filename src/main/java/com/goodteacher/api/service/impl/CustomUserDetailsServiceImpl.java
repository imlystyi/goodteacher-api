package com.goodteacher.api.service.impl;

import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.repository.StudentRepository;
import com.goodteacher.api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> studentOptional = studentRepository.findByNicknameAndIsActiveTrue(username);
        if (studentOptional.isPresent()) {
            return studentOptional.get();
        }

        Optional<Teacher> teacherOptional = teacherRepository.findByNicknameAndIsActiveTrue(username);
        if (teacherOptional.isPresent()) {
            return teacherOptional.get();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
