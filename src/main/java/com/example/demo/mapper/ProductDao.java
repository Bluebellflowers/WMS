package com.example.demo.mapper;

import com.example.demo.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductDao {
    int addProduct(Product product);

    int deleteProductById(String id);

    int updateProduct(Product product);

    Product queryProductById(String id);

    List<Product> queryAllProduct();

    List<Product> getByname(String name);

    int add_inputProduct(Product product);

    List<Product> queryAllinputProduct();

    int deliveryProduct(Product product);

    int add_outputProduct(Product product);

    List<Product> queryAlloutputProduct();

    Product queryoutProductById(String id);
}
