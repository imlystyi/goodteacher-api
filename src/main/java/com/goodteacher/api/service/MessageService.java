package com.goodteacher.api.service;

import com.goodteacher.api.dto.*;

import java.util.Set;

public interface MessageService {
    MessageDto findById(Long id);
    Set<MessageDto> findByUserIds(Long fromUserid, Long toUserId);

    MessageDto save(MessageSaveDto messageSaveDto);
}
