package com.bolsheviks.APMS.domain.auth;

import com.bolsheviks.APMS.domain.User.User;
import com.bolsheviks.APMS.domain.User.Role;
import com.bolsheviks.APMS.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @GetMapping("/sing_in")
    public String authentication(@RequestHeader("login") String login,
                                 @RequestHeader("password") String password) {
        User user = userRepository.findFirstByLoginAndPassword(login, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return user.getId().toString();
    }

    @PostMapping("/sing_up")
    public String registration(@RequestHeader("login") String login,
                               @RequestHeader("password") String password) {
//        Я отъебал Антона
        User newUser = new User(login, password, Role.USER);
        userRepository.save(newUser);
        return newUser.getId().toString();
    }
}
