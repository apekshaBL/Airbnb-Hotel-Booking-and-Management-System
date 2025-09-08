package com.AirBnbProject.AirBnbApplication.repository;

import com.AirBnbProject.AirBnbApplication.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
