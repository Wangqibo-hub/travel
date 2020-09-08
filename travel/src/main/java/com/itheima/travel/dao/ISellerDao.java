package com.itheima.travel.dao;

import com.itheima.travel.model.Seller;
import org.apache.ibatis.annotations.Select;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-01 20:31
 */
public interface ISellerDao {


    /**
    * @Description: 根据sid查询Seller
    * @Param: [Sid]
    * @Return: com.itheima.travel.model.Seller
    * @Author: Wangqibo
    * @Date: 2020/6/1/0001
    */
    @Select("select * from tab_seller where sid = #{sid}")
    Seller findSellerBySid(int Sid);
}
