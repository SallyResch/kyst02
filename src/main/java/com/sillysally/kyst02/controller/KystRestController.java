package com.sillysally.kyst02.controller;

import com.sillysally.kyst02.api.ApiModel;
import com.sillysally.kyst02.configurations.KystPasswordConfig;

import com.sillysally.kyst02.user.UserModel;
import com.sillysally.kyst02.user.UserModelRepository;
import com.sillysally.kyst02.user.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/rest")
public class KystRestController {

private final KystPasswordConfig bcrypt;
private final UserModelRepository userModelRepository;
private final UserModelService userModelService;

    @Autowired
    public KystRestController(KystPasswordConfig bcrypt,
                              UserModelRepository userModelRepository,
                              UserModelService userModelService){
        this.bcrypt = bcrypt;
        this.userModelRepository = userModelRepository;
        this.userModelService = userModelService;
    }

    @GetMapping("/findAllUsers")
    public List <UserModel> fetchAllCustomers(){
        return userModelRepository.findAll();
    }

    @GetMapping("/find/{username}")
    public UserModel findByUsername(@PathVariable String username) {

        System.out.println(userModelService.loadUserByUsername(username));

        return (UserModel) userModelService.loadUserByUsername(username);
    }
    @DeleteMapping ("/delete/{username}")
    public String deleteUser(@PathVariable("username")String username, Model model){
        UserModel user = userModelRepository.findByUsername(username);
        userModelRepository.delete(user);
        return "User id deleted";
    }

    @GetMapping("/fetchWeatherApi")
    public String fetchWeatherApi(){
        String http="https://api.open-meteo.com/v1/forecast?latitude=59.33&longitude=18.07&hourly=temperature_2m,snowfall";
        RestTemplate restTemplate= new RestTemplate();
        return restTemplate.getForObject(http,String.class);
    }

    @GetMapping("/fetchApi")
    public ApiModel fetchApi() {
        final String url = "https://api.open-meteo.com/v1/forecast?latitude=59.33&longitude=18.07&hourly=temperature_2m,snowfall";
        RestTemplate restTemplate = new RestTemplate();
        ApiModel apiModel;

        try {
            apiModel = restTemplate.getForObject(url, ApiModel.class);
            if (apiModel != null) {
                System.out.println(
                        apiModel.getHourly().getTemperature_2m().get(5));
            }
            return apiModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
