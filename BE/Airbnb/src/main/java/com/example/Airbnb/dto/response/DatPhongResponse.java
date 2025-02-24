package com.example.Airbnb.dto.response;

import com.example.Airbnb.entity.NguoiDungEntity;
import com.example.Airbnb.entity.PhongEntity;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class DatPhongResponse {
    private Integer id;
    private PhongEntity maPhong;
    private Date ngayDen;
    private Date ngayDi;
    private Integer soLuongKhach;
    private NguoiDungEntity maNguoiDung;
}