package com.chatop.api.service;

import com.chatop.api.dto.MessageDTO;
import com.chatop.api.dto.ModelConverter;
import com.chatop.api.model.Message;
import com.chatop.api.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message createMessage(MessageDTO messageDTO) throws Exception {
        Message message = ModelConverter.toMessageCreate(messageDTO);
        return messageRepository.save(message);
    }
}
