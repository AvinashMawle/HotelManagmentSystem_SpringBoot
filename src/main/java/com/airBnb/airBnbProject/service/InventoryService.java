package com.airBnb.airBnbProject.service;

import com.airBnb.airBnbProject.entity.Inventory;

import java.time.LocalDate;

public interface InventoryService {

    Inventory getOrCreateInventory(Long hotelId, Long roomId, LocalDate date);

    void increaseBookedCount(Long hotelId, Long roomId, LocalDate date, int roomsCount);

    void decreaseBookedCount(Long hotelId, Long roomId, LocalDate date, int roomsCount);
}
