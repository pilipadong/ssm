package com.xgd.service.impl;

import com.github.pagehelper.PageHelper;
import com.xgd.dao.OrdersDao;
import com.xgd.pojo.Orders;
import com.xgd.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

   public  List<Orders> findAll(int page, int size)throws Exception{
       //参数pageNum是页码    pageSize  每页显示条数
       PageHelper.startPage(page,size);

       return ordersDao.findAll();
   }

    @Override
    public Orders findById(String orderId) throws Exception{
        return ordersDao.findByOrderId(orderId);
    }
}
