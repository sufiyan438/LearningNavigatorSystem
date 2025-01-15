package com.learningNavigator.Learning_Navigator_Project.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class HiddenFeatureController {

    private static final String NUMBER_API_URL = "http://numbersapi.com/";

    @GetMapping("hidden-feature/{number}")
    public ResponseEntity<String> getNumberFact(@PathVariable int number) {
        // String url = NUMBER_API_URL + number + "?json";
        String url = NUMBER_API_URL + number + "?default=Boring+number";
        RestTemplate restTemplate = new RestTemplate();
        String answer = restTemplate.getForObject(url, String.class);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
    
}
