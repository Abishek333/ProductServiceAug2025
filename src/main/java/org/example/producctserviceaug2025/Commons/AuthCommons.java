package org.example.producctserviceaug2025.Commons;

import org.example.producctserviceaug2025.DTO.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class AuthCommons {
    private static RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static boolean validateToken(String token) {
        UserDto userDto = restTemplate.getForObject("http://localhost:8080/user/validateToken"+ token, UserDto.class);
        if(userDto == null){
            return false;
        }
        return true;
    }

}
