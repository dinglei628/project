package com.demo.mapper;

import com.demo.entity.Product;
import com.demo.entity.Sale;
import com.demo.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {

    Users getLogin(@Param("userName")String userName);

    List<Product> getSel();

    int insert(Sale s);

    int update(Product p);

    List<Sale> getSaleList(@Param("userId")int userId,@Param("a")int a,@Param("from")int from,@Param("pageSize")int pageSize);

    int getSaleCount(@Param("userId")int userId,@Param("a")int a);

    Product getOne(@Param("productName")String productName);
}
