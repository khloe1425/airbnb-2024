package com.example.Airbnb.controller;

import com.example.Airbnb.dto.request.NguoiDungRequest;
import com.example.Airbnb.dto.request.SignupRequest;
import com.example.Airbnb.dto.response.BaseResponse;
import com.example.Airbnb.service.imp.NguoiDungServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class NguoiDungController {
    @Autowired
    NguoiDungServiceImp nguoiDungServiceImp;


    @GetMapping("")
    public ResponseEntity<?> getAll() {
        BaseResponse baseResponse = new BaseResponse();
        HashMap<String, Object> dataList = new HashMap<>();
        dataList.put("users:", nguoiDungServiceImp.getAllNguoiDung());
        baseResponse.setContent(dataList);
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody @Valid SignupRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        nguoiDungServiceImp.addUser(request);
        baseResponse.setContent("Added nguoi dung");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam int userId) {
        BaseResponse baseResponse = new BaseResponse();
        nguoiDungServiceImp.deleteUser(userId);
        baseResponse.setContent("delete nguoi dung");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/phan-trang-tim-kiem")
    public ResponseEntity<?> getByPhanTrang(
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "") String keyword) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(nguoiDungServiceImp.searchUsers(pageIndex, pageSize, keyword));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(nguoiDungServiceImp.getNguoiDungById(id));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") int id, @RequestBody NguoiDungRequest nguoiDungRequest) {
        BaseResponse baseResponse = new BaseResponse();
        nguoiDungServiceImp.updateUser(id, nguoiDungRequest);
        baseResponse.setContent("update nguoi dung thanh cong");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/upload-avatar")
    public ResponseEntity<?> uploadAvatar(@RequestParam MultipartFile file) {
        BaseResponse baseResponse = new BaseResponse();
        nguoiDungServiceImp.uploadAvatar(file);
        baseResponse.setContent("uploadAvatar thanh cong");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/search/{TenNguoiDung}")
    public ResponseEntity<?> getByName(@PathVariable("TenNguoiDung") String name) {
        BaseResponse baseResponse = new BaseResponse();
        HashMap<String, Object> dataList = new HashMap<>();
        dataList.put("users:", nguoiDungServiceImp.getNguoiDungByTen(name));
        baseResponse.setContent(dataList);
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
