package com.canteen.CanteenManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;


@Service
public class WeatherService {
    private final String API_KEY = "a09b2784a9eb82f161461e6aae585a26";
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public double getCurrentTemperature(String city) {
        String url = BASE_URL + "?q=" + city + "&units=metric&appid=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        var response = restTemplate.getForObject(url, Map.class);
        Map<String, Object> main = (Map<String, Object>) response.get("main");
        return Double.parseDouble(main.get("temp").toString());
    }
}
