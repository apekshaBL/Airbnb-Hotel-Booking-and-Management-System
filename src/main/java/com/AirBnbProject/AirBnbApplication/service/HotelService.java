package com.AirBnbProject.AirBnbApplication.service;

import com.AirBnbProject.AirBnbApplication.dto.HotelDto;
import com.AirBnbProject.AirBnbApplication.entity.Hotel;

public interface HotelService {
    HotelDto createNewHotel(HotelDto  hotelDto);

    HotelDto getHotelById(Long id);
    HotelDto updateHotelById(Long id,HotelDto hotelDto);
    void deleteHotelById(Long id);
}
