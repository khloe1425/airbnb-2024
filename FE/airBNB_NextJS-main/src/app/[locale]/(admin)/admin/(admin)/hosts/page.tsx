"use client"
import { Input, Table, Modal, Form, Button, message, Select, Tag, DatePicker, Row, Col, Image, Pagination } from "antd";
import { Option } from 'antd/es/mentions';
import {
  DeleteOutlined,
  EditOutlined,
  EyeFilled,
  SearchOutlined,
} from "@ant-design/icons";
import React, { useState } from "react";
import Link from "next/link";
import { phong, Room } from "@/utils/JsonData/roomJson";
import { host, Host } from "@/utils/JsonData/hostJson";
import type { TableProps } from 'antd';
import { createStyles } from "antd-style";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
// import ModalViewUser from "@/components/modal-view-user/ModalViewUser";
import { toSlugWithId } from "@/utils/method/method";
import { useLocale } from "next-intl";
import dayjs from 'dayjs';
import { removeAccents } from "@/utils/removeAccents/removeAccents";

const useStyle = createStyles(({ css }) => {
  return {
    customTable: css`
      .ant-table-container {
        .ant-table-body,
        .ant-table-content {
          scrollbar-width: thin;
          scrollbar-color: unset;
        }
      }
    `,
  };
});

const HostAdmin: React.FC = () => {
  const [isEditModalVisible, setIsEditModalVisible] = useState(false);
  const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
  const [isAddModalVisible, setIsAddModalVisible] = useState(false);
  const [isRoomDetailModalVisible, setIsRoomDetailModalVisible] = useState(false);
  const [isHostDetailModal, setIsHostDetailModal] = useState(false);
  const [listHost, setListHost] = useState<Host[]>(host);
  const [searchKeyword, setSearchKeyword] = useState("");
  const [selectedHost, setSelectedHost] = useState<Host | null>(null);
  const [selectedRooms, setSelectedRooms] = useState<Room[]>([]);
  // Paging
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);
  // 
  const [form] = Form.useForm();
  const { styles } = useStyle();
  // locate
  const locale = useLocale();


  const handleAdd = () => {
    form.resetFields();
    setIsAddModalVisible(true);
  };

  const handleRoomDetail = (host: Host) => {
    const rooms = phong.filter((room) => room.maChuPhong === host.maChuPhong);
    setSelectedRooms(rooms);
    setIsRoomDetailModalVisible(true);
  };

  const handleEdit = (host: Host) => {
    setSelectedHost(host);
    form.setFieldsValue({
      ...host,
      gioiTinh: host.gioiTinh ? "true" : "false",
      ngaySinh: host.ngaySinh ? dayjs(host.ngaySinh, "DD/MM/YYYY") : null,
    });
    setIsEditModalVisible(true);
  };

  const handleDelete = (host: Host) => {
    setSelectedHost(host);
    setIsDeleteModalVisible(true);
  };

  const handleViewHostDetail = (host: Host) => {
    setSelectedHost(host);
    setIsHostDetailModal(true);
  };

  const confirmDelete = () => {
    setListHost((prev) => prev.filter((host) => host.maChuPhong !== selectedHost?.maChuPhong));
    setIsDeleteModalVisible(false);
    message.success("Xoá chủ phòng thành công");
  };

  const handleAddSave = () => {
    form
      .validateFields()
      .then((values) => {
        const newHost = {
          ...values,
          maChuPhong: `CP${listHost.length + 1}`,
          status: "ACTIVE",
          gioiTinh: values.gioiTinh === "true" ? true : false,
          // ngaySinh: values.ngaySinh ? values.ngaySinh.format('DD/MM/YYYY') : null,
        }
        setListHost((prev) => [...prev, newHost]);
        console.log(newHost)
        setIsAddModalVisible(false);
        message.success("Thêm chủ phòng thành công");
      })
      .catch((info) => console.log("Validate Failed:", info));
  };

  const handleSave = () => {
    form
      .validateFields()
      .then((values) => {

        const updatedHost = {
          ...values,
          gioiTinh: values.gioiTinh === "true" ? true : false,
        };

        setListHost((prev) =>
          prev.map((host) =>
            host.maChuPhong === updatedHost.maChuPhong ? updatedHost : host
          )
        );

        console.log("Cập nhật thành công:", updatedHost);
        setIsEditModalVisible(false);
        message.success("Cập nhật chủ phòng thành công!");
      })
      .catch((info) => console.log("Validate Failed:", info));
  };

  const columns: TableProps<Host>['columns'] = [
    {
      title: "Ảnh đại diện",
      dataIndex: "avatar",
      key: "avatar",
      render: (avatar: string) => (
        <div className="w-7 h-7 rounded-full border border-primary-100 flex items-center justify-center overflow-hidden">
          <Image
            src={avatar === "" ? "/images/logo.jpg" : avatar}
            alt="hinh anh"
            className="w-full h-full"
          />
        </div>
      ),
    },
    {
      title: "Mã chủ phòng",
      dataIndex: "maChuPhong",
      key: "maChuPhong",
    },
    {
      title: "Tên chủ phòng",
      dataIndex: "tenChuPhong",
      key: "tenChuPhong",
    },
    {
      title: "Email",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "Phòng sở hữu",
      key: "phongSoHuu",
      render: (_: any, record: Host) => (
        <button className="text-blue-600" onClick={() => handleRoomDetail(record)}>
          Xem phòng
        </button>
      ),
    },
    {
      title: "Trạng thái",
      dataIndex: "status",
      key: "status",
      render: (text: string) => (
        <div>
          {text === "ACTIVE" ? (
            <Tag color="green">{text}</Tag>
          ) : (
            <Tag color="red">{text}</Tag>
          )}
        </div>
      ),
      filters: [
        { text: "ACTIVE", value: "ACTIVE" },
        { text: "INACTIVE", value: "INACTIVE" },
      ],
      onFilter: (value, record) => record.status === value,
    },
    {
      title: "Chức năng",
      key: "action",
      render: (_: any, record: Host) => (
        <div className="flex items-center justify-start gap-5">
          <DeleteOutlined
            onClick={() => handleDelete(record)}
            className="cursor-pointer transition-all duration-500 ease-in-out !text-[#7E7C86] hover:!text-red-600"
          />
          <EditOutlined
            onClick={() => handleEdit(record)}
            className="cursor-pointer transition-all duration-500 ease-in-out !text-[#7E7C86]"
          />
          <EyeFilled
            onClick={() => handleViewHostDetail(record)}
            className="cursor-pointer transition-all duration-500 ease-in-out !text-[#7E7C86]"
          />
        </div>
      ),
    },
  ];

  const filteredData = listHost.filter(
    (host) =>
      host.maChuPhong.toLowerCase().includes(searchKeyword.toLowerCase()) ||
      removeAccents(host.tenChuPhong).toLowerCase().includes(removeAccents(searchKeyword.toLowerCase())) ||
      host.email.toLowerCase().includes(searchKeyword.toLowerCase()) ||
      host.soDT.includes(searchKeyword)
  );

  // Paging
  const paginatedData = filteredData.slice(
    (currentPage - 1) * pageSize,
    currentPage * pageSize
  );


  return (
    <>
      <div className="w-full h-full">
        <div className="w-full h-[50px] flex items-center gap-2 md:gap-0 justify-between">
          <Input
            allowClear
            size="large"
            prefix={<SearchOutlined />}
            placeholder="Nhập mã, tên, số điện thoại hoặc email để tìm kiếm"
            className="!w-[450px]"
            onChange={(e) => {
              setSearchKeyword(e.target.value.toLowerCase().trim());
            }}
          />
          <Button
            onClick={() => {
              form.resetFields();
              setIsAddModalVisible(true);
            }}
            size="large"
            className="!bg-primary-100 !text-white !border-none"
          >
            <FontAwesomeIcon icon={faPlus} />
            <span className="!hidden md:!inline-block">Thêm chủ phòng</span>
          </Button>
        </div>
        <div className="hidden md:block w-full h-[calc(100%-50px)] bg-white rounded-lg mt-2">
          <Table
            className={styles.customTable}
            columns={columns}
            dataSource={filteredData}
            pagination={false}
            rowKey="maChuPhong"
          />
        </div>

        {/*  */}
        {/* Danh sách hiển thị */}
        <div className="flex flex-col flex-wrap gap-2 md:hidden mt-3">
          {paginatedData.map((item, index) => (
            <div
              className="w-full flex gap-3 p-3 rounded-lg bg-white shadow-md"
              key={index}
            >
              <div className="w-16 h-16 rounded-full border border-primary-100 flex items-center justify-center overflow-hidden">
                <Image
                  src={item.avatar !== "" ? item.avatar : "/images/logo.jpg"}
                  alt="hinh anh"
                  height="100%"
                  width="100%"
                  className="!object-cover"
                />
              </div>
              <div className="w-[calc(100% - 64px)] flex flex-col justify-between">
                <div>
                  <h1 className="font-bold">{item.tenChuPhong}</h1>
                  <p>{item.email}</p>
                  <p>
                    {item.status === "INACTIVE" ? (
                      <Tag color="red">{item.status}</Tag>
                    ) : (
                      <Tag color="green">{item.status}</Tag>
                    )}
                  </p>
                </div>
                <div className="flex items-center gap-3">
                  <p
                    onClick={() => handleRoomDetail(item)}
                    className="text-primary-100 hover:underline pt-3"
                  >
                    Xem phòng
                  </p>
                  <p
                    onClick={() => handleViewHostDetail(item)}
                    className="text-primary-100 hover:underline pt-3"
                  >
                    Xem chi tiết
                  </p>
                  <p
                    onClick={() => handleDelete(item)}
                    className="text-primary-100 hover:underline pt-3"
                  >
                    Xoá
                  </p>
                </div>
              </div>
            </div>
          ))}
          <Pagination
            className="mt-4 text-right"
            current={currentPage}
            pageSize={pageSize}
            total={filteredData.length}
            onChange={(page, size) => {
              setCurrentPage(page);
              setPageSize(size);
            }}
            showSizeChanger
            pageSizeOptions={["5", "10", "20"]}
          />
        </div>
      </div>

      {/* --------------------------Modal Components-------------------------- */}
      {/* Modal Add */}
      <Modal
        title={
          <span className="text-3xl font-semibold text-[#fe6b6e]">
            Thêm chủ phòng mới
          </span>
        }
        visible={isAddModalVisible}
        onCancel={() => setIsAddModalVisible(false)}
        onOk={handleAddSave}
        okText="Lưu"
        cancelText="Hủy"
        closable={false}
      >
        <Form form={form} layout="vertical">
          <Form.Item
            name="tenChuPhong"
            label="Tên chủ phòng"
            rules={[{ required: true, message: "Tên không được để trống!" }]}>
            <Input />
          </Form.Item>
          <Form.Item
            name="soDT"
            label="Số điện thoại"
            validateFirst
            rules={[
              { required: true, message: "Số điện thoại không được để trống!" },
              { pattern: /^[0-9]+$/, message: "Số điện thoại chỉ được chứa các ký tự số!" },
              { min: 7, max: 11, message: "Số điện thoại phải dài từ 7 đến 11 số!" },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="email"
            label="Email"
            rules={[
              { required: true, message: "Email không được để trống!" },
              { type: "email", message: "Email không hợp lệ!" }
            ]}
          >
            <Input />
          </Form.Item>
          <Row gutter={[16, 0]}>
            <Col xs={24} sm={12}>
              <Form.Item
                name="gioiTinh"
                label="Giới tính"
                rules={[{ required: true, message: 'Vui lòng chọn giới tính!' }]}
              >
                <Select placeholder="Chọn giới tính"
                  getPopupContainer={(trigger) => trigger.parentElement as HTMLElement}
                >
                  <Option value="true">Nam</Option>
                  <Option value="false">Nữ</Option>
                </Select>
              </Form.Item>
            </Col>
            {/* Ngày sinh */}
            <Col xs={24} sm={12}>
              <Form.Item
                name="ngaySinh"
                label="Ngày sinh"
                rules={[{ required: true, message: 'Vui lòng chọn ngày sinh!' }]}
              >
                <DatePicker
                  format="DD/MM/YYYY"
                  getPopupContainer={(trigger) => trigger.parentElement as HTMLElement}
                />
              </Form.Item>
            </Col>
          </Row>
          <Form.Item
            name="moTa"
            label="Mô tả"
            rules={[
              { required: true, message: "mô tả không được để trống!" }
            ]}
          >
            <Input />
          </Form.Item>
        </Form>
      </Modal>

      {/* Modal Delete */}
      <Modal
        title={
          <span className="text-3xl font-semibold text-[#fe6b6e]">
            Xác nhận xoá
          </span>
        }
        visible={isDeleteModalVisible}
        onCancel={() => setIsDeleteModalVisible(false)}
        onOk={confirmDelete}
        okText="Xóa"
        cancelText="Hủy"
        closable={false}
      >
        <p>Bạn có chắc chắn muốn xóa chủ phòng "{selectedHost?.tenChuPhong}"?</p>
      </Modal>

      {/* Modal Chi Tiết Phòng */}
      <Modal
        title={
          <span className="text-3xl font-semibold text-[#fe6b6e]">
            Chi tiết phòng - Chủ phòng: {selectedRooms[0]?.maChuPhong ? selectedRooms[0]?.maChuPhong : "Chưa có thông tin"}
          </span>
        }
        visible={isRoomDetailModalVisible}
        onCancel={() => setIsRoomDetailModalVisible(false)}
        footer={null}
        width={800}
        bodyStyle={{ maxHeight: "400px", overflowY: "scroll" }}
      >
        <div className="space-y-4">
          {selectedRooms.map((room) => (
            <div key={room.id} className="border-b pb-4 flex items-center gap-4">
              <img
                src={room.hinhAnh}
                alt={room.tenPhong}
                className="w-1/3 h-40 object-cover rounded-lg"
              />
              <div className="w-2/3">
                <h3 className="text-xl font-semibold">{room.tenPhong}</h3>
                <p><strong>ID Phòng:</strong> {room.id}</p>
                <p><strong>Giá tiền:</strong> {room.giaTien} USD</p>
                <p className="line-clamp-2"><strong>Mô tả: </strong>{room.moTa}</p>
                <Link
                  // href={`/room-detail/${room.id}`}
                  href={`/${locale}/room/${toSlugWithId(room.tenPhong, room.id)}`}
                  className="text-blue-500 hover:text-blue-700 transition duration-300 ease-in-out"
                >
                  Xem chi tiết phòng
                </Link>
              </div>
            </div>
          ))}
        </div>
      </Modal>

      {/* Modal Update */}
      <Modal
        title={
          <span className="text-3xl font-semibold text-[#fe6b6e]">
            Sửa thông tin chủ phòng
          </span>
        }
        visible={isEditModalVisible}
        onCancel={() => setIsEditModalVisible(false)}
        onOk={handleSave}
        okText="Cập nhật"
        cancelText="Hủy"
        closable={false}
      >
        <Form form={form} layout="vertical">
          <Form.Item name="maChuPhong" label="Mã chủ phòng">
            <Input disabled />
          </Form.Item>
          <Form.Item
            name="tenChuPhong"
            label="Tên chủ phòng"
            rules={[{ required: true, message: "Tên không được để trống!" }]}>
            <Input />
          </Form.Item>
          <Form.Item
            name="soDT"
            label="Số điện thoại"
            validateFirst
            rules={[
              { required: true, message: "Số điện thoại không được để trống!" },
              { pattern: /^[0-9]+$/, message: "Số điện thoại chỉ được chứa các ký tự số!" },
              { min: 7, max: 11, message: "Số điện thoại phải dài từ 7 đến 11 số!" },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="email"
            label="Email"
            rules={[
              { required: true, message: "Tên không được để trống!" },
              { type: "email", message: "Email không hợp lệ!" }
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="status"
            label="Trạng thái"
            rules={[{ required: true, message: "Trạng thái không được để trống!" }]}>
            <Select>
              <Select.Option value="ACTIVE">ACTIVE</Select.Option>
              <Select.Option value="INACTIVE">INACTIVE</Select.Option>
            </Select>
          </Form.Item>
          <Row gutter={[16, 0]}>
            <Col xs={24} sm={12}>
              <Form.Item
                name="gioiTinh"
                label="Giới tính"
                rules={[{ required: true, message: 'Vui lòng chọn giới tính!' }]}
              >
                <Select
                  placeholder="Chọn giới tính"
                  getPopupContainer={(trigger) => trigger.parentElement as HTMLElement}
                >
                  <Option value="true">Nam</Option>
                  <Option value="false">Nữ</Option>
                </Select>
              </Form.Item>
            </Col>
            {/* Ngày sinh */}
            <Col xs={24} sm={12}>
              <Form.Item
                name="ngaySinh"
                label="Ngày sinh"
                rules={[{ required: true, message: 'Vui lòng chọn ngày sinh!' }]}
              >
                <DatePicker
                  format="DD/MM/YYYY"
                  getPopupContainer={(trigger) => trigger.parentElement as HTMLElement}
                />
              </Form.Item>
            </Col>
          </Row>
          <Form.Item
            name="moTa"
            label="Mô tả"
            rules={[
              { required: true, message: "mô tả không được để trống!" }
            ]}
          >
            <Input />
          </Form.Item>
        </Form>
      </Modal>
      {/* Detail */}
      <Modal
        title={
          <span className="text-3xl font-semibold text-[#fe6b6e]">
            Chi tiết chủ phòng
          </span>
        }
        visible={isHostDetailModal}
        footer={[
          <Button key="close" onClick={() => setIsHostDetailModal(false)}>
            Đóng
          </Button>,
          <Button className="!bg-primary-100 !text-white" key="open" onClick={() => {
            setIsHostDetailModal(false);
            if (selectedHost !== null) {
              handleEdit(selectedHost);
            }
          }}>
            Cập nhật thông tin
          </Button>
        ]}
        closable={false}
      >
        {selectedHost ? (
          <div>
            <p><b>Mã chủ phòng:</b> {selectedHost.maChuPhong}</p>
            <p><b>Tên chủ phòng:</b> {selectedHost.tenChuPhong}</p>
            <p><b>Số điện thoại:</b> {selectedHost.soDT}</p>
            <p><b>Email:</b> {selectedHost.email}</p>
            <p><b>Ngày sinh:</b> {selectedHost.ngaySinh}</p>
            <p><b>Mô tả: </b> {selectedHost.moTa}</p>
            <p><b>Trạng thái:</b> {selectedHost.status === "ACTIVE" ? "Hoạt động" : "Không hoạt động"}</p>
          </div>
        ) : (
          <p>Không có dữ liệu</p>
        )}
      </Modal>
    </>
  );
};

export default HostAdmin;