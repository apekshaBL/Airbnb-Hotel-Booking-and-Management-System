package com.AirBnbProject.AirBnbApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="hotelContactInfo")
public class HotelContactInfo {
    private String address;
    private String phoneNumber;
    private String email;
    private String location;

    
}
