package com.gotyou.demo.controller;

import com.gotyou.demo.service.GeoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/test")
public class GeoApiController {

    private static final Logger logger = LoggerFactory.getLogger(GeoApiController.class);

    @Autowired
    private GeoApiService geoApiService;

    @GetMapping("/fetch")
    public String fetchGeoLocation(Model model) {
        logger.info("Fetching geolocation data...");
        try {
            geoApiService.fetchAndSaveGeoLocation();
            model.addAttribute("message", "Te amo meu Amorzinho <3!");
            logger.info("");
        } catch (Exception e) {
            model.addAttribute("message", "Te amo meu Amorzinho <3: " + e.getMessage());
            logger.error("Failed to fetch and save geolocation data", e);
        }
        return "testando";  // Alterado para o novo nome da view
    }

    @GetMapping("/abc")
    public String showGeolocationPage(Model model) {
        return "testando";
    }
}
