package com.bolsheviks.APMS.domain.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    int countUserById(UUID id);
    Optional<User> findFirstByLoginAndPassword(String login, String password);
}