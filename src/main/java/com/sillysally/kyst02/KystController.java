package com.sillysally.kyst02;

import com.sillysally.kyst02.configurations.KystPasswordConfig;
import com.sillysally.kyst02.user.UserModel;
import com.sillysally.kyst02.user.UserModelRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//webMvc

@Controller
public class KystController {

    private final UserModelRepository userModelRepository;
    private final KystPasswordConfig kystPasswordConfig;

    public KystController(UserModelRepository userModelRepository, KystPasswordConfig kystPasswordConfig) {
        this.userModelRepository = userModelRepository;
        this.kystPasswordConfig = kystPasswordConfig;
    }

    @GetMapping("/register")
    public String registerUser(@Valid UserModel userModel){
        return "registerPage";
    }

    @PostMapping ("/register")
    public String registerUser(@Valid UserModel userModel, BindingResult result, Model model){

        if (result.hasErrors()){
            return "registerPage";
        }
        userModel.setPassword(kystPasswordConfig.bCryptEncoder().encode(userModel.getPassword()));
        userModel.setAccountNonExpired(true);
        userModel.setAccountNonLocked(true);
        userModel.setCredentialsNonExpired(true);
        userModel.setEnabled(true);

        System.out.println(userModel);
        userModelRepository.save(userModel);
        return "homePage";
    }
}
