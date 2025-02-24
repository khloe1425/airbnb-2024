package com.example.Airbnb.repository;

import com.example.Airbnb.entity.NguoiDungEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDungEntity, Integer> {
    NguoiDungEntity findByEmail(String email);

    NguoiDungEntity findById(int id);

    List<NguoiDungEntity> findByName(String name);

    @Query("SELECT u FROM NguoiDung u WHERE u.name LIKE %:keyword% OR u.email LIKE %:keyword%")
    Page<NguoiDungEntity> searchUsers(String keyword, Pageable pageable);
}
