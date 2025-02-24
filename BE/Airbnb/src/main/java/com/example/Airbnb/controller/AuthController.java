package com.example.Airbnb.controller;

import com.example.Airbnb.dto.request.SignupRequest;
import com.example.Airbnb.dto.response.BaseResponse;
import com.example.Airbnb.exception.CustomException;
import com.example.Airbnb.service.imp.NguoiDungServiceImp;
import com.example.Airbnb.utils.JwtHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    NguoiDungServiceImp nguoiDungServiceImp;
    @Autowired
    JwtHelper jwtHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String email, @RequestParam String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(token);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        HashMap<String, Object> dataList = new HashMap<>();
        dataList.put("token", jwtHelper.generateToken(email));
        response.setContent(dataList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request, BindingResult result) {
        List<FieldError> list = result.getFieldErrors();
        for (FieldError data :
                list) {
            throw new CustomException(data.getDefaultMessage(), 500);
        }
        BaseResponse response = new BaseResponse();
        nguoiDungServiceImp.addUser(request);
        response.setStatusCode(200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
