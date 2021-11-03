package com.bolsheviks.APMS.domain.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static com.bolsheviks.APMS.security.SecurityFilter.USER_UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserController(UserRepository userRepository,
                          UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @GetMapping("/self")
    public UserDto get(@RequestAttribute(USER_UUID) UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return userConverter.convertUserToUserDto(user);
    }

    @PostMapping("/self")
    public void post(@RequestAttribute(USER_UUID) UUID userId, @RequestBody UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        userConverter.fillUserByUserDto(user, userDto);
        userRepository.save(user);
    }
}
