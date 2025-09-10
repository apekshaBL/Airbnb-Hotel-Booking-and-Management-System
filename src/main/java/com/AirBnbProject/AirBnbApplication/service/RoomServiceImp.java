package com.AirBnbProject.AirBnbApplication.service;

import com.AirBnbProject.AirBnbApplication.dto.RoomDto;
import com.AirBnbProject.AirBnbApplication.entity.Hotel;
import com.AirBnbProject.AirBnbApplication.entity.Room;
import com.AirBnbProject.AirBnbApplication.exception.ResourceNotFoundException;
import com.AirBnbProject.AirBnbApplication.repository.HotelRepository;
import com.AirBnbProject.AirBnbApplication.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImp  implements RoomService{

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long HotelId, RoomDto roomDto) {
        log.info("Creating new room of hotel with id:{}",+HotelId);
        Hotel hotel=hotelRepository.findById(HotelId).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id:"+HotelId));
        Room room=modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room=roomRepository.save(room);

        //TODO:create inventory as soon as room is created and if the hotel is active
        if(hotel.isActive()){
          inventoryService.initializeRoomForYear(room);
        }
        return modelMapper.map(room,RoomDto.class);

    }

    @Override
    public RoomDto getRoomById(Long RoomId) {
        log.info("getting room of hotel by id:{}",+RoomId);
        Room room=roomRepository.findById(RoomId).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id:"+RoomId));
        return modelMapper.map(room,RoomDto.class);

    }

    @Override
    public List<RoomDto> getAllRoomsOfHotel(Long HotelId) {
        log.info("getting all rooms of hotel with id:{}",+HotelId);
        Hotel hotel=hotelRepository.findById(HotelId).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id:"+HotelId));
        return hotel.getRooms().stream().map((element)->modelMapper.map(element,RoomDto.class)).collect(Collectors.toList());

    }

    @Override
    public void deleteRoomById(Long RoomId) {
       log.info("deleting the room of id:"+RoomId);
        Room room=roomRepository.findById(RoomId).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id:"+RoomId));
        inventoryService.deleteFutureInventories(room);
        roomRepository.deleteById(RoomId);
    }
}
