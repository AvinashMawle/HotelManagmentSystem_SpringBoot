package com.airBnb.airBnbProject.controller;

import com.airBnb.airBnbProject.dto.RoomDto;
import com.airBnb.airBnbProject.entity.Room;
import com.airBnb.airBnbProject.repository.RoomRepository;
import com.airBnb.airBnbProject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;


    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto) {
        RoomDto created = roomService.createRoom(roomDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotelId){
        List<RoomDto> rooms = roomService.getAllRoomsInHotel(hotelId);
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

    @GetMapping("/get-room/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long roomId){

        RoomDto room = roomService.getRoomById(roomId);
        return new ResponseEntity<>(room,HttpStatus.OK);

    }

}
