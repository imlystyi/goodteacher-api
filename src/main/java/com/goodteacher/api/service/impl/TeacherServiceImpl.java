// BAD YET

package com.goodteacher.api.service.impl;

import com.goodteacher.api.dto.TeacherDto;
import com.goodteacher.api.dto.TeacherInfoDto;
import com.goodteacher.api.dto.UserDto;
import com.goodteacher.api.repository.TeacherRepository;
import com.goodteacher.api.repository.UserRepository;
import com.goodteacher.api.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repository;
    private final UserServiceImpl userService;

    @Override
    public TeacherDto create(final UUID userId) {
        userService.findById(userId);


    }

    @Override
    public TeacherDto updateInfo(final TeacherInfoDto dto) {
        return null;
    }
}
