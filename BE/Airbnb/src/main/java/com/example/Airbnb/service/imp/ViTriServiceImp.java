package com.example.Airbnb.service.imp;

import com.example.Airbnb.dto.request.ViTriRequest;
import com.example.Airbnb.dto.response.ViTriResponse;
import com.example.Airbnb.entity.ViTriEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ViTriServiceImp {
    List<ViTriResponse> getAllViTri();

    boolean addViTri(ViTriRequest viTriRequest);

    Page<ViTriEntity> searchViTri(int pageIndex, int pageSize, String keyword);

    ViTriResponse getViTriById(int id);

    boolean updateById(int id, ViTriRequest viTriRequest);

    boolean deleteById(int id);

    boolean uploadHinhViTri(int maViTri, MultipartFile file);
}