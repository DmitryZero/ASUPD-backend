package com.bolsheviks.APMS.domain.auth;

import com.bolsheviks.APMS.domain.User.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @GetMapping("/sign_in")
    public String authentication(@RequestHeader("login") String login,
                                 @RequestHeader("password") String password) {
        User user = userRepository.findFirstByLoginAndPassword(login, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return user.getId().toString();
    }

    @PostMapping("/sign_up")
    public String registration(@RequestHeader("login") String login,
                               @RequestHeader("password") String password,
                               @RequestBody UserDto userDto) {
//        Я отъебал Антона TODO: навестить маму Егора
        User newUser = new User(login, password, Role.USER);
        userConverter.fillUserByUserDto(newUser, userDto);
        try {
            userRepository.save(newUser);
        } catch (Throwable t) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return newUser.getId().toString();
    }
}
