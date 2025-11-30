package com.airBnb.airBnbProject.controller;

import com.airBnb.airBnbProject.entity.Inventory;
import com.airBnb.airBnbProject.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin/inventory")
public class InventoryController {


    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{hotelId}/{roomId}/{date}")
    public Inventory getInventory(
            @PathVariable Long hotelId,
            @PathVariable Long roomId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return inventoryService.getOrCreateInventory(hotelId, roomId, date);
    }

}
