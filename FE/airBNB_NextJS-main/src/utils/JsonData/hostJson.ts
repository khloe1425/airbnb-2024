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
        avatar: "https://photo.znews.vn/w660/Uploaded/sgtnrn/2024_04_14/ty_phu_682.jpg",
        tenChuPhong: "Bill Gates",
        soDT: "0901234567",
        email: "gb1234@gmail.com",
        gioiTinh: true,
        ngaySinh: "12/08/2000",
        moTa: "Chủ nhà siêu cấp là những người có kinh nghiệm, được đánh giá cao và cam kết mang lại kỳ nghỉ tuyệt vời cho khách.",
        status: "ACTIVE",
    },
    {
        maChuPhong: "CP2",
        avatar: "https://media.vov.vn/sites/default/files/styles/large/public/2023-11/1_8.jpeg",
        tenChuPhong: "Lisa",
        soDT: "0937654321",
        email: "lisabp1@gmail.com",
        gioiTinh: false,
        ngaySinh: "12/08/2000",
        moTa: "Tinh thần tổng thể, háo hức cho du lịch, tò mò, epicurean, networker và vĩnh viễn...",
        status: "ACTIVE",
    },
    {
        maChuPhong: "CP3",
        avatar: "https://bsmedia.business-standard.com/_media/bs/img/article/2024-07/24/full/1721806332-4893.JPG?im=FeatureCrop,size=(826,465)",
        tenChuPhong: "Elon Musk",
        soDT: "0987123456",
        email: "musk100@gmail.com",
        gioiTinh: true,
        ngaySinh: "12/08/2000",
        moTa: "chủ nhà siêu cấp",
        status: "ACTIVE",
    },
    {
        maChuPhong: "CP4",
        avatar: "",
        tenChuPhong: "Bill",
        soDT: "0977654321",
        email: "bill0121@gmail.com",
        gioiTinh: true,
        ngaySinh: "12/08/1992",
        moTa: "chủ nhà siêu cấp",
        status: "ACTIVE",
    },
    {
        maChuPhong: "CP5",
        avatar: "",
        tenChuPhong: "Nguyễn Quang Hải",
        soDT: "0912123456",
        email: "hoanghai2111@gmail.com",
        gioiTinh: true,
        ngaySinh: "12/08/1997",
        moTa: "chủ nhà siêu cấp",
        status: "ACTIVE",
    },
];