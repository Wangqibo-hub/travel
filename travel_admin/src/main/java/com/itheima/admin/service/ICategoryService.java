package com.itheima.admin.service;

import com.itheima.admin.model.Category;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-04 09:59
 */
public interface ICategoryService {


    /**
    * @Description: //获取类别信息的方法
    * @Param: []
    * @Return: java.util.List<com.itheima.admin.model.Category>
    * @Author: Wangqibo
    * @Date: 2020/6/4/0004
    */
    List<Category> findAll();
}
