package com.example.Airbnb.dto.response;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhongResponse {
    private int id;
    private String name;
    private int khach;
    private int phongNgu;
    private int giuong;
    private int phongTam;
    private String moTa;
    private int giaTien;
    private boolean mayGiat;
    private boolean banLa;
    private boolean tivi;
    private boolean dieuHoa;
    private boolean wifi;
    private boolean bep;
    private boolean doXe;
    private boolean hoBoi;
    private boolean banUi;
    private String hinhAnh;
    private Integer maViTri; // chỉ lưu ID của ViTriEntity, không lưu cả object
}
