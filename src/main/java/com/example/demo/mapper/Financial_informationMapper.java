package com.example.demo.mapper;

import com.example.demo.model.Financial_information;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Financial_informationMapper {
    //添加一条财务信息
    @Insert("insert into financial_information(id,productID,name,time,money) values (#{id},#{productID},#{name},#{time},#{money})")
    void add(Financial_information financial_information);
    //根据货物名称查询财务信息
    @Select("select * from financial_information where productID = #{productID}")
    List<Financial_information> queryinformationByproduct(@Param("productID") String productID);
    //查询所哟财务信息
    @Select("select * from financial_information")
    List<Financial_information> queryAllinformation();
    //
    @Select("select * from financial_information where id = #{id}")
    Financial_information queryinformationByid(@Param("id") String id);
}
