package com.example.Airbnb.service.imp;

import com.example.Airbnb.dto.request.PhongRequest;
import com.example.Airbnb.dto.response.PhongResponse;
import com.example.Airbnb.entity.PhongEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface PhongServiceImp {
    List<PhongResponse> getAllPhong();

    boolean addPhong(PhongRequest phongRequest);

    List<PhongResponse> getPhongByViTri(int viTriId);

    Page<PhongEntity> searchPhong(int pageIndex, int pageSize, String keyword);

    PhongResponse getPhongById(int id);

    boolean updateById(int id, PhongRequest phongRequest);

    boolean deleteById(int id);

    boolean uploadHinhPhong(int maPhong, MultipartFile file);
}
