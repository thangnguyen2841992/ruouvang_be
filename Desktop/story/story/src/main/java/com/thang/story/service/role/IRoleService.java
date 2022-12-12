package com.thang.story.service.role;


import com.thang.story.model.entity.Role;
import com.thang.story.service.IGeneralService;

import java.util.Optional;

public interface IRoleService extends IGeneralService<Role> {
    Optional<Role> findByName(String name);

}
