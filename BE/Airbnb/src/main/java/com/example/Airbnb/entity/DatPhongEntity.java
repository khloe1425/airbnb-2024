package com.example.Airbnb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity(name = "DatPhong")
public class DatPhongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "so_luong_khach")
    private int soLuongKhach;
    @Column(name = "ngay_den")
    private Date ngayDen;
    @Column(name = "ngay_di")
    private Date ngayDi;
    @ManyToOne
    @JoinColumn(name = "ma_nguoi_dat")
    @JsonBackReference
    private NguoiDungEntity maNguoiDung;
    @ManyToOne
    @JoinColumn(name = "ma_phong")
    @JsonBackReference
    private PhongEntity maPhong;
}
