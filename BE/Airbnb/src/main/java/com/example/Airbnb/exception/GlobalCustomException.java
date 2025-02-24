package com.example.Airbnb.exception;

import com.example.Airbnb.dto.response.BaseResponse;
import org.hibernate.TransientPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalCustomException {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<?> handleCustomException(CustomException e) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(e.getStatusCode());
        baseResponse.setContent(e.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(TransientPropertyValueException.class)
    public ResponseEntity<?> handleTransientPropertyValueException(TransientPropertyValueException e) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(500);
        baseResponse.setContent("data in valid");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(CustomFileNotFoundException.class)
    public ResponseEntity<?> handleCustomFileNotFound(Exception e) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setContent(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
