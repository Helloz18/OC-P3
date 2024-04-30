package com.chatop.api.controller;

import com.chatop.api.dto.MessageDTO;
import com.chatop.api.model.ResponseMessage;
import com.chatop.api.service.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO) {
        if(messageDTO.getMessage() == null || messageDTO.getUserId() == 0 || messageDTO.getRentalId() == 0
        || messageDTO.getMessage().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        try {
            messageService.createMessage(messageDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
        return ResponseEntity.ok().body(new ResponseMessage("Message send with success"));

    }
}
