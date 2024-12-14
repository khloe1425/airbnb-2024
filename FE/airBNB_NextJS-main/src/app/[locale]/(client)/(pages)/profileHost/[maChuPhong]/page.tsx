"use client"
import Link from "next/link";
import React, { useState, useEffect } from 'react';
import { host, Host } from "@/utils/JsonData/hostJson";
import { phong, Room } from "@/utils/JsonData/roomJson";

type Props = {
  params: {
    locale: string;
    maChuPhong: string;
  };
  searchParams: Record<string, unknown>;
}

const ProfileHostPage = (props: Props) => {
  const { params: { maChuPhong } } = props;
  const [currentPage, setCurrentPage] = useState(0);
  // const [roomJson, setRoomJson] = useState<Room[]>([]);
  const [roomJson, setRoomJson] = useState<Room[]>(phong.filter((room) => room.maChuPhong === maChuPhong));
  const [currentIndex, setCurrentIndex] = useState(0);

  const reviews = [
    "Nhà/phòng cho thuê hoàn toàn giống với ảnh và rất đẹp. Nó sạch sẽ và đẹp, và chủ nhà rất tốt bụng. Tôi cảm thấy như ở nhà khi ở Paris. Và con mèo thật dễ thương! Nếu tôi đến Paris lần sau, tôi muốn ở lại một lần nữa!",
    "Chỗ ở rất tuyệt vời, sạch sẽ và tiện nghi. Chủ nhà rất chu đáo và thân thiện, tôi rất hài lòng với kỳ nghỉ của mình.",
    "Một trải nghiệm tuyệt vời! Phòng rộng rãi, thoải mái, và có tất cả những gì tôi cần. Chủ nhà rất dễ thương và giúp đỡ.",
    "Phòng rất đẹp và thoải mái. Các tiện nghi đầy đủ và không gian sạch sẽ. Một nơi tuyệt vời để thư giãn.",
    "Căn hộ nhỏ nhưng rất tiện nghi. Chủ nhà rất nhiệt tình và dễ thương, tôi sẽ quay lại lần nữa."
  ];

  // Đánh giá
  const reviewsPerPage = 2;  // Hiển thị 2 ô đánh giá trên mỗi trang
  const totalPages = Math.ceil(reviews.length / reviewsPerPage);

  const nextPage = () => {
    setCurrentPage((prevPage) => (prevPage + 1) % totalPages);
  };

  const prevPage = () => {
    setCurrentPage((prevPage) => (prevPage - 1 + totalPages) % totalPages);
  };

  const currentReviews = reviews.slice(currentPage * reviewsPerPage, (currentPage + 1) * reviewsPerPage);

  // Lấy ds chủ phòng từ json
  const hostJson = host.find((item) => item.maChuPhong === maChuPhong);

  const currentRoom = roomJson[currentIndex];
  const totalRooms = roomJson.length;

  const prevRoom = () => {
    if (currentIndex > 0) setCurrentIndex(currentIndex - 1);
  };

  const nextRoom = () => {
    if (currentIndex < totalRooms - 1) setCurrentIndex(currentIndex + 1);
  };

  return (
    <div className="container mx-auto px-4 py-8 max-w-[400px] lg:max-w-none">
      <div className="grid grid-cols-12 gap-4">
        <div className="col-span-12 lg:col-span-4">
          <div className="sticky top-20">
            {/* Box 1 */}
            <div className="bg-white p-4 rounded-2xl shadow-md mb-4">
              <div className="flex justify-between">
                <div className="flex flex-col items-center space-y-2 w-1/2">
                  <div className="w-24 h-24 bg-gray-300 rounded-full overflow-hidden">
                    <img
                      src={hostJson?.avatar || "https://picsum.photos/200"}
                      alt="Avatar"
                      className="w-full h-full object-cover"
                    />
                  </div>
                  <h1 className="text-2xl font-semibold">{hostJson?.tenChuPhong}</h1>
                  <div className="flex items-center space-x-2 text-sm text-gray-500">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" className="w-4 h-4 fill-green-500">
                      <path d="m8.5 7.6 3.1-1.75 1.47-.82a.83.83 0 0 0 .43-.73V1.33a.83.83 0 0 0-.83-.83H3.33a.83.83 0 0 0-.83.83V4.3c0 .3.16.59.43.73l3 1.68 1.57.88c.35.2.65.2 1 0zm-.5.9a3.5 3.5 0 1 0 0 7 3.5 3.5 0 0 0 0-7z"></path>
                    </svg>
                    <span>Chủ nhà siêu cấp</span>
                  </div>
                </div>
                <div className="text-sm text-gray-500 w-1/2">
                  <div>
                    <p><span className="font-bold text-xl text-black">364</span> <br /> Đánh giá</p>
                  </div>
                  <hr className="my-2" />
                  <div>
                    <span><span className="font-bold text-xl text-black">4.83</span> <br /> Xếp hạng</span>
                  </div>
                  <hr className="my-2" />
                  <div>
                    <span><span className="font-bold text-xl text-black">14</span> <br /> năm kinh nghiệm đón tiếp khách</span>
                  </div>
                </div>
              </div>
            </div>
            {/* Box 2 */}
            <div className="bg-white p-5 rounded-2xl border">
              <h3 className="text-xl py-3 font-semibold">Thông tin đã được xác nhận của {hostJson?.tenChuPhong}</h3>
              <p className="flex items-center text-lg pb-2">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', fill: 'none', height: 24, width: 24, stroke: 'currentcolor', strokeWidth: '2.66667', overflow: 'visible', paddingRight: '7px' }}>
                  <path fill="none" d="m4 16.5 8 8 16-16" />
                </svg>
                <span>Danh tính</ span>
              </p>
              <p className="flex items-center text-lg pb-2">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', fill: 'none', height: 24, width: 24, stroke: 'currentcolor', strokeWidth: '2.66667', overflow: 'visible', paddingRight: '7px' }}>
                  <path fill="none" d="m4 16.5 8 8 16-16" />
                </svg>
                <span>Địa chỉ email</span>
              </p>
              <p className="flex items-center text-lg pb-2">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', fill: 'none', height: 24, width: 24, stroke: 'currentcolor', strokeWidth: '2.66667', overflow: 'visible', paddingRight: '7px' }}>
                  <path fill="none" d="m4 16.5 8 8 16-16" />
                </svg>
                <span>Số điện thoại</span>
              </p>
            </div>
            {/* Báo cáo màn hình lớn hơn lg */}
            <div className="mt-4 flex items-center space-x-2 hidden lg:flex">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', height: 16, width: 16, fill: 'currentcolor' }}>
                <path d="M28 6H17V4a2 2 0 0 0-2-2H3v28h2V18h10v2a2 2 0 0 0 2 2h11.12a1 1 0 0 0 .84-1.28L27.04 14l1.92-6.72A1 1 0 0 0 28 6z" />
              </svg>
              <Link className="font-bold underline text-black" href="#">Báo cáo hồ sơ này</Link>
            </div>
          </div>
        </div>
        {/* Right Section (7 parts) */}
        <div className="col-span-12 lg:col-span-8">
          {/* Section 1: Thông tin */}
          <section className="bg-white lg:px-6 rounded-lg">
            <h1 className="text-3xl font-bold mb-8 hidden lg:block">Thông tin về {hostJson?.tenChuPhong}</h1>
            <ul className="list-none flex flex-wrap">
              <li className="w-full lg:w-1/2 pb-4 lg:pr-4">
                <p className="flex items-center text-base">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', height: 30, width: 30, fill: 'var(--linaria-theme_palette-hof)', marginRight: '15px' }}>
                    <path d="m31.47 10.12-15-8a1 1 0 0 0-.94 0l-15 8a1 1 0 0 0 0 1.76L4 13.73V23a1 1 0 0 0 .52.88l11 6a1 1 0 0 0 .96 0l11-6A1 1 0 0 0 28 23v-9.27l2-1.06V23h2V11a1 1 0 0 0-.53-.88zM26 22.4l-10 5.45-10-5.45V14.8l9.53 5.08a1 1 0 0 0 .94 0L26 14.8v7.6zm-10-4.54L3.12 11 16 4.13 28.88 11 16 17.87z" />
                  </svg>
                  <span>Nơi tôi từng theo học: INSEAD, IAE Paris Sorbonne, ICAM</span>
                </p>
              </li>
              <li className="w-full lg:w-1/2 pb-4 lg:pr-4">
                <p className="flex items-center text-base">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', height: 25, width: 25, fill: 'var(--linaria-theme_palette-hof)', marginRight: '15px' }}><path d="M20 2a2 2 0 0 1 2 1.85V6h6a3 3 0 0 1 3 2.82V27a3 3 0 0 1-2.82 3H4a3 3 0 0 1-3-2.82V9a3 3 0 0 1 2.82-3H10V4a2 2 0 0 1 1.85-2H12zm8 6H4a1 1 0 0 0-1 .88V12a3 3 0 0 0 2.82 3H13v2H6a4.98 4.98 0 0 1-3-1v11a1 1 0 0 0 .88 1H28a1 1 0 0 0 1-.88V16c-.78.59-1.74.95-2.78 1h-7.17v-2H26a3 3 0 0 0 3-2.82V9a1 1 0 0 0-.88-1zm-10 4a1 1 0 0 1 1 .88V19a1 1 0 0 1-.88 1H14a1 1 0 0 1-1-.88V13a1 1 0 0 1 .88-1H14zm-1 2h-2v4h2zm3-10h-8v2h8z" /></svg>
                  <span>Công việc của tôi: Nghiêm ngặt & Dirigeant</span>
                </p>
              </li>
              <li className="w-full lg:w-1/2 pb-4 lg:pr-4">
                <p className="flex items-center text-base">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', height: 30, width: 30, fill: 'var(--linaria-theme_palette-hof)', marginRight: '15px' }}>
                    <path d="M16 .33a15.67 15.67 0 1 1 0 31.34A15.67 15.67 0 0 1 16 .33zm0 2a13.67 13.67 0 1 0 0 27.34 13.67 13.67 0 0 0 0-27.34zm1 3v10.1l8.74 5.04-1 1.73L15 16.58V5.33z" />
                  </svg>
                  <span>Tôi dành quá nhiều thời gian để: Nhiếp ảnh, Du lịch, Ẩm thực</span>
                </p>
              </li>
              <li className="w-full lg:w-1/2 pb-4 lg:pr-4">
                <p className="flex items-center text-base">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', height: 25, width: 25, fill: 'var(--linaria-theme_palette-hof)', marginRight: '15px' }}><path d="M26 2a5 5 0 0 1 5 4.78V21a5 5 0 0 1-4.78 5h-6.06L16 31.08 11.84 26H6a5 5 0 0 1-4.98-4.56L1 21.22 1 21V7a5 5 0 0 1 4.78-5H26zm0 2H6a3 3 0 0 0-3 2.82V21a3 3 0 0 0 2.82 3H12.8l3.2 3.92L19.2 24H26a3 3 0 0 0 3-2.82V7a3 3 0 0 0-2.82-3H26zM16 6a8.02 8.02 0 0 1 8 8.03A8 8 0 0 1 16.23 22h-.25A8 8 0 0 1 8 14.24v-.25A8 8 0 0 1 16 6zm1.68 9h-3.37c.11 1.45.43 2.76.79 3.68l.09.22.13.3c.23.45.45.74.62.8H16c.33 0 .85-.94 1.23-2.34l.11-.44c.16-.67.29-1.42.34-2.22zm4.24 0h-2.23c-.1 1.6-.42 3.12-.92 4.32a6 6 0 0 0 3.1-4.07l.05-.25zm-9.61 0h-2.23a6 6 0 0 0 3.14 4.32c-.5-1.2-.82-2.71-.91-4.32zm.92-6.32-.13.07A6 6 0 0 0 10.08 13h2.23c.1-1.61.42-3.12.91-4.32zM16 8h-.05c-.27.08-.64.7-.97 1.65l-.13.4a13.99 13.99 0 0 0-.54 2.95h3.37c-.19-2.66-1.1-4.85-1.63-5H16zm2.78.69.02.05c.48 1.19.8 2.68.9 4.26h2.21A6.02 6.02 0 0 0 19 8.8l-.22-.12z" /></svg>
                  <span>Nói Tiếng Anh và Tiếng Pháp</span>
                </p>
              </li>
              <li className="w-full lg:w-1/2 pb-4 lg:pr-4">
                <p className="flex items-center text-base">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', height: 30, width: 30, fill: 'var(--linaria-theme_palette-hof)', marginRight: '15px' }}>
                    <path d="M13.7 13.93a4 4 0 0 1 5.28.6l.29.37 4.77 6.75a4 4 0 0 1 .6 3.34 4 4 0 0 1-4.5 2.91l-.4-.08-3.48-.93a1 1 0 0 0-.52 0l-3.47.93a4 4 0 0 1-2.94-.35l-.4-.25a4 4 0 0 1-1.2-5.2l.23-.37 4.77-6.75a4 4 0 0 1 .96-.97zm3.75 1.9a2 2 0 0 0-2.98.08l-.1.14-4.84 6.86a2 2 0 0 0 2.05 3.02l.17-.04 4-1.07a1 1 0 0 1 .5 0l3.97 1.06.15.04a2 2 0 0 0 2.13-2.97l-4.95-7.01zM27 12a4 4 0 1 1 0 8 4 4 0 0 1 0-8zM5 12a4 4 0 1 1 0 8 4 4 0 0 1 0-8zm22 2a2 2 0 1 0 0 4 2 2 0 0 0 0-4zM5 14a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm6-10a4 4 0 1 1 0 8 4 4 0 0 1 0-8zm10 0a4 4 0 1 1 0 8 4 4 0 0 1 0-8zM11 6a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm10 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4z" />
                  </svg>
                  <span>Thú cưng: Một chú mèo hổ tên Titie</span>
                </p>
              </li>
              <li className="w-full lg:w-1/2 pb-4 lg:pr-4">
                <p className="flex items-center text-base">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', height: 25, width: 25, fill: 'var(--linaria-theme_palette-hof)', marginRight: '15px' }}><path d="M23.02 3a8.3 8.3 0 0 0-5.58 2.44L16 6.88l-1.44-1.44a8.31 8.31 0 0 0-11.79 0 8.3 8.3 0 0 0-2.44 5.9c0 4.92 2.49 8.84 8.04 13.43l.7.58.75.58.77.6.4.3.82.6.86.62.89.63.92.64L16 30.35l1.33-.9 1.06-.73.66-.46.7-.5.85-.62c7.4-5.45 11.07-10.12 11.07-15.8A8.3 8.3 0 0 0 23.33 3zm.31 2a6.31 6.31 0 0 1 6.34 6.33c0 4.3-2.62 8.12-8.01 12.48l-.72.57-.75.58-.79.58-.81.6-.7.49-.65.45-1.24.85-.36-.24-.9-.63-.87-.6-.42-.31-.81-.6-.4-.29-.76-.58-.74-.57c-5.84-4.6-8.4-8.31-8.4-12.78A6.3 6.3 0 0 1 8.66 5c1.67 0 3.27.65 4.48 1.85L16 9.71l2.86-2.86A6.31 6.31 0 0 1 23.33 5z" /></svg>
                  <span>Sống tại Paris, Pháp</span>
                </p>
              </li>
              <li className="w-full lg:w-1/2 pb-4 lg:pr-4">
                <p className="flex items-center text-base">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', height: 25, width: 25, fill: 'var(--linaria-theme_palette-hof)', marginRight: '15px' }}>
                    <path d="m5.7 1.3 3 3-.66.72a12 12 0 0 0 16.95 16.94l.72-.67 3 3-1.42 1.42-1.67-1.68A13.94 13.94 0 0 1 18 26.96V29h3v2h-8v-2h3v-2.04a13.95 13.95 0 0 1-8.92-4.08 14 14 0 0 1-1.11-18.5L4.29 2.71zm18.18 4.44.21.21.21.22a10 10 0 1 1-.64-.63zm-9.34 11.13-2.45 2.45a8 8 0 0 0 8.04 1.05 16.7 16.7 0 0 1-5.59-3.5zm4.91-4.91-3.5 3.5c2.85 2.54 6.08 3.82 6.7 3.2.63-.61-.66-3.85-3.2-6.7zm-9.81-2.1-.08.19a8 8 0 0 0 1.12 7.86l2.45-2.45a16.68 16.68 0 0 1-3.5-5.6zM23.32 8.1l-2.45 2.44a16.73 16.73 0 0 1 3.5 5.6 8 8 0 0 0-1.05-8.05zm-11.98-.76c-.62.62.66 3.86 3.2 6.7l3.5-3.5c-2.85-2.54-6.07-3.82-6.7-3.2zm2.54-1.7c1.75.59 3.75 1.83 5.58 3.49l2.44-2.45a8.03 8.03 0 0 0-8.02-1.04z" />
                  </svg>
                  <span>Sống tại Paris, Pháp</span>
                </p>
              </li>
            </ul>
            <p>{hostJson?.moTa}</p>
            <hr className="border-gray-200 lg:px-6 px-4 my-10" />
          </section>
          {/* Sectino 2: slider đánh giá */}
          <section className="relative lg:px-6 rounded-lg">
            <div className="flex items-center justify-between">
              <h1 className="text-xl font-bold text-center">Đánh giá của {hostJson?.tenChuPhong}</h1>
              <div className="flex space-x-2">
                <button onClick={prevPage} className="p-2 text-gray-500 hover:text-gray-700 transition-all duration-300">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" className="h-6 w-6" fill="none" stroke="currentColor">
                    <path d="M20 28L8.7 16.7a1 1 0 0 1 0-1.4L20 4" />
                  </svg>
                </button>
                <button onClick={nextPage} className="p-2 text-gray-500 hover:text-gray-700 transition-all duration-300">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" className="h-6 w-6" fill="none" stroke="currentColor">
                    <path d="M12 4l11.3 11.3a1 1 0 0 1 0 1.4L12 28" />
                  </svg>
                </button>
              </div>
            </div>

            <div className="mt-4">
              {/* <div className="text-center text-gray-500">Đang hiển thị {currentPage + 1}/{totalPages} trang</div> */}
              <div className="mt-2 grid grid-cols-1 gap-4 transition-all duration-500">
                {/* Chỉ hiển thị 1 ô đánh giá tại một thời điểm */}
                <div className="p-4 rounded-2xl border space-y-2">
                  <blockquote className="italic text-lg">
                    {currentReviews[0]}
                  </blockquote>
                </div>
              </div>
            </div>
            <div className="p-1 hover:bg-gray-100 rounded-lg transition-all duration-500 mt-4 inline-block">
              <Link className="font-bold underline text-black" href="#">Hiển thị thêm đánh giá</Link>
            </div>
            <hr className="border-gray-200 lg:px-6 px-4 my-10" />
          </section>

          {/* Section nhà cho thuê */}
          <section className="relative lg:px-6 rounded-lg">
            <div className="flex items-center justify-between">
              <h1 className="text-xl font-bold text-center">
                Mục cho thuê của {hostJson?.tenChuPhong}
              </h1>
              <div className="flex space-x-2">
                <button
                  onClick={prevRoom}
                  className={`p-2 text-gray-500 hover:text-gray-700 transition-all duration-300 ${currentIndex === 0 && "opacity-50 cursor-not-allowed"
                    }`}
                  disabled={currentIndex === 0}
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 32 32"
                    className="h-6 w-6"
                    fill="none"
                    stroke="currentColor"
                  >
                    <path d="M20 28L8.7 16.7a1 1 0 0 1 0-1.4L20 4" />
                  </svg>
                </button>
                <button
                  onClick={nextRoom}
                  className={`p-2 text-gray-500 hover:text-gray-700 transition-all duration-300 ${currentIndex === totalRooms - 1 && "opacity-50 cursor-not-allowed"
                    }`}
                  disabled={currentIndex === totalRooms - 1}
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 32 32"
                    className="h-6 w-6"
                    fill="none"
                    stroke="currentColor"
                  >
                    <path d="M12 4l11.3 11.3a1 1 0 0 1 0 1.4L12 28" />
                  </svg>
                </button>
              </div>
            </div>

            <div className="mt-4">
              <div className="mt-2 grid grid-cols-1 gap-4 transition-all duration-500">
                {/* Hiển thị thông tin phòng */}
                <div className="rounded-2xl space-y-4">
                  <img
                    src={currentRoom.hinhAnh || "/placeholder.jpg"}
                    alt={currentRoom.tenPhong}
                    className="rounded-lg w-full h-64 object-cover"
                  />
                  <div>
                    <h2 className="text-2xl font-bold">{currentRoom.tenPhong}</h2>
                    <p className="text-gray-700 line-clamp-3">{currentRoom.moTa || "Không có mô tả"}</p>
                  </div>
                </div>
              </div>
            </div>

            <div className="mt-4 flex justify-between items-center">
              <span className="text-sm text-gray-500">
                Phòng {currentIndex + 1} / {totalRooms}
              </span>
              <div className="p-1 hover:bg-gray-100 rounded-lg transition-all duration-500 inline-block">
                <a
                  className="font-bold underline text-black cursor-pointer"
                  href="#"
                >
                  Xem thêm thông tin
                </a>
              </div>
            </div>

            <hr className="border-gray-200 lg:px-6 px-4 my-10 block lg:hidden" />
          </section>

          <section className="lg:px-6 block lg:hidden">
            <div className="mt-4 flex items-center space-x-2">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" aria-hidden="true" role="presentation" focusable="false" style={{ display: 'block', height: 16, width: 16, fill: 'currentcolor' }}>
                <path d="M28 6H17V4a2 2 0 0 0-2-2H3v28h2V18h10v2a2 2 0 0 0 2 2h11.12a1 1 0 0 0 .84-1.28L27.04 14l1.92-6.72A1 1 0 0 0 28 6z" />
              </svg>
              <Link className="font-bold underline text-black" href="#">Báo cáo hồ sơ này</Link>
            </div>
          </section>
        </div>
      </div>
    </div>
  )
}

export default ProfileHostPage