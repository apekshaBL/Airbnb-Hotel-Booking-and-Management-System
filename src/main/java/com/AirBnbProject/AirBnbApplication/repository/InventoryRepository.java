package com.AirBnbProject.AirBnbApplication.repository;

import com.AirBnbProject.AirBnbApplication.entity.Inventory;
import com.AirBnbProject.AirBnbApplication.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    void deleteByDataAfterAndRoom(LocalDate date, Room room);
}
