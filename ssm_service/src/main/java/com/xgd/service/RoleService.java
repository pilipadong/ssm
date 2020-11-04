package com.xgd.service;

import com.xgd.pojo.Permission;
import com.xgd.pojo.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void save(Role role);

    List<Permission> findByRoleIdOtherPermission(String id);

    void addPermissionToRole(String roleId, String[] permissionids);
}
