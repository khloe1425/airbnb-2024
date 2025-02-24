package com.example.Airbnb.entity;

import com.example.Airbnb.dto.response.NguoiDungResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@Entity(name = "NguoiDung")
public class NguoiDungEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @JsonIgnore
    @Column(name = "pass_word")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "birth_day")
    private Date birthDay;
    @Column(name = "gender")
    private boolean gender;
    @Column(name = "avatar")
    private String avatar;
    @ManyToOne()
    @JoinColumn(name = "role_id")
    @JsonBackReference
    RoleEntity role;
    @JsonManagedReference
    @OneToMany(mappedBy = "maNguoiBinhLuan")
    Set<BinhLuanEntity> binhLuan;
    @JsonManagedReference
    @OneToMany(mappedBy = "maNguoiDung")
    Set<DatPhongEntity> datPhongEntity;

    // Chuyển đổi Entity sang DTO
    public NguoiDungResponse toDTO() {
        return new NguoiDungResponse(this.name, this.email, this.phone, this.birthDay, this.gender, this.avatar, this.role.getName());
    }
}
