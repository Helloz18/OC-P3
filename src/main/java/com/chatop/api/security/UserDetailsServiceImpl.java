package com.chatop.api.security;
import com.chatop.api.model.LoginRequest;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class used to create a UserDetails needed for authentication and authorization purpose.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Method called by Spring Security to get UserDetails for authentication.
     * @param email
     * @return a UserDetails object to authenticate the user.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return new LoginRequest(user.getEmail(), user.getPassword());
    }
}

