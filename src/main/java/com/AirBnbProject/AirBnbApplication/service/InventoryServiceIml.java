package com.AirBnbProject.AirBnbApplication.service;

import com.AirBnbProject.AirBnbApplication.dto.HotelDto;
import com.AirBnbProject.AirBnbApplication.dto.HotelSearchDto;
import com.AirBnbProject.AirBnbApplication.entity.Hotel;
import com.AirBnbProject.AirBnbApplication.entity.Inventory;
import com.AirBnbProject.AirBnbApplication.entity.Room;
import com.AirBnbProject.AirBnbApplication.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceIml implements InventoryService{
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void initializeRoomForYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate= today.plusYears(1);
        for(;!today.isAfter(endDate);today=today.plusDays(1)){
            Inventory inventory=Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteAllInventories(Room room) {
        LocalDate today=LocalDate.now();
        inventoryRepository.deleteByRoom(room);

    }

    @Override
    public Page<HotelDto> searchHotels(HotelSearchDto hotelSearchDto) {
        Pageable pageable = PageRequest.of(hotelSearchDto.getPage(),hotelSearchDto.getSize());
        long dateCount= ChronoUnit.DAYS.between(hotelSearchDto.getCheckInDate(),hotelSearchDto.getCheckOutDate())+1;
        Page<Hotel>hotelPage=inventoryRepository.findHotelsWithAvailableInventory(hotelSearchDto.getCity(),
                hotelSearchDto.getCheckInDate(),hotelSearchDto.getCheckOutDate(),hotelSearchDto.getRoomsCount(),dateCount,pageable);
        return hotelPage.map((element)->modelMapper.map(element,HotelDto.class));
    }
}
