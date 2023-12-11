package com.goodteacher.api.resource;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageResource {
    private final MessageService messageService;

    @GetMapping("/find/id/{id}")
    public ResponseEntity<MessageDto> findById(final @PathVariable Long id) {
        final MessageDto foundMessageDto = this.messageService.findById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundMessageDto);
    }

    @GetMapping("/find/user-ids/{fromUserId}/{toUserId}")
    public ResponseEntity<Set<MessageDto>> findByUserIds(final @PathVariable Long fromUserId,
                                                         final @PathVariable Long toUserId) {
        final Set<MessageDto> foundMessageDto = this.messageService.findByUserIds(fromUserId, toUserId);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundMessageDto);
    }

    @PostMapping("/send")
    public ResponseEntity<MessageDto> sendMessage(final @RequestBody MessageSaveDto messageSaveDto) {

        final MessageDto savedMessageDto = this.messageService.save(messageSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessageDto);
    }
}
