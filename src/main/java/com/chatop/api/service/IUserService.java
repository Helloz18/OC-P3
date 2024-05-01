package com.chatop.api.service;

import com.chatop.api.dto.UserDTO;
import com.chatop.api.model.User;

public interface IUserService {

    User createUser(UserDTO userDTO) throws Exception;

    User getUserById(int id) throws Exception;

    User getUserByEmail(String email) throws Exception;

}
