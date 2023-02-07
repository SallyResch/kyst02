package com.sillysally.kyst02.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);
    void deleteByUsername(String username);

    User findUserById(Long id);
}
