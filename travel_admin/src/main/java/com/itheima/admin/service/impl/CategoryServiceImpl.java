package com.itheima.admin.service.impl;

import com.itheima.admin.dao.ICategoryDao;
import com.itheima.admin.model.Category;
import com.itheima.admin.service.ICategoryService;
import com.itheima.admin.util.DaoFactory;
import com.itheima.admin.util.FactoryUtil;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-04 09:59
 */
public class CategoryServiceImpl implements ICategoryService {

    //实例化ICategoryDao
    private ICategoryDao iCategoryDao = DaoFactory.getBean(ICategoryDao.class);

    /**
    * @Description: //获取类别信息的方法
    * @Param: []
    * @Return: java.util.List<com.itheima.admin.model.Category>
    * @Author: Wangqibo
    * @Date: 2020/6/4/0004
    */
    @Override
    public List<Category> findAll() {
        //调用dao方法获取类别集合返回
        List<Category> categoryList = iCategoryDao.findAll();
        return categoryList;
    }
}
