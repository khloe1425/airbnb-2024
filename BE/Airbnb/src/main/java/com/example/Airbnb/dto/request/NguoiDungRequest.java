package com.example.Airbnb.dto.request;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDungRequest {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String birthday;
    private boolean gender;
    private String role;

    public Date getBirthDay() {
        LocalDate localDate = LocalDate.parse(this.birthday, DateTimeFormatter.ISO_LOCAL_DATE);
        Date sqlDate = Date.valueOf(localDate);
        return sqlDate;
    }
}
