package com.chatop.api.controller;

import com.chatop.api.dto.MessageDTO;
import com.chatop.api.model.ResponseMessage;
import com.chatop.api.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Message controller", description = "Controller used to manage messages. A connected user can send a message about a Rental.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("")
    @Operation(summary = "Send a message to a User about a Rental.",
            description = "Three fields are needed, the message, the id of the user and the id of the rental.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message send with success",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "400", description = "")
    })
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO) {
        if(messageDTO.getMessage() == null || messageDTO.getUserId() == 0 || messageDTO.getRentalId() == 0
        || messageDTO.getMessage().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        try {
            messageService.createMessage(messageDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok().body(new ResponseMessage("Message send with success"));

    }
}
