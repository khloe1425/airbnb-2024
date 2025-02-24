package com.example.Airbnb.filter;

import com.example.Airbnb.dto.response.BaseResponse;
import com.example.Airbnb.repository.NguoiDungRepository;
import com.example.Airbnb.utils.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class JwtAndCybersoftFilter extends OncePerRequestFilter {
    @Value("${cybersoft.token}")
    private String cybersoftToken;
    private static final String[] PUBLIC_ENDPOINTS = {
            "/api/auth/signin", "/api/auth/signup"
    };
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return Arrays.stream(PUBLIC_ENDPOINTS).anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException {
        try {
            String token = getTokenFromHeader(request);
            String tokenCybersoft = getTokenCyberSoftFromHeader(request);
            if (tokenCybersoft == null) {
                returnBaseResponseEntity(response, "no cybersoft token", 401);
                return;
            }
            if (tokenCybersoft.equalsIgnoreCase(cybersoftToken)) {
                returnBaseResponseEntity(response, "cybersoftToken fall", 401);
                return;
            }
            if (token != null && !token.isEmpty()) {
                String email = null;
                email = jwtHelper.decodeToken(token).getSubject();
                if (email != null) {
                    SecurityContext context = SecurityContextHolder.getContext();
                    UsernamePasswordAuthenticationToken user =
                            new UsernamePasswordAuthenticationToken(email, "", new ArrayList<>());
                    context.setAuthentication(user);
                }
            }
        } catch (Exception e) {
            returnBaseResponseEntity(response, e.getMessage(), 500);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    private String getTokenCyberSoftFromHeader(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("tokenCybersoft");
        if (header != null) {
            return header;
        }
        return null;
    }

    private void returnBaseResponseEntity(HttpServletResponse response, String message, int statusCode) throws IOException {
        BaseResponse baseResponse = new BaseResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        baseResponse.setContent(message);
        baseResponse.setStatusCode(statusCode);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), baseResponse);
    }
}
