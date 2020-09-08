package com.itheima.admin.dao;

import com.itheima.admin.model.Category;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-04 09:54
 */
public interface ICategoryDao {


    /**
    * @Description: //查询类别列表的方法
    * @Param: []
    * @Return: java.util.List<com.itheima.admin.model.Category>
    * @Author: Wangqibo
    * @Date: 2020/6/4/0004
    */
    List<Category> findAll();
}
