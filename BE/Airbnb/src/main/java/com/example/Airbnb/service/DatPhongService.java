package com.example.Airbnb.service;

import com.example.Airbnb.dto.request.DatPhongRequest;
import com.example.Airbnb.dto.response.DatPhongResponse;
import com.example.Airbnb.entity.DatPhongEntity;
import com.example.Airbnb.exception.CustomException;
import com.example.Airbnb.repository.DatPhongRepository;
import com.example.Airbnb.repository.NguoiDungRepository;
import com.example.Airbnb.repository.PhongRepository;
import com.example.Airbnb.service.imp.DatPhongServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatPhongService implements DatPhongServiceImp {
    @Autowired
    private DatPhongRepository datPhongRepository;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    PhongRepository phongRepository;
    @Autowired
    ModelMapper modelMapper = new ModelMapper();


    // Hàm hỗ trợ chuyển đổi Date -> LocalDate
    private LocalDateTime convertToLocalDate(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Override
    public List<DatPhongResponse> getAllDatPhong() {
        try {
            List<DatPhongEntity> datPhongEntities = datPhongRepository.findAll();
//            List<DatPhongResponse> datPhongResponseList = new ArrayList<>();
            return datPhongEntities.stream()
                    .map(datPhongEntity -> modelMapper.map(datPhongEntity, DatPhongResponse.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Lỗi lấy danh sách đặt phòng: " + e.getMessage());
        }
    }


    @Override
    public boolean addDatPhong(DatPhongRequest datPhongRequest) {
        try {
            System.out.println(datPhongRequest.toString());
            DatPhongEntity entity = modelMapper.map(datPhongRequest, DatPhongEntity.class);
            entity.setMaPhong(phongRepository.findById(datPhongRequest.getMaPhong()));
            entity.setMaNguoiDung(nguoiDungRepository.findById(datPhongRequest.getMaNguoiDung()));
            datPhongRepository.saveAndFlush(entity);
            return true;
        } catch (Exception e) {
            throw new CustomException("Lỗi khi thêm đặt phòng: " + e.getMessage());
        }
    }

    @Override
    public DatPhongResponse getDatPhongById(int id) {
        try {
            DatPhongEntity entity = datPhongRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Không tìm thấy đặt phòng với ID: " + id));
            return modelMapper.map(entity, DatPhongResponse.class);
        } catch (Exception e) {
            throw new CustomException("Lỗi khi lấy đặt phòng theo ID: " + e.getMessage());
        }
    }

    @Override
    public boolean updateDatPhong(int id, DatPhongRequest datPhongRequest) {
        try {
            DatPhongEntity entity = datPhongRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Không tìm thấy đặt phòng để cập nhật"));
            modelMapper.map(datPhongRequest, entity);
            entity.setId(id);
            entity.setMaPhong(phongRepository.findById(datPhongRequest.getMaPhong()));
            entity.setMaNguoiDung(nguoiDungRepository.findById(datPhongRequest.getMaNguoiDung()));
            datPhongRepository.saveAndFlush(entity);
            return true;
        } catch (Exception e) {
            throw new CustomException("Lỗi khi cập nhật đặt phòng: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteDatPhong(int id) {
        try {
            datPhongRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new CustomException("Lỗi khi xóa đặt phòng: " + e.getMessage());
        }
    }

    @Override
    public List<DatPhongResponse> getDatPhongByUserId(int maNguoiDung) {
        try {
            List<DatPhongEntity> datPhongEntities = datPhongRepository.findByMaNguoiDung(nguoiDungRepository.findById(maNguoiDung));
            return datPhongEntities.stream()
                    .map(entity -> modelMapper.map(entity, DatPhongResponse.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Lỗi khi lấy đặt phòng theo người dùng: " + e.getMessage());
        }
    }
}