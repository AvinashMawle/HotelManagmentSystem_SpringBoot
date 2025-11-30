package com.airBnb.airBnbProject.service;

import com.airBnb.airBnbProject.dto.HotelDto;
import com.airBnb.airBnbProject.entity.Hotel;
import com.airBnb.airBnbProject.entity.HotelContactInfo;
import com.airBnb.airBnbProject.exception.HotelNotFoundException;
import com.airBnb.airBnbProject.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class HotelServiceImpl implements HotelService{


    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {

        // Convert DTO to Entity
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);

        // ID is ignored automatically because it's null in new DTO
        Hotel savedHotel = hotelRepository.save(hotel);

        // Convert back to DTO and return
        return modelMapper.map(savedHotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        // Throw custom exception if not found
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

        return modelMapper.map(hotel, HotelDto.class);
    }



    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {

        Hotel existing = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

        // Update only fields present in DTO (Put or Patch style)
        existing.setName(hotelDto.getName());
        existing.setCity(hotelDto.getCity());
        existing.setPhotos(hotelDto.getPhotos());
        existing.setAmenities(hotelDto.getAmenities());
        existing.setActive(hotelDto.getActive());

        if (hotelDto.getContactInfo() != null) {
            existing.setContactInfo(modelMapper.map(hotelDto.getContactInfo(), HotelContactInfo.class));
        }

        Hotel updated = hotelRepository.save(existing);

        return modelMapper.map(updated, HotelDto.class);
    }

    @Override
    public void deleteHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

        hotelRepository.delete(hotel);
    }
}
