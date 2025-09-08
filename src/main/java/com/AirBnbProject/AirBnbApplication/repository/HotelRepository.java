package com.AirBnbProject.AirBnbApplication.repository;

import com.AirBnbProject.AirBnbApplication.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
