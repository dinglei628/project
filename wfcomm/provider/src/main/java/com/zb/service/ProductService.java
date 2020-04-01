package com.zb.service;

import com.zb.entity.product;
import com.zb.entity.takeout;
import com.zb.mapper.ProductMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    @GetMapping("/show")
    public List<product> show(){
       return productMapper.getSel();
    }

    @PostMapping("insert")
    public String insert(@RequestBody takeout t, @RequestParam(value = "quantity",required = false) int quantity,
                         @RequestParam(value = "productId") int id){
        int insert = productMapper.insert(t);
        if(insert == 1){
            productMapper.update(quantity,id);
            return "添加成功";
        }else{
            return "添加失败";
        }
    }

}
