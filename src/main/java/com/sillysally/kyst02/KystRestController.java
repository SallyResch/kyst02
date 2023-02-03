package com.sillysally.kyst02;

import com.sillysally.kyst02.authorities.UserRoles;
import com.sillysally.kyst02.configurations.KystPasswordConfig;

import com.sillysally.kyst02.user.UserModel;
import com.sillysally.kyst02.user.UserModelRepository;
import com.sillysally.kyst02.user.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/saveBenny")
    public UserModel saveUserBenny() {

        UserModel benny = new UserModel(
                "Benny",
                "Karlsson",
                6,
                "Benny",
                bcrypt.bCryptEncoder().encode("123"),
                UserRoles.USER.getGrantedAuthorities(),
                true,
                true,
                true,
                true
        );

        System.out.println(benny);

        return userModelRepository.save(benny);
    }

    @GetMapping("/find/{username}")
    public UserModel findByUsername(@PathVariable String username) {

        System.out.println(userModelService.loadUserByUsername(username));

        return (UserModel) userModelService.loadUserByUsername(username);
    }



    @GetMapping("/encode")
    public String testEncoding() {

        bcrypt.bCryptEncoder().matches("", "");

        return bcrypt.bCryptEncoder().encode("password");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testAdminPermission() {

        return "Only admins can enter";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String testUserPermission() {

        return "Only user can enter";
    }

    // Logical
    @GetMapping("/unknown")
    @PreAuthorize("hasRole('ROLE_ADMIN') " + " && " +
            "hasAuthority('user:read') ")
    public String testUnknownPermission() {

        return "This should NEVER work";
    }

}
