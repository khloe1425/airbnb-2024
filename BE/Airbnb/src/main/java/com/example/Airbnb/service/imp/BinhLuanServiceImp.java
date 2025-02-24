package com.example.Airbnb.service.imp;

import com.example.Airbnb.dto.request.BinhLuanRequest;
import com.example.Airbnb.dto.response.BinhLuanResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BinhLuanServiceImp {
    List<BinhLuanResponse> getAllBinhLuan();

    boolean addBinhLuan(BinhLuanRequest binhLuanRequest);

    boolean updateBinhLuan(int binhLuanId, BinhLuanRequest binhLuanRequest);

    boolean deleteBinhLuan(int binhLuanId);

    List<BinhLuanResponse> getBinhLuanByMaPhong(int phongId);

}
