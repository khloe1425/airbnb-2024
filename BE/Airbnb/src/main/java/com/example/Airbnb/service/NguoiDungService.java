package com.example.Airbnb.service;

import com.example.Airbnb.Constant.Constant;
import com.example.Airbnb.dto.request.NguoiDungRequest;
import com.example.Airbnb.dto.request.SignupRequest;
import com.example.Airbnb.dto.response.NguoiDungResponse;
import com.example.Airbnb.entity.NguoiDungEntity;
import com.example.Airbnb.entity.RoleEntity;
import com.example.Airbnb.exception.CustomException;
import com.example.Airbnb.repository.NguoiDungRepository;
import com.example.Airbnb.repository.RoleRepository;
import com.example.Airbnb.service.imp.NguoiDungServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
public class NguoiDungService implements NguoiDungServiceImp {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<NguoiDungResponse> getAllNguoiDung() {
        try {
            List<NguoiDungResponse> listUser = new ArrayList<>();
            List<NguoiDungEntity> userEntityList = nguoiDungRepository.findAll();
            for (NguoiDungEntity item :
                    userEntityList) {
                NguoiDungResponse user = new NguoiDungResponse();
                user.setGender(item.isGender());
                user.setName(item.getName());
                user.setPhone(item.getPhone());
                user.setAvatar(item.getAvatar());
                user.setEmail(item.getEmail());
                user.setBirthDay(item.getBirthDay());
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            throw new CustomException("Lỗi get all user " + e.getMessage());
        }
    }

    @Override
    public boolean addUser(SignupRequest request) {
        boolean isSuccess = false;
        try {
            NguoiDungEntity nguoiDung = new NguoiDungEntity();
            nguoiDung.setName(request.getName());
            nguoiDung.setPassword(passwordEncoder.encode(request.getPassword()));
            nguoiDung.setEmail(request.getEmail());
            nguoiDung.setRole(new RoleEntity());
            nguoiDung.getRole().setId(2);
            nguoiDung.setBirthDay(request.getBirthDay());
            nguoiDung.setGender(request.isGender());
            nguoiDung.setPhone(request.getPhone());
            if (nguoiDungRepository.findByEmail(request.getEmail()) != null)
                throw new CustomException("Email đã tồn tại !");
            nguoiDungRepository.saveAndFlush(nguoiDung);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public NguoiDungResponse getNguoiDungById(int id) {
        try {
            NguoiDungEntity item = nguoiDungRepository.findById(id);
            NguoiDungResponse user = item.toDTO();
            return user;
        } catch (Exception e) {
            throw new CustomException("Lỗi get user by id " + e.getMessage());
        }
    }

    @Override
    public boolean updateUser(int userId, NguoiDungRequest request) {
        boolean isSuccess = false;
        try {
            NguoiDungEntity nguoiDung = nguoiDungRepository.findById(userId);
            nguoiDung.setName(request.getName());
            nguoiDung.setEmail(request.getEmail());
            nguoiDung.setBirthDay(request.getBirthDay());
            nguoiDung.setGender(request.isGender());
            nguoiDung.setPhone(request.getPhone());
            nguoiDung.setRole(roleRepository.findByName(request.getRole()));
            nguoiDungRepository.saveAndFlush(nguoiDung);
            isSuccess = true;
            return isSuccess;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean deleteUser(int userId) {
        boolean isSuccess = false;
        try {
            nguoiDungRepository.deleteById(userId);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException("Lỗi delete nguoi dung " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public List<NguoiDungResponse> getNguoiDungByTen(String name) {
        try {
            List<NguoiDungResponse> listUser = new ArrayList<>();
            List<NguoiDungEntity> userEntityList = nguoiDungRepository.findByName(name);
            for (NguoiDungEntity item :
                    userEntityList) {
                NguoiDungResponse user = new NguoiDungResponse();
                user.setGender(item.isGender());
                user.setName(item.getName());
                user.setPhone(item.getPhone());
                user.setAvatar(item.getAvatar());
                user.setEmail(item.getEmail());
                user.setBirthDay(item.getBirthDay());
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            throw new CustomException("Lỗi add user " + e.getMessage());
        }
    }

    @Override
    public boolean uploadAvatar(MultipartFile file) {
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
            return true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public Page<NguoiDungEntity> searchUsers(int pageIndex, int pageSize, String keyword) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        return nguoiDungRepository.searchUsers(keyword, pageRequest);
    }
}
