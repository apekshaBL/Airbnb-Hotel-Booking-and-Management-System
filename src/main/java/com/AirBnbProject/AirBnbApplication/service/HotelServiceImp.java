package com.AirBnbProject.AirBnbApplication.service;

import com.AirBnbProject.AirBnbApplication.dto.HotelDto;
import com.AirBnbProject.AirBnbApplication.entity.Hotel;
import com.AirBnbProject.AirBnbApplication.exception.ResourceNotFoundException;
import com.AirBnbProject.AirBnbApplication.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImp implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto){
        log.info("Creating a new hotel with name:{}",hotelDto.getName());
        Hotel hotel=modelMapper.map(hotelDto,Hotel.class);
        hotel.setActive(false);
        hotel=hotelRepository.save(hotel);
        log.info("Created a new hotel with ID:{}",hotelDto.getId());
        return modelMapper.map(hotelDto,HotelDto.class) ;
    }

    @Override
    public HotelDto getHotelById(Long id){
        log.info("getting the hotel with ID:{}",id);
        Hotel hotel=hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id:"+id));
        return modelMapper.map(hotel,HotelDto.class);
    }


}
