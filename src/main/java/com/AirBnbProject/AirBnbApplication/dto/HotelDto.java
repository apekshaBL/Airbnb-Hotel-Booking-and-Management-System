package com.AirBnbProject.AirBnbApplication.dto;

import com.AirBnbProject.AirBnbApplication.entity.HotelContactInfo;
import com.AirBnbProject.AirBnbApplication.entity.Room;
import lombok.Data;
import java.util.List;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;

    private HotelContactInfo hotelContactInfo;
    private boolean active;


}
