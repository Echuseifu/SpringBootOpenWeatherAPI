package com.tts.WeatherApp.service;

import com.tts.WeatherApp.Repository.ZipCodeRepository;
import com.tts.WeatherApp.model.Response;
import com.tts.WeatherApp.model.ZipCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

        @Value("${api_key}")
        private String apiKey;

//    public Response getForecast(String zipCode) {
//        String url = "http://api.openweathermap.org/data/2.5/weather?zip=" +
//                zipCode + "&units=imperial&appid=" + apiKey;
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(url, Response.class);
//    }


        @Autowired
        private ZipCodeRepository zipCodeRepository;

        public Response getForecast(String zipCode) {
            String url = "http://api.openweathermap.org/data/2.5/weather?zip=" +
                    zipCode + "&units=imperial&appid=" + apiKey;

            // we need to save our zipcode argument a a zipcode object
            ZipCode passedZipCode = new ZipCode(zipCode);
            zipCodeRepository.save(passedZipCode);


            RestTemplate restTemplate = new RestTemplate();

            try {
                return restTemplate.getForObject(url, Response.class);
            }
            catch (HttpClientErrorException ex){
                Response response = new Response();
                response.setName("error");
                return response;
            }
        }


        // most recent 10 searches
    public List<ZipCode> getLatestZipCodeSearches(){
            List<ZipCode> zipCodes = new ArrayList<>();
            zipCodeRepository.findAll().forEach(zipCodes::add);// referencing the method directly
           return zipCodes.stream()// allows us to use more functional methods
                    .limit(10)
                    .collect(Collectors.toList());


    }




}
