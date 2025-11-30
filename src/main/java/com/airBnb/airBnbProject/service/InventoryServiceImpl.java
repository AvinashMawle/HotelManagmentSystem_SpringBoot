package com.airBnb.airBnbProject.service;

import com.airBnb.airBnbProject.entity.Hotel;
import com.airBnb.airBnbProject.entity.Inventory;
import com.airBnb.airBnbProject.entity.Room;
import com.airBnb.airBnbProject.exception.HotelNotFoundException;
import com.airBnb.airBnbProject.repository.HotelRepository;
import com.airBnb.airBnbProject.repository.InventoryRepository;
import com.airBnb.airBnbProject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Inventory getOrCreateInventory(Long hotelId, Long roomId, LocalDate date) {
        // Try to find existing inventory
        return inventoryRepository
                .findByHotelIdAndRoomIdAndDate(hotelId, roomId, date)
                .orElseGet(() -> createNewInventory(hotelId, roomId, date));
    }


    private Inventory createNewInventory(Long hotelId, Long roomId, LocalDate date) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found"));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new HotelNotFoundException("Room not found"));

        if(room.getHotel().getId()!= hotelId){
            throw new IllegalArgumentException(
                    "Invalid inventory request: Room " + roomId + " does not belong to Hotel " + hotelId
            );
        }

        Inventory inv = new Inventory();
        inv.setHotel(hotel);
        inv.setRoom(room);
        inv.setDate(date);
        inv.setBookedCount(0);
        inv.setTotalCount(room.getTotalCount());
        inv.setSurgeFactor(new BigDecimal("1.0"));
        inv.setPrice(room.getBasePrice());
        inv.setCity(hotel.getCity());
        inv.setClosed(false);

        return inventoryRepository.save(inv);
    }

    @Override
    public void increaseBookedCount(Long hotelId, Long roomId, LocalDate date, int roomsCount) {
        Inventory inventory = getOrCreateInventory(hotelId, roomId, date);

        if (inventory.getBookedCount() + roomsCount > inventory.getTotalCount()) {
            throw new HotelNotFoundException("Not enough room availability");
        }

        inventory.setBookedCount(inventory.getBookedCount() + roomsCount);
        inventoryRepository.save(inventory);
    }

    @Override
    public void decreaseBookedCount(Long hotelId, Long roomId, LocalDate date, int roomsCount) {

        Inventory inventory = getOrCreateInventory(hotelId, roomId, date);

        inventory.setBookedCount(Math.max(0, inventory.getBookedCount() - roomsCount));
        inventoryRepository.save(inventory);
    }
}
