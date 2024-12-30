import React from 'react'
import RoomItem from '../room-item/RoomItem'
import { getAllRoomAsync } from '@/services/rooms/rooms.service';
import { RoomType } from '@/types/room/roomType.type';

type Props = {
    viTri: number;
    maPhong: number
};

const RelatedRoom: React.FC<Props> = async ({ viTri, maPhong }) => {
    const res: RoomType[] = await getAllRoomAsync();
    const filteredRooms = res.filter((room) => room.maViTri === viTri && room.id !== maPhong);
    // Lấy ngẫu nhiên 4 phòng
    const relatedRoom = filteredRooms.sort(() => 0.5 - Math.random()).slice(0, 4);

    return (
        <div>
            <div className="flex flex-wrap gap-3">
                {relatedRoom.map((room: RoomType, index: number) => (
                    <RoomItem key={index} data={room} />
                ))}
            </div>
        </div>
    )
}

export default RelatedRoom