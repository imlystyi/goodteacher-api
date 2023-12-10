package com.goodteacher.api.service;

import com.goodteacher.api.dto.*;

import java.util.Set;

public interface MessageService {
    MessageDto save(MessageSaveDto messageSaveDto);
    MessageDto findById(Long id);
    Set<MessageDto> findByUserId(Long id);
}
