package com.sillysally.kyst02.dataObjects;

import com.sillysally.kyst02.user.UserModel;
import com.sillysally.kyst02.user.UserModelRepository;
import org.springframework.stereotype.Component;

@Component
public class UserModelDAO implements IUserModelDAO<UserModel>{
    private final UserModelRepository userModelRepository;


    public UserModelDAO(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public UserModel findUser(String username) {
        return userModelRepository.findByUsername(username);
    }

    @Override
    public void save(UserModel userModel) {
        userModelRepository.save(userModel);
    }

    @Override
    public void update() {
    userModelRepository
    }

    @Override
    public void delete(String username) {
    userModelRepository.deleteByUsername(username);
    }
}
