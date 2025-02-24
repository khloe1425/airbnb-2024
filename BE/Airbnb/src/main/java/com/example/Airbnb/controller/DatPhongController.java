package com.example.Airbnb.controller;

import com.example.Airbnb.dto.request.DatPhongRequest;
import com.example.Airbnb.dto.response.BaseResponse;
import com.example.Airbnb.service.imp.DatPhongServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dat-phong")
public class DatPhongController {
    @Autowired
    private DatPhongServiceImp datPhongServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(datPhongServiceImp.getAllDatPhong());
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addDatPhong(@RequestBody DatPhongRequest datPhongRequest) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent("Đặt phòng thành công: " + datPhongServiceImp.addDatPhong(datPhongRequest));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(datPhongServiceImp.getDatPhongById(id));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") int id, @RequestBody DatPhongRequest datPhongRequest) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(datPhongServiceImp.updateDatPhong(id, datPhongRequest));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(datPhongServiceImp.deleteDatPhong(id));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/lay-theo-nguoi-dung/{maNguoiDung}")
    public ResponseEntity<?> getByUserId(@PathVariable("maNguoiDung") int maNguoiDung) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(datPhongServiceImp.getDatPhongByUserId(maNguoiDung));
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}