package com.chatop.api.repository;

import com.chatop.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(final int id);


    Optional<User> findByEmail(final String email);



}
