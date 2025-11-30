package com.airBnb.airBnbProject.repository;

import com.airBnb.airBnbProject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findByHotelId(Long hotelId);

}
