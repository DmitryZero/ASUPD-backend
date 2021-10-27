package com.bolsheviks.APMS.security;

import com.bolsheviks.APMS.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final SecurityFilterProperties securityFilterProperties;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (checkFreeAccessRegexes(request)) {
            return true;
        }
        return super.shouldNotFilter(request);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || userRepository.countUserById(UUID.fromString(header)) == 0) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Пошёл нахуй, паскуда!");
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkFreeAccessRegexes(HttpServletRequest request) {
        return Arrays.stream(securityFilterProperties.getFreeAccessRegexes())
                .anyMatch(s -> request.getRequestURI().matches(s));
    }
}
