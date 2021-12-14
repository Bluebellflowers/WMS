package com.example.demo.service.impl;

import com.example.demo.mapper.ProductDao;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductService")
public class productServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public int addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public int deleteProductById(String id) {
        return productDao.deleteProductById(id);
    }

    @Override
    public int updateProduct(Product product)
    {
        return productDao.updateProduct(product);
    }

    @Override
    public Product queryProductById(String id) {
        return productDao.queryProductById(id);
    }

    @Override
    public List<Product> queryAllProduct() {
        return productDao.queryAllProduct();
    }

    @Override
    public List<Product> getByname(String name) {
        return productDao.getByname(name);
    }

    @Override
    public int add_inputProduct(Product product) {
        return productDao.add_inputProduct(product);
    }

    @Override
    public List<Product> queryAllinputProduct() {
        return productDao.queryAllinputProduct();
    }

    @Override
    public int deliveryProduct(Product product)
    {
        return productDao.deliveryProduct(product);
    }

    @Override
    public int add_outputProduct(Product product) {
        return productDao.add_outputProduct(product);
    }

    @Override
    public List<Product> queryAlloutputProduct() {
        return productDao.queryAlloutputProduct();
    }

    @Override
    public Product queryoutProductById(String id) {
        return productDao.queryoutProductById(id);
    }
}
