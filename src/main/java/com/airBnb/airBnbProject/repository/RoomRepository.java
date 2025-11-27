package com.airBnb.airBnbProject.repository;

import com.airBnb.airBnbProject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
