package com.AirBnbProject.AirBnbApplication.service;

import com.AirBnbProject.AirBnbApplication.dto.HotelDto;
import com.AirBnbProject.AirBnbApplication.dto.HotelInfoDto;
import com.AirBnbProject.AirBnbApplication.dto.RoomDto;
import com.AirBnbProject.AirBnbApplication.entity.Hotel;
import com.AirBnbProject.AirBnbApplication.entity.Room;
import com.AirBnbProject.AirBnbApplication.exception.ResourceNotFoundException;
import com.AirBnbProject.AirBnbApplication.repository.HotelRepository;
import com.AirBnbProject.AirBnbApplication.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImp implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto){
        log.info("Creating a new hotel with name:{}",hotelDto.getName());
        Hotel hotel=modelMapper.map(hotelDto,Hotel.class);
        hotel.setActive(true);
        Hotel Savehotel =hotelRepository.save(hotel);
        log.info("Created a new hotel with ID:{}",hotelDto.getId());
        return modelMapper.map(Savehotel,HotelDto.class) ;
    }

    @Override
    public HotelDto getHotelById(Long id){
        log.info("getting the hotel with ID:{}",id);
        Hotel hotel=hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id:"+id));
        return modelMapper.map(hotel,HotelDto.class);
    }
    @Override
    public HotelDto updateHotelById(Long id,HotelDto hotelDto){
        log.info("updating hotel information with ID:{}",id);
        Hotel hotel=hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("NO such Hotel is found of id:"+id));
        modelMapper.map(hotelDto,hotel);
        hotel.setId(id);
        hotel=hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDto.class);

    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        Hotel hotel=hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("NO such Hotel is found of id:"+id));
        for(Room room:hotel.getRooms()){
            inventoryService.deleteAllInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void activateHotel(Long id) {
        log.info("Activating the hotel  with ID:{}",id);
        Hotel hotel=hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("NO such Hotel is found of id:"+id));
        hotel.setActive(true);
        //TODO: Create inventory for all the rooms for this hotel
         for(Room room:hotel.getRooms()){
             inventoryService.initializeRoomForYear(room);
         }
    }

    @Override
    public HotelInfoDto getHotelInfoById(Long hotelId) {
        Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("NO such Hotel is found of id:"+hotelId));
        List<RoomDto>rooms=hotel.getRooms().stream()
                .map((element)->modelMapper.map(element,RoomDto.class)).toList();
        return new HotelInfoDto(modelMapper.map(hotel,HotelDto.class),rooms);
    }

}
