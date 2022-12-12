package com.thang.story.service.user;

import com.thang.story.model.dto.UserPrincipal;
import com.thang.story.model.entity.Role;
import com.thang.story.model.entity.User;
import com.thang.story.repository.IUserRepository;
import com.thang.story.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;
    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        return UserPrincipal.build(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return this.userRepository.findByPhone(phone);
    }

    @Override
    public User saveAdmin(User user) {
        Optional<Role> roleOptional = this.roleService.findByName("ROLE_ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(roleOptional.get().getId(), roleOptional.get().getName()));
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        user.setAvatar("default-avatar.jpg");
        user.setEmail("admin@gmail.com");
        user.setPhone("0394910426");
        user.setDateCreated(new Date());
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        Optional<Role> roleOptional = this.roleService.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(roleOptional.get().getId(), roleOptional.get().getName()));
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        user.setAvatar("default-avatar.jpg");
        user.setDateCreated(new Date());
        return userRepository.save(user);
    }

}
