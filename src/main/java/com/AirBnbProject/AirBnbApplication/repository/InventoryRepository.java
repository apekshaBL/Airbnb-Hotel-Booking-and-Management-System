package com.AirBnbProject.AirBnbApplication.repository;

import com.AirBnbProject.AirBnbApplication.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
