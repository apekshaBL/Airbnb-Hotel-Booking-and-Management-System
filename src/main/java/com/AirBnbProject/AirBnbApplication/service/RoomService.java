package com.AirBnbProject.AirBnbApplication.service;

import com.AirBnbProject.AirBnbApplication.dto.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto createRoom(RoomDto roomDto);
    RoomDto getRoomById(Long id);
    List<RoomDto> getAllRooms();
    void deleteRoomById(Long id);
}
