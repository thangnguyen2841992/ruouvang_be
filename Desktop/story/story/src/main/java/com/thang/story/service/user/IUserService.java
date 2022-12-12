package com.thang.story.service.user;

import com.thang.story.model.entity.User;
import com.thang.story.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByUsername(String username);


    Optional<User> findByEmail(String email);


    Optional<User> findByPhone(String phone);

    User saveAdmin(User user);

    User saveUser(User user);
}
