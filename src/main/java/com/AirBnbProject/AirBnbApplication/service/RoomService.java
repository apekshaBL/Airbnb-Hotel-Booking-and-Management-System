package com.AirBnbProject.AirBnbApplication.service;

import com.AirBnbProject.AirBnbApplication.dto.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto createNewRoom(Long HotelId,RoomDto roomDto);
    RoomDto getRoomById(Long RoomId);
    List<RoomDto> getAllRoomsOfHotel(Long HotelId);
    void deleteRoomById(Long RoomId);
}
