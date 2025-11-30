package com.airBnb.airBnbProject.service;

import com.airBnb.airBnbProject.dto.HotelDto;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotel);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);
}
