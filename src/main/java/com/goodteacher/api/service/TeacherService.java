package com.goodteacher.api.service;

import com.goodteacher.api.dto.TeacherDto;
import com.goodteacher.api.dto.TeacherInfoDto;

import java.util.UUID;

public interface TeacherService {
    TeacherDto create(final UUID dto);

    TeacherDto updateInfo(final TeacherInfoDto dto);
}
