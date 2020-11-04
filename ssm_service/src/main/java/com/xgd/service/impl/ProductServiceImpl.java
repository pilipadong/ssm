package com.xgd.service.impl;

import com.xgd.dao.ProductDao;
import com.xgd.pojo.Product;
import com.xgd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll()  {
        return productDao.findAll();
    }


    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
