package com.AirBnbProject.AirBnbApplication.controller;

import com.AirBnbProject.AirBnbApplication.dto.HotelDto;
import com.AirBnbProject.AirBnbApplication.entity.Hotel;
import com.AirBnbProject.AirBnbApplication.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {
   private final HotelService hotelService;

   @PostMapping("/create")
    public ResponseEntity<HotelDto>createNewHotel(@RequestBody HotelDto hotelDto){
       log.info("Attempting to create a new  hotel with name:"+hotelDto.getName());
       HotelDto hotel=hotelService.createNewHotel(hotelDto);
       return new ResponseEntity<>(hotel, HttpStatus.CREATED);
   }
   @GetMapping("/{id}")
    public ResponseEntity<HotelDto>getHotelById(@PathVariable Long id){
       log.info("fetching hotel by id :"+id);
       HotelDto hotel=hotelService.getHotelById(id);
       return ResponseEntity.ok(hotel);
   }

   @PutMapping("/update/{id}")
    public ResponseEntity<HotelDto>updateHotelById(@PathVariable Long id,@RequestBody HotelDto hotelDto){
       HotelDto hotel=hotelService.updateHotelById(id,hotelDto);
       return ResponseEntity.ok(hotel);
   }

   @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
     hotelService.deleteHotelById(id);
     return ResponseEntity.noContent().build();
   }




}
