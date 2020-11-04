package com.xgd.dao;

import com.xgd.pojo.Role;
import com.xgd.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id=true,property = "id", column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.xgd.dao.RoleDao.findRoleByUserId")),
    })
    public UserInfo findByUsernaem(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users (username,password,email,phoneNum,status)values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id=true,property = "id", column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.xgd.dao.RoleDao.findRoleByUserId")),
    })
    UserInfo findByid(String id);

    @Select("select * from role where id not in (select roleid from users_role where userid=#{id})")
    List<Role> findOtherRoles(String id);


    @Insert("insert into users_role(userid,roleId) values(#{userId},#{roleid})")
    void addRoleToUser(@Param( "userId") String userId,@Param("roleid") String roleid);
}
