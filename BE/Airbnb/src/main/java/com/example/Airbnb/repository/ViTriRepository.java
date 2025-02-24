package com.example.Airbnb.repository;

import com.example.Airbnb.entity.ViTriEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ViTriRepository extends JpaRepository<ViTriEntity, Integer> {
    ViTriEntity findById(int id);

    @Query("SELECT u FROM ViTri u WHERE u.tenViTri LIKE %:keyword%")
    Page<ViTriEntity> searchViTri(String keyword, Pageable pageable);
}