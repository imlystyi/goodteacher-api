package com.goodteacher.api.service.impl;

import com.goodteacher.api.repository.StudentRepository;
import com.goodteacher.api.repository.TeacherRepository;
import com.goodteacher.api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public boolean anyByEmail(final String email) {
        return this.studentRepository.existsByEmail(email) || this.teacherRepository.existsByEmail(email);
    }

    @Override
    public boolean anyByNickname(final String nickname) {
        return this.studentRepository.existsByNickname(nickname) || this.teacherRepository.existsByNickname(nickname);
    }
}
