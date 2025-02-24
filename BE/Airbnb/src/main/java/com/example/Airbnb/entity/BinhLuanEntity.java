package com.example.Airbnb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity(name = "BinhLuan")
public class BinhLuanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ma_phong")
    private PhongEntity maPhong;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ma_nguoi_binh_luan")
    private NguoiDungEntity maNguoiBinhLuan;
    @Column(name = "noi_dung")
    private String noiDung;
    @Column(name = "sao_binh_luan")
    private int saoBinhLuan;
    @Column(name = "ngay_binh_luan")
    private Date ngayBinhLuan;
}
