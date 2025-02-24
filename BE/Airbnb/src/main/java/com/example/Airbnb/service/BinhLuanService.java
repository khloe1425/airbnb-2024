package com.example.Airbnb.service;

import com.example.Airbnb.dto.request.BinhLuanRequest;
import com.example.Airbnb.dto.response.BinhLuanResponse;
import com.example.Airbnb.entity.BinhLuanEntity;
import com.example.Airbnb.exception.CustomException;
import com.example.Airbnb.repository.BinhLuanRepository;
import com.example.Airbnb.repository.NguoiDungRepository;
import com.example.Airbnb.repository.PhongRepository;
import com.example.Airbnb.service.imp.BinhLuanServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinhLuanService implements BinhLuanServiceImp {
    @Autowired
    BinhLuanRepository binhLuanRepository;
    @Autowired
    PhongRepository phongRepository;
    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Override
    public List<BinhLuanResponse> getAllBinhLuan() {
        try {
            List<BinhLuanResponse> binhLuanResponseList = new ArrayList<>();
            List<BinhLuanEntity> binhLuanEntityList = binhLuanRepository.findAll();
            for (BinhLuanEntity binhLuan :
                    binhLuanEntityList) {
                BinhLuanResponse binhLuanResponse = new BinhLuanResponse();
                binhLuanResponse.setId(binhLuan.getId());
                binhLuanResponse.setMaPhong(binhLuan.getMaPhong().getId());
                binhLuanResponse.setMaNguoiBinhLuan(binhLuan.getMaNguoiBinhLuan().getId());
                binhLuanResponse.setNgayBinhLuan(binhLuan.getNgayBinhLuan().toString());
                binhLuanResponse.setNoiDung(binhLuan.getNoiDung());
                binhLuanResponse.setSaoBinhLuan(binhLuan.getSaoBinhLuan());
                binhLuanResponseList.add(binhLuanResponse);
            }
            return binhLuanResponseList;
        } catch (Exception e) {
            throw new CustomException("Lỗi get all BinhLuan " + e.getMessage());
        }
    }

    @Override
    public boolean addBinhLuan(BinhLuanRequest binhLuanRequest) {
        try {
            boolean isSuccess = false;
            BinhLuanEntity binhLuan = new BinhLuanEntity();
            binhLuan.setMaPhong(phongRepository.findById(binhLuanRequest.getMaPhong()));
            binhLuan.setMaNguoiBinhLuan(nguoiDungRepository.findById(binhLuanRequest.getMaNguoiBinhLuan()));
            binhLuan.setNgayBinhLuan(binhLuanRequest.getNgayBinhLuan());
            binhLuan.setNoiDung(binhLuanRequest.getNoiDung());
            binhLuan.setSaoBinhLuan(binhLuanRequest.getSaoBinhLuan());
            binhLuanRepository.saveAndFlush(binhLuan);
            isSuccess = true;
            return isSuccess;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean updateBinhLuan(int binhLuanId, BinhLuanRequest binhLuanRequest) {
        try {
            boolean isSuccess = false;
            BinhLuanEntity binhLuan = binhLuanRepository.findById(binhLuanId);
            binhLuan.setMaPhong(phongRepository.findById(binhLuanRequest.getMaPhong()));
            binhLuan.setMaNguoiBinhLuan(nguoiDungRepository.findById(binhLuanRequest.getMaNguoiBinhLuan()));
            binhLuan.setNgayBinhLuan(binhLuanRequest.getNgayBinhLuan());
            binhLuan.setNoiDung(binhLuanRequest.getNoiDung());
            binhLuan.setSaoBinhLuan(binhLuanRequest.getSaoBinhLuan());
            binhLuanRepository.saveAndFlush(binhLuan);
            isSuccess = true;
            return isSuccess;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean deleteBinhLuan(int binhLuanId) {
        boolean isSuccess = false;
        try {
            binhLuanRepository.deleteById(binhLuanId);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException("Lỗi delete binh luan " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public List<BinhLuanResponse> getBinhLuanByMaPhong(int phongId) {
        try {
            List<BinhLuanResponse> binhLuanResponseList = new ArrayList<>();
            List<BinhLuanEntity> binhLuanEntityList = binhLuanRepository.findByMaPhong(phongRepository.findById(phongId));
            for (BinhLuanEntity binhLuan :
                    binhLuanEntityList) {
                BinhLuanResponse binhLuanResponse = new BinhLuanResponse();
                binhLuanResponse.setId(binhLuan.getId());
                binhLuanResponse.setMaPhong(binhLuan.getMaPhong().getId());
                binhLuanResponse.setMaNguoiBinhLuan(binhLuan.getMaNguoiBinhLuan().getId());
                binhLuanResponse.setNgayBinhLuan(binhLuan.getNgayBinhLuan().toString());
                binhLuanResponse.setNoiDung(binhLuan.getNoiDung());
                binhLuanResponse.setSaoBinhLuan(binhLuan.getSaoBinhLuan());
                binhLuanResponseList.add(binhLuanResponse);
            }
            return binhLuanResponseList;
        } catch (Exception e) {
            throw new CustomException("Lỗi get BinhLuan theo maPhong " + e.getMessage());
        }
    }
}
