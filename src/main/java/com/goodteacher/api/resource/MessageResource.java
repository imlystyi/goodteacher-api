package com.goodteacher.api.resource;

import com.goodteacher.api.dto.*;
import com.goodteacher.api.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * The REST controller to handle the requests to the message resource.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessageResource {
    // region Fields

    private final MessageService messageService;

    // endregion

    // region GET mappings

    /**
     * Represents the HTTP GET request method for finding a message by its ID.
     *
     * @param id ID to search by as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the found message as {@link MessageDto} in the response body if the message was successfully found; otherwise, result depends on problem.
     */
    @GetMapping("/find/id/{id}")
    public ResponseEntity<MessageDto> findById(final @PathVariable Long id) {
        final MessageDto foundMessageDto = messageService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(foundMessageDto);
    }

    /**
     * Represents the HTTP GET request method for finding messages by their senders' ID.
     *
     * @param fromUserId sender's ID to search by as {@link Long}. Must be contained in the path as the path variable.
     * @param toUserId  receiver's ID to search by as {@link Long}. Must be contained in the path as the path variable.
     * @return {@link ResponseEntity} with HTTP status 200 "OK" and the found messages as {@link Set}{@code <}{@link MessageDto}{@code >} in the response body if any message was successfully found; otherwise, result depends on problem.
     */
    @GetMapping("/find/user-ids/{fromUserId}/{toUserId}")
    public ResponseEntity<Set<MessageDto>> findByUserIds(final @PathVariable Long fromUserId,
                                                         final @PathVariable Long toUserId) {
        final Set<MessageDto> foundMessageDto = messageService.findByUserIds(fromUserId, toUserId);

        return ResponseEntity.status(HttpStatus.OK).body(foundMessageDto);
    }

    // endregion

    // region POST mappings

    /**
     * Represents the HTTP POST request method for sending a message.
     *
     * @param messageSaveDto message to send as {@link MessageSaveDto}. Must be contained in the request body and be successfully validated.
     * @return {@link ResponseEntity} with HTTP status 201 "Created" and the saved message as {@link MessageDto} in the response body if the message was successfully sent; otherwise, result depends on problem.
     */
    @PostMapping("/send")
    public ResponseEntity<MessageDto> sendMessage(final @RequestBody @Valid MessageSaveDto messageSaveDto) {

        final MessageDto savedMessageDto = messageService.save(messageSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessageDto);
    }

    // endregion
}
