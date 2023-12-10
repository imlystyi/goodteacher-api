package com.goodteacher.api.resource;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageResource {

    private final MessageService messageService;

    @GetMapping("/find/{messageId}")
    public ResponseEntity<MessageDto> findById(final @PathVariable Long messageId) {

        final MessageDto foundMessageDto = this.messageService.findById(messageId);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundMessageDto);
    }

    @GetMapping("/findAll/{userId}")
    public ResponseEntity<Set<MessageDto>> findByUserId(final @PathVariable Long userId) {

        final Set<MessageDto> foundMessageDto = this.messageService.findByUserId(userId);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundMessageDto);
    }

    @PostMapping("/send-message")
    public ResponseEntity<MessageDto> sendMessage(final @RequestBody MessageSaveDto messageSaveDto) {

        final MessageDto savedMessageDto = this.messageService.save(messageSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessageDto);
    }

}
