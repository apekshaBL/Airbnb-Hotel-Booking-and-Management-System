package com.AirBnbProject.AirBnbApplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
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
