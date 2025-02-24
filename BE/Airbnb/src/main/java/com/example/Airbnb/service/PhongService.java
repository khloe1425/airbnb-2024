package com.example.Airbnb.service;

import com.example.Airbnb.Constant.Constant;
import com.example.Airbnb.dto.request.PhongRequest;
import com.example.Airbnb.dto.response.PhongResponse;
import com.example.Airbnb.entity.PhongEntity;
import com.example.Airbnb.exception.CustomException;
import com.example.Airbnb.repository.PhongRepository;
import com.example.Airbnb.repository.ViTriRepository;
import com.example.Airbnb.service.imp.PhongServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PhongService implements PhongServiceImp {
    @Autowired
    PhongRepository phongRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ViTriRepository viTriRepository;

    @Override
    public List<PhongResponse> getAllPhong() {
        try {
            List<PhongEntity> phongEntities = phongRepository.findAll();
            return phongEntities.stream()
                    .map(phongEntity -> modelMapper.map(phongEntity, PhongResponse.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Lỗi get all phong " + e.getMessage());
        }
    }

    @Override
    public boolean addPhong(PhongRequest phongRequest) {
        boolean isSuccess = false;
        try {
            PhongEntity phongEntity = modelMapper.map(phongRequest, PhongEntity.class);
            System.out.println(phongEntity.toString());
            phongRepository.saveAndFlush(phongEntity);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public List<PhongResponse> getPhongByViTri(int viTriId) {
        try {
            List<PhongEntity> phongEntities = phongRepository.findByMaViTri(viTriRepository.findById(viTriId));
            return phongEntities.stream()
                    .map(phongEntity -> modelMapper.map(phongEntity, PhongResponse.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Lỗi get phong by vitri " + e.getMessage());
        }
    }

    @Override
    public Page<PhongEntity> searchPhong(int pageIndex, int pageSize, String keyword) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        return phongRepository.searchPhong(keyword, pageRequest);
    }

    @Override
    public PhongResponse getPhongById(int id) {
        try {
            PhongEntity phongEntity = phongRepository.findById(id);
            return modelMapper.map(phongEntity, PhongResponse.class);
        } catch (Exception e) {
            throw new CustomException("Lỗi get phong by id " + e.getMessage());
        }
    }

    @Override
    public boolean updateById(int id, PhongRequest phongRequest) {
        System.out.println(phongRequest.toString());
        boolean isSuccess = false;
        try {
            PhongEntity phongEntity = phongRepository.findById(id);
            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(phongRequest, phongEntity);
            phongEntity.setId(id);
            phongRepository.saveAndFlush(phongEntity);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean deleteById(int id) {
        boolean isSuccess = false;
        try {
            phongRepository.deleteById(id);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean uploadHinhPhong(int maPhong, MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        try {
            if (file.isEmpty()) {
                throw new CustomException("FileNotFound");
            }
            File uploadDir = new File(Constant.UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = uploadDir.getAbsolutePath() + File.separator + fileName;
            file.transferTo(new File(filePath));
            PhongEntity phongEntity = phongRepository.findById(maPhong);
            phongEntity.setHinhAnh(fileName);
            phongRepository.saveAndFlush(phongEntity);
            return true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
