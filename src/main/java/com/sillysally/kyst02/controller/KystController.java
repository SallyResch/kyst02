package com.sillysally.kyst02.controller;

import com.sillysally.kyst02.authorities.UserRoles;
import com.sillysally.kyst02.configurations.KystPasswordConfig;
import com.sillysally.kyst02.user.UserModel;
import com.sillysally.kyst02.user.UserModelRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String displayRegisterUser(UserModel userModel){

        return "registerPage";
    }

    @PostMapping ("/register")
    public String registerUser(@Valid UserModel userModel, BindingResult result, Model model){

        if (result.hasErrors()){
            return "registerPage";
        }
        String role = String.valueOf(userModel.getAuthorities().iterator().next());
        switch (role){
            case "Admin" -> userModel.setAuthorities(UserRoles.ADMIN.getGrantedAuthorities());
            case "User" -> userModel.setAuthorities(UserRoles.USER.getGrantedAuthorities());
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

    @GetMapping ("/delete/{username}")
    public String deleteUser(@PathVariable(name="username")String username){
        UserModel user = userModelRepository.findByUsername(username);
        userModelRepository.delete(user);
        return "redirect:/kyst";
    }

    /*@GetMapping ("/update/{id}")
    public UserModelRepository showUpdatePage(@PathVariable(name = "id") Long id){
        UserModelRepository updateView = new UserModelRepository("update-user-form");
        User user = userModelRepository.findUserById(id);
        updateView.add("user",user);
        return updateView;
    }*/

}
