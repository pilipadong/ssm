package com.xgd.service.impl;

import com.xgd.dao.RoleDao;
import com.xgd.pojo.Permission;
import com.xgd.pojo.Role;
import com.xgd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceimpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Permission> findByRoleIdOtherPermission(String id) {
        return roleDao.findByRoleIdOtherPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionids) {
        for (String permissionid : permissionids) {
            roleDao.addPermissionToRole(roleId,permissionid);
        }
    }
}
