package com.gotyou.demo.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "geo_location")
public class GeoLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;
    private String cityName;
    private String continentCode;
    private String continentName;
    private String countryName;
    private String countryCode;
    private String stateName;
    private String capital;
    private String currency;
    private String flag;
    private Double latitude;
    private Double longitude;

    @ElementCollection
    private List<String> languages;

}
