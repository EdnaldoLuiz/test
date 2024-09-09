package com.gotyou.demo.service;

import com.gotyou.demo.model.GeoLocation;
import com.gotyou.demo.repository.GeoLocationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class GeoApiService {

    @Autowired
    private GeoLocationRepository geoLocationRepository;

    public String getUserCountry(Locale userLocale) {
        return userLocale.getCountry();
    }

    public void fetchAndSaveGeoLocation() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.geoapify.com/v1/ipinfo?&apiKey=83d0567ac6ca4b85b7213202936ad1d9")
                .get()
                .build();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful() && response.body() != null) {
            String responseBody = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);

            GeoLocation geoLocation = new GeoLocation();
            geoLocation.setIp(rootNode.path("ip").asText());
            geoLocation.setCityName(rootNode.path("city").path("name").asText());
            geoLocation.setContinentCode(rootNode.path("continent").path("code").asText());
            geoLocation.setContinentName(rootNode.path("continent").path("name").asText());
            geoLocation.setCountryName(rootNode.path("country").path("name").asText());
            geoLocation.setCountryCode(rootNode.path("country").path("iso_code").asText());
            geoLocation.setStateName(rootNode.path("state").path("name").asText());
            geoLocation.setCapital(rootNode.path("country").path("capital").asText());
            geoLocation.setCurrency(rootNode.path("country").path("currency").asText());
            geoLocation.setFlag(rootNode.path("country").path("flag").asText());
            geoLocation.setLatitude(rootNode.path("location").path("latitude").asDouble());
            geoLocation.setLongitude(rootNode.path("location").path("longitude").asDouble());

            List<String> languages = new ArrayList<>();
            rootNode.path("country").path("languages").forEach(lang -> languages.add(lang.path("name").asText()));
            geoLocation.setLanguages(languages);

            geoLocationRepository.save(geoLocation);
        }
    }
}
