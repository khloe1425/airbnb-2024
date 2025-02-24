package com.example.Airbnb.repository;

import com.example.Airbnb.entity.BinhLuanEntity;
import com.example.Airbnb.entity.PhongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BinhLuanRepository extends JpaRepository<BinhLuanEntity, Integer> {
    BinhLuanEntity findById(int id);

    List<BinhLuanEntity> findByMaPhong(PhongEntity maPhong);
}
