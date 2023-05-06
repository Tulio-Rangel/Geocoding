package com.tulio.geocoding.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;

@Service
public class GeocodingService {

    public LatLng geocodeAddress(String address) throws Exception {
        // Set up the GeoApiContext with your API key
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDO7z5V8YCrI6zEhpRoy7JrIi1yqn3zoxM")
                .build();

        // Make the geocoding API request
        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();

        // Extract the latitude and longitude from the result
        LatLng location = results[0].geometry.location;
        return location;
    }
}

