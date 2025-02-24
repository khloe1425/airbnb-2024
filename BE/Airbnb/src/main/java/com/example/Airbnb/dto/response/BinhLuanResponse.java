package com.example.Airbnb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BinhLuanResponse {
    private int id;
    private int maPhong;
    private int maNguoiBinhLuan;
    private String ngayBinhLuan;
    private String noiDung;
    private int saoBinhLuan;

}
