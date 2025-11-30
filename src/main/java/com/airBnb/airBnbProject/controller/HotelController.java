package com.airBnb.airBnbProject.controller;

import com.airBnb.airBnbProject.dto.HotelDto;
import com.airBnb.airBnbProject.entity.Hotel;
import com.airBnb.airBnbProject.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/admin/hotels")
public class HotelController {

    private static final Logger log = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createNewHotel(@RequestBody HotelDto hotelDto){

        log.info("CONTROLLER: Received Create Hotel Request = {}", hotelDto);

        HotelDto hotelDto1 = hotelService.createNewHotel(hotelDto);
        return new ResponseEntity<>(hotelDto1,HttpStatus.CREATED);
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId){
        HotelDto hotelDto = hotelService.getHotelById(hotelId);
        return  new ResponseEntity<>(hotelDto,HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> updateHotel(
            @PathVariable Long id,
            @RequestBody HotelDto hotelDto) {

        HotelDto updated = hotelService.updateHotelById(id, hotelDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotelById(id);
        return ResponseEntity.ok("Hotel deleted successfully with ID: " + id);
    }

}
