package com.AirBnbProject.AirBnbApplication.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Embeddable
@Table(name="hotelContactInfo")
public class HotelContactInfo {
    private String address;
    private String phoneNumber;
    private String email;
    private String location;

    
}
