package com.sillysally.kyst02;

import com.sillysally.kyst02.authorities.UserRoles;
import com.sillysally.kyst02.configurations.KystPasswordConfig;
import com.sillysally.kyst02.user.UserModel;
import com.sillysally.kyst02.user.UserModelRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

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
    public String displayUser(@Valid UserModel userModel){
        return "registerPage";
    }

    @PostMapping ("/register")
    public String registerUser(@Valid UserModel userModel, BindingResult result, Model model){

        if (result.hasErrors()){
            return "registerPage";
        }
        String role = String.valueOf(userModel.getAuthorities().iterator().next());
        switch(role){
            case "Admin" -> userModel.setAuthorities(UserRoles.ADMIN.getGrantedAuthorities());
            case "User" -> userModel.setAuthorities(UserRoles.USER.getGrantedAuthorities());
            case "Company" -> userModel.setAuthorities(UserRoles.COMPANY.getGrantedAuthorities());
        }
        userModel.setUsername(userModel.getUsername());
        userModel.setPassword(kystPasswordConfig.bCryptEncoder().encode(userModel.getPassword()));
        userModel.setAccountNonExpired(true);
        userModel.setAccountNonLocked(true);
        userModel.setCredentialsNonExpired(true);
        userModel.setEnabled(true);

        System.out.println(userModel);
        userModelRepository.save(userModel);
        return "loginPage";
    }

    @PutMapping("/user")
    public String changeUser(@Valid UserModel userModel){

        return "userPage";
    }
}
