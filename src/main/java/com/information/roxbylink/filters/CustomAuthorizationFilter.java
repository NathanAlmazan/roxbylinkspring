package com.information.roxbylink.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.information.roxbylink.account.dto.AccountDto;
import com.information.roxbylink.errors.CustomErrorResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/login")) filterChain.doFilter(request, response);

        String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length());
            JwtAlgorithm jwtAlgorithm = new JwtAlgorithm();

            try {
                AccountDto account = jwtAlgorithm.verifyToken(token);

                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                account.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account.getUsername(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                filterChain.doFilter(request, response);
            } catch (Exception ex) {
                response.setStatus(FORBIDDEN.value());
                response.getWriter().write(jwtAlgorithm.customServerError(FORBIDDEN.value(), ex.getMessage()));
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
