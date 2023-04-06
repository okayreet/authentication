package com.catalogue.authentication.config;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.catalogue.authentication.Login;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
                password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain,
            Authentication authentication) throws IOException, ServletException {
        Login user = (Login) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + (10 * 60 * 1000) * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("role", user.getRole())
                .sign(algorithm);

        /*
         * //
         * // String refresh_token = JWT.create()
         * // .withSubject(user.getUsername())
         * // .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
         * // .withIssuer(request.getRequestURL().toString())
         * // .sign(algorithm);
         * response.setHeader("access_token", access_token);
         * response.setHeader("refresh_token", refresh_token);
         */
        Map<String, String> tokens = new HashMap<>();
        // tokens.put("email", user.getUsername());
        tokens.put("access_token", access_token);
        // tokens.put("refresh_token", refresh_token);//will come back to it later
        tokens.put("role", user.getRole().get(0));
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> tokens = new HashMap<>();
        tokens.put("error", "Wrong email or password");
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}