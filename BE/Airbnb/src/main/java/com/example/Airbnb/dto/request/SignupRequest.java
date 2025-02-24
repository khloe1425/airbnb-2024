package com.example.Airbnb.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 8)
    private String password;
    @NotNull(message = "Email not null")
    @NotEmpty(message = "Email not empty")
    @Email(message = "Email invalid format")
    private String email;
    private String phone;
    private String birthDay;
    private boolean gender;
    private String role;

    public Date getBirthDay() {
        LocalDate localDate = LocalDate.parse(this.birthDay, DateTimeFormatter.ISO_LOCAL_DATE);
        Date sqlDate = Date.valueOf(localDate);
        return sqlDate;
    }
}

