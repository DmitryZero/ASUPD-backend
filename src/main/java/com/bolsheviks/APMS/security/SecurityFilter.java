package com.bolsheviks.APMS.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

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
        if (header != null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Авторизация ещё не подключена :)");
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkFreeAccessRegexes(HttpServletRequest request) {
        return Arrays.stream(securityFilterProperties.getFreeAccessRegexes())
                .anyMatch(s -> request.getRequestURI().matches(s));
    }
}
