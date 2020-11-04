package com.xgd.service;

import com.xgd.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    List<Product> findAll( );

    void save(Product product);
}
