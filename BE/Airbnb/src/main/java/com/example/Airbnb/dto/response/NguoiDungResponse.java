package com.example.Airbnb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDungResponse {
    private String name;
    private String email;
    private String phone;
    private Date birthDay;
    private boolean gender;
    private String avatar;
    private String roleName;
}
