package com.example.Airbnb.dto.request;

import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Getter
public class BinhLuanRequest {
    private int id;
    private int maPhong;
    private int maNguoiBinhLuan;
    private String ngayBinhLuan;
    private String noiDung;
    private int saoBinhLuan;

    public Date getNgayBinhLuan() {
        LocalDate localDate = LocalDate.parse(ngayBinhLuan.substring(0, 10), DateTimeFormatter.ISO_LOCAL_DATE);
        // Chuyển LocalDate sang java.sql.Date
        Date sqlDate = Date.valueOf(localDate);
        return sqlDate;
    }
}
