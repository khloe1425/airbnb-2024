DROP DATABASE IF EXISTS airbnb;

CREATE DATABASE airbnb;
USE airbnb;

CREATE TABLE role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE nguoi_dung (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    pass_word VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    birth_day DATE,
    avatar text,
    gender BOOLEAN,
    role_id INT
);

CREATE TABLE vi_tri (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_vi_tri VARCHAR(255) NOT NULL,
    tinh_thanh VARCHAR(255),
    quoc_gia VARCHAR(255),
    hinh_anh VARCHAR(255)
);

CREATE TABLE phong (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_phong VARCHAR(255) NOT NULL,
    khach INT NOT NULL,
    phong_ngu INT,
    giuong INT,
    phong_tam INT,
    mo_ta TEXT,
    gia_tien INT,
    may_giat BOOLEAN,
    ban_la BOOLEAN,
    tivi BOOLEAN,
    dieu_hoa BOOLEAN,
    wifi BOOLEAN,
    bep BOOLEAN,
    do_xe BOOLEAN,
    ho_boi BOOLEAN,
    ban_ui BOOLEAN,
    hinh_anh VARCHAR(255),
    ma_vi_tri INT
);

CREATE TABLE dat_phong (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ma_phong INT,
    ngay_den DATETIME NOT NULL,
    ngay_di DATETIME NOT NULL,
    so_luong_khach INT NOT NULL,
    ma_nguoi_dat INT
);

CREATE TABLE binh_luan (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ma_phong INT,
    ma_nguoi_binh_luan INT,
    ngay_binh_luan DATETIME NOT NULL,
    noi_dung TEXT,
    sao_binh_luan INT
);

-- Khóa ngoại giữa nguoi_dung và role
ALTER TABLE nguoi_dung
ADD CONSTRAINT FK_role_id FOREIGN KEY (role_id) REFERENCES role(id);

-- Khóa ngoại giữa phong và vi_tri
ALTER TABLE phong
ADD CONSTRAINT FK_phong_vi_tri FOREIGN KEY (ma_vi_tri) REFERENCES vi_tri(id);

-- Khóa ngoại giữa dat_phong và phong
ALTER TABLE dat_phong
ADD CONSTRAINT FK_dat_phong_phong FOREIGN KEY (ma_phong) REFERENCES phong(id);

-- Khóa ngoại giữa dat_phong và nguoi_dung
ALTER TABLE dat_phong
ADD CONSTRAINT FK_dat_phong_nguoi_dung FOREIGN KEY (ma_nguoi_dat) REFERENCES nguoi_dung(id);

-- Khóa ngoại giữa binh_luan và nguoi_dung
ALTER TABLE binh_luan
ADD CONSTRAINT FK_binh_luan_nguoi_dung FOREIGN KEY (ma_nguoi_binh_luan) REFERENCES nguoi_dung(id);

-- Khóa ngoại giữa binh_luan và phong
ALTER TABLE binh_luan
ADD CONSTRAINT FK_binh_luan_phong FOREIGN KEY (ma_phong) REFERENCES phong(id);

INSERT INTO role (id, role_name) VALUES
(1, 'admin'),
(2, 'user');

--  Auto-generated SQL script. Actual values for binary/complex data types may differ - what you see is the default string representation of values.
INSERT INTO airbnb.phong (ten_phong,khach,phong_ngu,giuong,phong_tam,mo_ta,gia_tien,may_giat,ban_la,tivi,dieu_hoa,wifi,bep,do_xe,ho_boi,ban_ui,hinh_anh)
	VALUES ('abc',1,1,1,1,'acs',10000,1,1,1,1,1,1,1,1,1,'');
--  Auto-generated SQL script. Actual values for binary/complex data types may differ - what you see is the default string representation of values.
INSERT INTO airbnb.phong (ten_phong,khach,phong_ngu,giuong,phong_tam,mo_ta,gia_tien,may_giat,ban_la,tivi,dieu_hoa,wifi,bep,do_xe,ho_boi,ban_ui,hinh_anh)
	VALUES ('ass',2,1,1,1,'1',11111,0,1,1,1,1,1,1,1,1,'');
--  Auto-generated SQL script. Actual values for binary/complex data types may differ - what you see is the default string representation of values.
INSERT INTO airbnb.binh_luan (ma_phong,ma_nguoi_binh_luan,ngay_binh_luan,noi_dung,sao_binh_luan)
	VALUES (2,1,'2022-1-1','4',5)
,(1,1,'2022-1-1','4',5);

