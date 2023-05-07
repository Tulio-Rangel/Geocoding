## How to run

#### 1. Clonar el proyecto
```
git clone https://github.com/Tulio-Rangel/Geocoding.git
```

#### 2. Add API Key
In file GeocodingService add your Google Maps API Key (line 15)

#### 3. Run the app
Use your IDE to run the app or use a terminal a run ```mvn spring-boot:run```

#### 3. Test the endpoint
Both points 1st and 2nd can be tested with the following endpoint: ```http://localhost:8080/geocode```
In the query parameters add a query call address and its value must be the addres you want to. the answer must be the latitude, longitude and the neighborhood.

For the 4th point you have to use the following endpoin: ```http://localhost:8080/increment-address```
In the query parameters add a query call address and its value must be the addres you want to. The answer must be the neighborhood.
