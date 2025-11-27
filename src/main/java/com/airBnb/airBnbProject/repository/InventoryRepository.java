package com.airBnb.airBnbProject.repository;

import com.airBnb.airBnbProject.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
