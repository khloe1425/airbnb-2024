package com.example.Airbnb.controller;

import com.example.Airbnb.dto.request.PhongRequest;
import com.example.Airbnb.dto.response.BaseResponse;
import com.example.Airbnb.service.imp.PhongServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/phong-thue")
public class PhongController {
    @Autowired
    PhongServiceImp phongServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(phongServiceImp.getAllPhong());
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addPhong(@RequestBody PhongRequest phongRequest) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent("add phong successful" + phongServiceImp.addPhong(phongRequest));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/lay-phong-theo-vi-tri")
    public ResponseEntity<?> getByViTri(@RequestParam int maViTri) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(phongServiceImp.getPhongByViTri(maViTri));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/phan-trang-tim-kiem")
    public ResponseEntity<?> getPage(@RequestParam(defaultValue = "0") int pageIndex,
                                     @RequestParam(defaultValue = "5") int pageSize,
                                     @RequestParam(defaultValue = "") String keyword) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(phongServiceImp.searchPhong(pageIndex, pageSize, keyword));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(phongServiceImp.getPhongById(id));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") int id, @RequestBody PhongRequest phongRequest) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(phongServiceImp.updateById(id, phongRequest));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(phongServiceImp.deleteById(id));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/upload-hinh-phong")
    public ResponseEntity<?> uploadHinhPhong(@RequestParam int maPhong, @RequestParam MultipartFile file) {
        System.out.println(maPhong);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(phongServiceImp.uploadHinhPhong(maPhong, file));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
