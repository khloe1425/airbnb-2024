package com.example.Airbnb.controller;

import com.example.Airbnb.dto.request.BinhLuanRequest;
import com.example.Airbnb.dto.response.BaseResponse;
import com.example.Airbnb.service.imp.BinhLuanServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/binh-luan")
@CrossOrigin("*")
public class BinhLuanController {
    @Autowired
    BinhLuanServiceImp binhLuanServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllBinhLuan() {
        BaseResponse baseResponse = new BaseResponse();
        HashMap<String, Object> dataList = new HashMap<>();
        dataList.put("content", binhLuanServiceImp.getAllBinhLuan());
        baseResponse.setContent(dataList);
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody BinhLuanRequest binhLuanRequest) {
        BaseResponse baseResponse = new BaseResponse();
        binhLuanServiceImp.addBinhLuan(binhLuanRequest);
        baseResponse.setContent("Added Binh Luan");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int binhLuanId, @RequestBody BinhLuanRequest binhLuanRequest) {
        BaseResponse baseResponse = new BaseResponse();
        binhLuanServiceImp.updateBinhLuan(binhLuanId, binhLuanRequest);
        baseResponse.setContent("Updated Binh Luan");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int binhLuanId) {
        BaseResponse baseResponse = new BaseResponse();
        binhLuanServiceImp.deleteBinhLuan(binhLuanId);
        baseResponse.setContent("delete Binh Luan");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/lay-binh-luan-theo-phong/{MaPhong}")
    public ResponseEntity<?> getByMaPhong(@PathVariable("MaPhong") int phongId) {
        BaseResponse baseResponse = new BaseResponse();
        HashMap<String, Object> dataList = new HashMap<>();
        dataList.put("content", binhLuanServiceImp.getBinhLuanByMaPhong(phongId));
        baseResponse.setContent(dataList);
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
