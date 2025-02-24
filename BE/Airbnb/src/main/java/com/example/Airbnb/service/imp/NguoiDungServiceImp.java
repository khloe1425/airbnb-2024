package com.example.Airbnb.service.imp;

import com.example.Airbnb.dto.request.NguoiDungRequest;
import com.example.Airbnb.dto.response.NguoiDungResponse;
import com.example.Airbnb.dto.request.SignupRequest;
import com.example.Airbnb.dto.response.SigninResponse;
import com.example.Airbnb.entity.NguoiDungEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface NguoiDungServiceImp {
    List<NguoiDungResponse> getAllNguoiDung();

    boolean addUser(SignupRequest request);

    //    boolean addUser(NguoiDungRequest request);
    NguoiDungResponse getNguoiDungById(int id);

    boolean updateUser(int userId, NguoiDungRequest nguoiDungRequest);

    boolean deleteUser(int userId);

    List<NguoiDungResponse> getNguoiDungByTen(String tenNguoiDung);

    boolean uploadAvatar(MultipartFile file);

    public Page<NguoiDungEntity> searchUsers(int pageIndex, int pageSize, String keyword);

}
