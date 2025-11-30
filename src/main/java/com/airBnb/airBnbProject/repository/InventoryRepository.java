package com.airBnb.airBnbProject.repository;

import com.airBnb.airBnbProject.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByHotelIdAndRoomIdAndDate(Long hotelId, Long roomId, LocalDate date);

    List<Inventory> findByHotelIdAndRoomId(Long hotelId, Long roomId);
}

