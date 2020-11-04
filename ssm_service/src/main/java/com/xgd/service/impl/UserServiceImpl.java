package com.xgd.service.impl;

import com.xgd.dao.UserDao;
import com.xgd.pojo.Role;
import com.xgd.pojo.UserInfo;
import com.xgd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsernaem(username);
        User  user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        System.out.println(user);
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roleslist) {
        List<SimpleGrantedAuthority>  list = new ArrayList<>();
        for (Role role : roleslist) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    //查询全部用户
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    //添加用户
    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findByid(String id) {
        return userDao.findByid(id);
    }


    @Override
    public List<Role> findOtherRoles(String id) {
        return userDao.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleids) {

        for (String roleid : roleids) {
            userDao.addRoleToUser(userId,roleid);

        }


    }
}
