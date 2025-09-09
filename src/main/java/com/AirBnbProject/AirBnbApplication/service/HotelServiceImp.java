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
    public void deleteHotelById(Long id) {
        boolean exists=hotelRepository.existsById(id);
        if(!exists)throw new ResourceNotFoundException("Hotel not found with id:"+id);
        hotelRepository.deleteById(id);
    }

    @Override
    public void activateHotel(Long id) {
        log.info("Activating the hotel  with ID:{}",id);
        Hotel hotel=hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("NO such Hotel is found of id:"+id));
        hotel.setActive(true);
    }

}
