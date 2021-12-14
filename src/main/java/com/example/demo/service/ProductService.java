package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {
    //对product库的操作
    int addProduct(Product product);

    int deleteProductById(String id);

    int updateProduct(Product product);

    Product queryProductById(String id);

    List<Product> queryAllProduct();

    List<Product> getByname(String name);

    int deliveryProduct(Product product);

/////////////////////////////////////////////////////////////////////
    //对input_product库的操作
    int add_inputProduct(Product product);

    List<Product> queryAllinputProduct();

    /////////////////////////////////////////////////////////////////////
    //对out_product库的操作
    int add_outputProduct(Product product);

    List<Product> queryAlloutputProduct();

    Product queryoutProductById(String id);
}
