package com.example.Airbnb.config;

import com.example.Airbnb.dto.response.BaseResponse;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class AuthenticationEntryPointConfig implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        BaseResponse baseResponse = new BaseResponse();
        if (authException instanceof InsufficientAuthenticationException) {
            baseResponse.setStatusCode(401);
            baseResponse.setContent("Unauthorized");
        } else {
            baseResponse.setStatusCode(403);
            baseResponse.setContent("Access denied.");
        }
        response.setContentType("application/json");
        response.setStatus(HttpStatus.OK.value());

        response.getWriter().write(new Gson().toJson(baseResponse));
    }
}
