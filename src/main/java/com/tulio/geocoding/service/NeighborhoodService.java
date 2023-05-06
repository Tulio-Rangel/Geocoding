package com.tulio.geocoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.tulio.geocoding.config.AppConfig;

@Service
@Import(AppConfig.class)
public class NeighborhoodService {

    private static final String ENDPOINT_URL = "https://services.arcgis.com/P3ePLMYs2RVChkJx/ArcGIS/rest/services/USA_Neighborhoods/FeatureServer/0/query";

    @Autowired
    private RestTemplate restTemplate;


    public String getNeighborhoodName(double latitude, double longitude) {
        String where = String.format("contains(geom, POINT(%f %f))", longitude, latitude);
        String queryUrl = String.format("%s?where=%s&outFields=NAME&returnGeometry=false&f=json", ENDPOINT_URL, where);
        NeighborhoodQueryResult result = restTemplate.getForObject(queryUrl, NeighborhoodQueryResult.class);
        if (result != null && result.features != null && result.features.length > 0) {
            return result.features[0].attributes.NAME;
        } else {
            return null;
        }
    }

    private static class NeighborhoodQueryResult {
        public NeighborhoodFeature[] features;
    }

    private static class NeighborhoodFeature {
        public NeighborhoodAttributes attributes;
    }

    private static class NeighborhoodAttributes {
        public String NAME;
    }
}

