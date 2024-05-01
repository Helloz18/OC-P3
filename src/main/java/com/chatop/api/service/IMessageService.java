package com.chatop.api.service;

import com.chatop.api.dto.MessageDTO;
import com.chatop.api.model.Message;

public interface IMessageService {

    Message createMessage(MessageDTO messageDTO) throws Exception;
}
