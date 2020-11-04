package com.xgd.dao;

import com.xgd.pojo.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission (id,permissionname,url) values(role_seq.nextval,#{permissionName},#{url})")
    void save(Permission permission);
}
