package com.AirBnbProject.AirBnbApplication.controller;

import com.AirBnbProject.AirBnbApplication.dto.HotelDto;
import com.AirBnbProject.AirBnbApplication.dto.HotelInfoDto;
import com.AirBnbProject.AirBnbApplication.dto.HotelSearchDto;
import com.AirBnbProject.AirBnbApplication.service.HotelService;
import com.AirBnbProject.AirBnbApplication.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {
    private final InventoryService inventoryService;
    private final HotelService hotelService;


    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>>searchHotels(@RequestBody HotelSearchDto hotelSearchDto){
      Page<HotelDto> page=inventoryService.searchHotels(hotelSearchDto);
      return ResponseEntity.ok(page);
    }

    @GetMapping("/getDetails/{hotelId}")
    public ResponseEntity<HotelInfoDto>getHotelInfo(@PathVariable Long hotelId){
          return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }



}
