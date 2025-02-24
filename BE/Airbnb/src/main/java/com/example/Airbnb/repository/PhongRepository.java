package com.example.Airbnb.repository;

import com.example.Airbnb.entity.PhongEntity;
import com.example.Airbnb.entity.ViTriEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhongRepository extends JpaRepository<PhongEntity, Integer> {
    PhongEntity findById(int id);

    List<PhongEntity> findByMaViTri(ViTriEntity maViTri);

    @Query("SELECT u FROM Phong u WHERE u.name LIKE %:keyword%")
    Page<PhongEntity> searchPhong(String keyword, Pageable pageable);
}
