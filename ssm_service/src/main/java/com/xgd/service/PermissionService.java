package com.xgd.service;

import com.xgd.pojo.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll();

    void save(Permission permission);
}
