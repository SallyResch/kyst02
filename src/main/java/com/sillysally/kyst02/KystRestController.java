package com.sillysally.kyst02;

import com.sillysally.kyst02.configurations.KystPasswordConfig;

import com.sillysally.kyst02.user.UserModel;
import com.sillysally.kyst02.user.UserModelRepository;
import com.sillysally.kyst02.user.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
/*
    @PostMapping("/saveUser")
    public UserModel saveUser(@RequestBody UserModel userModel){
        return userModelService.save(userModel).getBody();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable("id") Long id, @RequestBody UserModel userModel){
        System.out.println("Debugging ID" + id);
        return userModelService.updateUserModel(id,userModel);
    }
 */
}
