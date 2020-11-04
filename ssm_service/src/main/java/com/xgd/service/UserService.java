package com.xgd.service;

import com.xgd.pojo.Role;
import com.xgd.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  extends UserDetailsService  {

    List<UserInfo> findAll();

    void save(UserInfo userInfo) throws Exception;

    UserInfo findByid(String id);

    List<Role> findOtherRoles(String id);

    void addRoleToUser(String userId, String[] roleids);
}
