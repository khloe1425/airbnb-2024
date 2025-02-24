package com.example.Airbnb.repository;

import com.example.Airbnb.entity.DatPhongEntity;
import com.example.Airbnb.entity.NguoiDungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatPhongRepository extends JpaRepository<DatPhongEntity, Integer> {
    List<DatPhongEntity> findByMaNguoiDung(NguoiDungEntity maNguoiDung);
}