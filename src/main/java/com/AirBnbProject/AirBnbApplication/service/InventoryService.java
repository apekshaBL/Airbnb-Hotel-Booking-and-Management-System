package com.AirBnbProject.AirBnbApplication.service;


import com.AirBnbProject.AirBnbApplication.dto.HotelDto;
import com.AirBnbProject.AirBnbApplication.dto.HotelSearchDto;
import com.AirBnbProject.AirBnbApplication.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
     void initializeRoomForYear(Room roomId);

     void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchDto hotelSearchDto);
}
