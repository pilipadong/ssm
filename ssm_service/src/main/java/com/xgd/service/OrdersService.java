package com.xgd.service;

import com.xgd.pojo.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> findAll(int page, int size)throws Exception;

    Orders findById(String orderId) throws Exception;
}
