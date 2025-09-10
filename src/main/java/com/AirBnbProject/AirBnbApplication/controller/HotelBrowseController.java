package com.AirBnbProject.AirBnbApplication.controller;

import com.AirBnbProject.AirBnbApplication.dto.HotelDto;
import com.AirBnbProject.AirBnbApplication.dto.HotelSearchDto;
import com.AirBnbProject.AirBnbApplication.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {
    private final InventoryService inventoryService;
    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>>searchHotels(@RequestBody HotelSearchDto hotelSearchDto){
      Page<HotelDto> page=inventoryService.searchHotels(hotelSearchDto);
      return ResponseEntity.ok(page);
    }



}
