package com.tulio.geocoding.controller;

import com.google.maps.model.LatLng;
import com.tulio.geocoding.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeocodingController {

    @Autowired
    private GeocodingService geocodingService;

    @GetMapping("/geocode")
    public LatLng geocode(@RequestParam String address) throws Exception {
        return geocodingService.geocodeAddress(address);
    }
}

