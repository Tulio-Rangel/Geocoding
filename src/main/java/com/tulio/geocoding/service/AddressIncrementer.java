package com.tulio.geocoding.service;

import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressIncrementer {

    @Autowired
    private GeocodingService geocodingService;

    @Autowired
    private NeighborhoodService neighborhoodService;

    public String getNeighborhoodForAddress(String address) throws Exception {
        LatLng latLng = geocodingService.geocodeAddress(address);
        String neighborhood = neighborhoodService.getNeighborhoodName(latLng.lat, latLng.lng);
        System.out.println("Neighborhood for " + address + " is " + neighborhood);
        return neighborhood;
    }

    public String findNeighborhoodByIncrementingAddress(String address) throws Exception {
        String neighborhood = getNeighborhoodForAddress(address);
        String lastAddressNumber = address.replaceAll("[^0-9]+$", "");
        String newAddress;
        do {
            int lastNum = Integer.parseInt(lastAddressNumber);
            int newNum = lastNum + 100;
            newAddress = address.replaceAll("[0-9]+$", Integer.toString(newNum));
            neighborhood = getNeighborhoodForAddress(newAddress);
            lastAddressNumber = Integer.toString(newNum);
        } while (neighborhood != null && neighborhood.equals(getNeighborhoodForAddress(address)));
        return neighborhood;
    }
}

