package com.example.Airbnb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SigninResponse {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String birthday;
    private String avatar;
    private boolean gender;
    private String role;
}
