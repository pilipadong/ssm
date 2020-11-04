package com.xgd.dao;

import com.xgd.pojo.Member;
import com.xgd.pojo.Orders;
import com.xgd.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrdersDao {


    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "PRODUCTID", javaType = Product.class,one = @One(select = "com.xgd.dao.ProductDao.findById")),
    })
    List<Orders>  findAll()throws Exception;

    @Select("select * from orders where id=#{orderId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "PRODUCTID", javaType = Product.class,one = @One(select = "com.xgd.dao.ProductDao.findById")),
            @Result(property = "member",column = "MEMBERID", javaType = Member.class,one = @One(select = "com.xgd.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id", javaType = List.class,many = @Many(select = "com.xgd.dao.TravellerDao.findByOrderId")),
    })
    Orders  findByOrderId(String orderId)throws  Exception;

}
