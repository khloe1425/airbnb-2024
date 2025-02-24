package com.example.Airbnb.service;

import com.example.Airbnb.Constant.Constant;
import com.example.Airbnb.dto.request.ViTriRequest;
import com.example.Airbnb.dto.response.ViTriResponse;
import com.example.Airbnb.entity.ViTriEntity;
import com.example.Airbnb.exception.CustomException;
import com.example.Airbnb.repository.ViTriRepository;
import com.example.Airbnb.service.imp.ViTriServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ViTriService implements ViTriServiceImp {
    @Autowired
    ViTriRepository viTriRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ViTriResponse> getAllViTri() {
        try {
            List<ViTriEntity> viTriEntities = viTriRepository.findAll();
            return viTriEntities.stream()
                    .map(viTriEntity -> modelMapper.map(viTriEntity, ViTriResponse.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Lỗi get all viTri " + e.getMessage());
        }
    }

    @Override
    public boolean addViTri(ViTriRequest viTriRequest) {
        try {
            ViTriEntity viTriEntity = modelMapper.map(viTriRequest, ViTriEntity.class);
            viTriRepository.saveAndFlush(viTriEntity);
            return true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public Page<ViTriEntity> searchViTri(int pageIndex, int pageSize, String keyword) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        return viTriRepository.searchViTri(keyword, pageRequest);
    }

    @Override
    public ViTriResponse getViTriById(int id) {
        try {
            ViTriEntity viTriEntity = viTriRepository.findById(id);
            return modelMapper.map(viTriEntity, ViTriResponse.class);
        } catch (Exception e) {
            throw new CustomException("Lỗi get viTri by id " + e.getMessage());
        }
    }

    @Override
    public boolean updateById(int id, ViTriRequest viTriRequest) {
        try {
            ViTriEntity viTriEntity = viTriRepository.findById(id);
            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(viTriRequest, viTriEntity);
            viTriEntity.setId(id);
            viTriRepository.saveAndFlush(viTriEntity);
            return true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            viTriRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean uploadHinhViTri(int maViTri, MultipartFile file) {
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

            ViTriEntity viTriEntity = viTriRepository.findById(maViTri);
            viTriEntity.setHinhAnh(fileName);
            viTriRepository.saveAndFlush(viTriEntity);

            return true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}