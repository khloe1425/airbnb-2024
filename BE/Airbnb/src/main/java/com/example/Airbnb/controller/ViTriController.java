package com.example.Airbnb.controller;

import com.example.Airbnb.dto.request.ViTriRequest;
import com.example.Airbnb.dto.response.BaseResponse;
import com.example.Airbnb.service.imp.ViTriServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/vi-tri")
public class ViTriController {
    @Autowired
    ViTriServiceImp viTriServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(viTriServiceImp.getAllViTri());
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addViTri(@RequestBody ViTriRequest viTriRequest) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent("add viTri successful" + viTriServiceImp.addViTri(viTriRequest));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/phan-trang-tim-kiem")
    public ResponseEntity<?> getPage(@RequestParam(defaultValue = "0") int pageIndex,
                                     @RequestParam(defaultValue = "5") int pageSize,
                                     @RequestParam(defaultValue = "") String keyword) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(viTriServiceImp.searchViTri(pageIndex, pageSize, keyword));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(viTriServiceImp.getViTriById(id));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") int id, @RequestBody ViTriRequest viTriRequest) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(viTriServiceImp.updateById(id, viTriRequest));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(viTriServiceImp.deleteById(id));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/upload-hinh-vitri")
    public ResponseEntity<?> uploadHinhViTri(@RequestParam int maViTri, @RequestParam MultipartFile file) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(viTriServiceImp.uploadHinhViTri(maViTri, file));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}