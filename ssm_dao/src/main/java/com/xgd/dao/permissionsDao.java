package com.xgd.dao;

import com.xgd.pojo.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface permissionsDao {

    @Select("select * from permission where id in(select permissionid from role_permission where roleid=#{id})")
    public List<Permission> findByid(String id);
}
