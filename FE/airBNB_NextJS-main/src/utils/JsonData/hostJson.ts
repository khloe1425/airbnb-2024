export interface Host {
    maChuPhong: string
    avatar: string;
    tenChuPhong: string;
    soDT: string;
    email: string;
    gioiTinh: boolean;
    ngaySinh: string;
    moTa: string;
    status: "ACTIVE" | "INACTIVE";
  }

export const host: Host[] = [
    {
        maChuPhong: "CP1",
        avatar: "",
        tenChuPhong: "Nguyễn Văn A",
        soDT: "0901234567",
        email: "ngvana@gmail.com",
        gioiTinh: true,
        ngaySinh: "12/08/2000",
        moTa: "Chủ phòng siêu cấp",
        status: "ACTIVE",
    },
    {
        maChuPhong: "CP2",
        avatar: "",
        tenChuPhong: "Trần Thị B",
        soDT: "0937654321",
        email: "tranthib@gmail.com",
        gioiTinh: true,
        ngaySinh: "12/08/2000",
        moTa: "Chủ phòng siêu cấp",
        status: "INACTIVE",
    },
    {
        maChuPhong: "CP3",
        avatar: "",
        tenChuPhong: "Lê Văn C",
        soDT: "0987123456",
        email: "levanc@gmail.com",
        gioiTinh: true,
        ngaySinh: "12/08/2000",
        moTa: "Chủ phòng siêu cấp",
        status: "ACTIVE",
    },
    {
        maChuPhong: "CP4",
        avatar: "",
        tenChuPhong: "Phạm Thị D",
        soDT: "0977654321",
        email: "phamthid@gmail.com",
        gioiTinh: true,
        ngaySinh: "12/08/2000",
        moTa: "Chủ phòng siêu cấp",
        status: "INACTIVE",
    },
    {
        maChuPhong: "CP5",
        avatar: "",
        tenChuPhong: "Hoàng Văn E",
        soDT: "0912123456",
        email: "hoangvane@gmail.com",
        gioiTinh: true,
        ngaySinh: "12/08/2000",
        moTa: "Chủ phòng siêu cấp",
        status: "ACTIVE",
    },
];