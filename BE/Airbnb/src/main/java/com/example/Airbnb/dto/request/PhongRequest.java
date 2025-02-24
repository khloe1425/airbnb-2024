package com.example.Airbnb.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhongRequest {
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
    private Integer maViTri;
}
