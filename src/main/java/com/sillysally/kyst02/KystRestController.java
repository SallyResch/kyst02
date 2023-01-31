package com.sillysally.kyst02;

import com.sillysally.kyst02.authorities.UserRoles;
import com.sillysally.kyst02.configurations.KystPasswordConfig;
import com.sillysally.kyst02.user.UserModel;
import com.sillysally.kyst02.user.UserModelRepository;
import com.sillysally.kyst02.user.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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

    @GetMapping ("/saveUser")
    public UserModel saveUser(){
        UserModel user = new UserModel(
                "Sally",
                "Resch",
                4,
                "SallyRally",
                bcrypt.bCryptEncoder().encode("password123"),
                UserRoles.ADMIN.getGrantedAuthorities(),
                true,
                true,
                true,
                true
        );
        return userModelRepository.save(user);
    }

    @GetMapping ("/encode")
    public String testEncode(){
        bcrypt.bCryptEncoder().matches("","");
        return bcrypt.bCryptEncoder().encode("password");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testAdminPermission(){
        return "only admins can enter";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String testUserPermission(){
        return "Only user can enter";
    }

}
