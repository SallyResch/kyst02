package com.sillysally.kyst02.dataObjects;

import com.sillysally.kyst02.user.UserModel;

public interface IUserModelDAO <T>{
    T findUser(String username);
    void save(UserModel userModel);
    void update();
    void delete();
}
