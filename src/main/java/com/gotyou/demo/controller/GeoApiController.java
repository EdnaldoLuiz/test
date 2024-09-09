package com.gotyou.demo.controller;

import com.gotyou.demo.service.GeoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

@Controller
@RequestMapping("/test")
public class GeoApiController {

    private static final Logger logger = LoggerFactory.getLogger(GeoApiController.class);

    @Autowired
    private GeoApiService geoApiService;

    @GetMapping("/fetch")
    public String fetchGeoLocation(HttpServletRequest request, Model model) {
        logger.info("Fetching geolocation data...");
        try {
            Locale userLocale = request.getLocale();
            String country = geoApiService.getUserCountry(userLocale);
            logger.info("User country: " + country);
            geoApiService.fetchAndSaveGeoLocation();
            model.addAttribute("message", "Geolocation data fetched and saved successfully!");
            logger.info("Geolocation data fetched and saved successfully.");
        } catch (Exception e) {
            model.addAttribute("message", "Failed to fetch and save geolocation data: " + e.getMessage());
            logger.error("Failed to fetch and save geolocation data", e);
        }
        return "testando"; // Nome da view
    }

    @GetMapping("/abc")
    public String showGeolocationPage(HttpServletRequest request, Model model) {
        Locale userLocale = request.getLocale();
        String country = geoApiService.getUserCountry(userLocale);
        model.addAttribute("country", "Your Country: " + country);
        return "testando";
    }
}
