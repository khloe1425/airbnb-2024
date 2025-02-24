package com.example.Airbnb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@Entity(name = "Phong")
@ToString
public class PhongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_phong")
    private String name;
    @Column(name = "khach")
    private int khach;
    @Column(name = "phong_ngu")
    private int phongNgu;
    @Column(name = "giuong")
    private int giuong;
    @Column(name = "phong_tam")
    private int phongTam;
    @Column(name = "mo_ta")
    private String moTa;
    @Column(name = "gia_tien")
    private int giaTien;
    @Column(name = "may_giat")
    private boolean mayGiat;
    @Column(name = "ban_la")
    private boolean banLa;
    @Column(name = "tivi")
    private boolean tivi;
    @Column(name = "dieu_hoa")
    private boolean dieuHoa;
    @Column(name = "wifi")
    private boolean wifi;
    @Column(name = "bep")
    private boolean bep;
    @Column(name = "do_xe")
    private boolean doXe;
    @Column(name = "ho_boi")
    private boolean hoBoi;
    @Column(name = "ban_ui")
    private boolean banUi;
    @Column(name = "hinh_anh")
    private String hinhAnh;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ma_vi_tri")
    private ViTriEntity maViTri;
    @OneToMany(mappedBy = "maPhong")
    @JsonManagedReference
    Set<BinhLuanEntity> binhLuan;
    @OneToMany(mappedBy = "maPhong")
    @JsonManagedReference
    Set<DatPhongEntity> datPhongEntity;


}
