package com.chatop.api.service;

import com.chatop.api.model.User;

public interface IUserService {

    User createUser(User user) throws Exception;

    User getUserById(int id) throws Exception;

    User getUserByEmail(String email);

}
