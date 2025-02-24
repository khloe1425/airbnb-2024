package com.example.Airbnb.dto.request;

import com.example.Airbnb.entity.NguoiDungEntity;
import com.example.Airbnb.entity.PhongEntity;
import com.example.Airbnb.repository.PhongRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDateTime;

@ToString
@Data

public class DatPhongRequest {

    private int maPhong;
    private LocalDateTime ngayDen;
    private LocalDateTime ngayDi;
    private Integer soLuongKhach;
    private int maNguoiDung;

    public Date getNgayDen() {
        return Date.valueOf(ngayDen.toLocalDate());
    }

    public Date getNgayDi() {
        return Date.valueOf(ngayDi.toLocalDate());
    }
}
