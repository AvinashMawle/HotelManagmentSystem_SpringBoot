package com.airBnb.airBnbProject.service;

import com.airBnb.airBnbProject.dto.HotelDto;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotel);

    HotelDto getHotelById(Long id);
}
