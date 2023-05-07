package com.tulio.geocoding.controller;

import com.google.maps.model.LatLng;
import com.tulio.geocoding.service.AddressIncrementer;
import com.tulio.geocoding.service.GeocodingService;
import com.tulio.geocoding.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GeocodingController {

    @Autowired
    private GeocodingService geocodingService;

    @Autowired
    private NeighborhoodService neighborhoodService;

    @Autowired
    private AddressIncrementer addressIncrementer;

    /*@GetMapping("/geocode")
    public LatLng geocode(@RequestParam String address) throws Exception {
        return geocodingService.geocodeAddress(address);
    }*/

    @GetMapping("/geocode")
    public GeocodeResult geocode(@RequestParam String address) throws Exception {
        LatLng latLng = geocodingService.geocodeAddress(address);
        String neighborhood = neighborhoodService.getNeighborhoodName(latLng.lat, latLng.lng);
        return new GeocodeResult(latLng.lat, latLng.lng, neighborhood);
    }

    private static class GeocodeResult {
        public double lat;
        public double lng;
        public String neighborhood;

        public GeocodeResult(double lat, double lng, String neighborhood) {
            this.lat = lat;
            this.lng = lng;
            this.neighborhood = neighborhood;
        }
    }


    @GetMapping("/increment-address")
    public String incrementAddress(@RequestParam String address) throws Exception {
        String neighborhood = addressIncrementer.findNeighborhoodByIncrementingAddress(address);
        return "Neighborhood for " + address + " is " + neighborhood;
    }

}

