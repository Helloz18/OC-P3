package com.chatop.api.service;

import com.chatop.api.dto.DtoConverter;
import com.chatop.api.dto.ModelConverter;
import com.chatop.api.dto.UserDTO;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        User user = ModelConverter.toUserCreate(userDTO);
        // the email must be unique for each user
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new Exception("The email provided may be registered already: " + userDTO.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    @Override
    public UserDTO getUserById(int id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() ->
                new Exception("No user registered with this id : " + id));
        return DtoConverter.toUserDTO(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
