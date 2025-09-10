package com.AirBnbProject.AirBnbApplication.controller;

import com.AirBnbProject.AirBnbApplication.dto.RoomDto;
import com.AirBnbProject.AirBnbApplication.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/hotels/{hotelId}/rooms")
public class RoomAdminController {
    private final RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<RoomDto>createRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto){
       RoomDto room= roomService.createNewRoom(hotelId,roomDto);
       return new ResponseEntity<>(room, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<RoomDto>>getALlRooms(@PathVariable Long hotelId){
        return ResponseEntity.ok(roomService.getAllRoomsOfHotel(hotelId));
    }

    @GetMapping("/getByID/{RoomId}")
    public  ResponseEntity<RoomDto>getRoomsById(@PathVariable Long hotelId,@PathVariable Long RoomId){
        return ResponseEntity.ok(roomService.getRoomById(RoomId));
    }

    @DeleteMapping("/delete/{RoomId}")
    public ResponseEntity<RoomDto> deleteById(@PathVariable Long hotelId,@PathVariable Long RoomId){
        roomService.deleteRoomById(RoomId);
        return ResponseEntity.noContent().build();
    }

}
