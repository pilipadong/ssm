package com.xgd.service.impl;

import com.xgd.dao.PermissionDao;
import com.xgd.pojo.Permission;
import com.xgd.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl  implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;


    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
