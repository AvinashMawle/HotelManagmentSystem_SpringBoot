package com.airBnb.airBnbProject.service;

import com.airBnb.airBnbProject.dto.RoomDto;
import com.airBnb.airBnbProject.entity.Hotel;
import com.airBnb.airBnbProject.entity.Room;
import com.airBnb.airBnbProject.exception.HotelNotFoundException;
import com.airBnb.airBnbProject.repository.HotelRepository;
import com.airBnb.airBnbProject.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{

    private static final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoomDto createRoom(RoomDto roomDto) {

        // Find hotel (required field)
        Hotel hotel = hotelRepository.findById(roomDto.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Given hotel not present in DB"));

        // Convert DTO → Entity
        Room room = modelMapper.map(roomDto, Room.class);

        // Set foreign key link
        room.setHotel(hotel);

        // Save to DB
        Room savedRoom = roomRepository.save(room);

        // Convert back Entity → DTO
        RoomDto response = modelMapper.map(savedRoom, RoomDto.class);
        response.setHotelId(savedRoom.getHotel().getId());

        return response;
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {

        // Validate hotel existence
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Given hotel not present in DB"));
        // Fetch rooms for this hotel
        List<Room> rooms = roomRepository.findByHotelId(hotelId);

        // Convert List<Entity> → List<DTO>
        List<RoomDto> response = rooms.stream()
                .map(room -> {
                    RoomDto dto = modelMapper.map(room, RoomDto.class);
                    dto.setHotelId(hotelId); // set manually
                    return dto;
                })
                .toList();

        return response;
    }

    @Override
    public RoomDto getRoomById(Long roomId) {

        Room room = roomRepository.findById(roomId).orElseThrow(()-> new HotelNotFoundException("Room Not Found"));
        //        log.info(room);
        RoomDto roomDto = modelMapper.map(room,RoomDto.class);
        roomDto.setHotelId(room.getHotel().getId());

        return roomDto;
    }

    @Override
    public void deleteRoomById(Long roomId) {

    }
}
