package com.thang.story.service.role;

import com.thang.story.model.entity.Role;
import com.thang.story.model.entity.User;
import com.thang.story.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return this.roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        this.roleRepository.deleteById(id);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return this.roleRepository.findByName(name);
    }
}
