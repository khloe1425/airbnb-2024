package com.example.Airbnb.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity(name = "ViTri")
public class ViTriEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_vi_tri")
    private String tenViTri;
    @Column(name = "tinh_thanh")
    private String tinhThanh;
    @Column(name = "quoc_gia")
    private String quocGia;
    @Column(name = "hinh_anh")
    private String hinhAnh;
    @JsonManagedReference
    @OneToMany(mappedBy = "maViTri", orphanRemoval = true)
    private Set<PhongEntity> phong;
}
