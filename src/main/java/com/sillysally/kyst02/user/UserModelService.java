package com.sillysally.kyst02.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserModelService implements UserDetailsService {

    private final UserModelRepository userModelRepository;

    public UserModelService(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userModelRepository.findByUsername(username);
    }

   /* public ResponseEntity<UserModel> updateUserModel(Long id, UserModel userModel) {
        
    }

    public ResponseEntity<UserModel> save(UserModel userModel) {
        UserModel userModel1=userModelRepository.save(new UserModel(
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getFamilyMembers(),
                userModel.getUsername(),
                userModel.getPassword(),
                userModel.getAuthorities(),
                userModel.isAccountNonExpired(),
                userModel.isAccountNonLocked(),
                userModel.isCredentialsNonExpired(),
                userModel.isEnabled(),
                userModel.getCreated()));
        return new ResponseEntity("User added successfully" + userModel1, HttpStatus.OK);
    }*/

    // TODO - Save User
    // TODO - Edit User
    // TODO - DELETE User
}
