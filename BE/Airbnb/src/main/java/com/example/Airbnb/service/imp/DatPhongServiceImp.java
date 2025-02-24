package com.example.Airbnb.service.imp;

import com.example.Airbnb.dto.request.DatPhongRequest;
import com.example.Airbnb.dto.response.DatPhongResponse;

import java.util.List;

public interface DatPhongServiceImp {
    List<DatPhongResponse> getAllDatPhong();

    boolean addDatPhong(DatPhongRequest datPhongRequest);

    DatPhongResponse getDatPhongById(int id);

    boolean updateDatPhong(int id, DatPhongRequest datPhongRequest);

    boolean deleteDatPhong(int id);

    List<DatPhongResponse> getDatPhongByUserId(int maNguoiDung);
}