package com.itheima.travel.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itheima.travel.dao.ICategoryDao;
import com.itheima.travel.model.Category;
import com.itheima.travel.util.DaoFactory;
import com.itheima.travel.util.JedisUtil;
import com.itheima.travel.util.ObjectMapperUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-30 23:11
 */
public class CategoryService {

    //实例化Dao
    private ICategoryDao iCategoryDao = DaoFactory.getBean(ICategoryDao.class);

    /**
    * @Description: 查询分类数据列表的方法
    * @Param: []
    * @Return: java.lang.String
    * @Author: Wangqibo
    * @Date: 2020/5/30/0030
    */
    public String findCategory() throws Exception {

        //从缓存中获取集合数据
        Jedis jedis = JedisUtil.getJedis();
        String categoryList = jedis.get("categoryList");

        //判断数据是否有效
        if (categoryList == null) {
            //无效，从数据库获取集合数据，写入缓存
            List<Category> list = iCategoryDao.findAll();
            categoryList = ObjectMapperUtil.getObjectMapper().writeValueAsString(list);

            jedis.set("categoryList",categoryList);
        }

        //关闭连接
        jedis.close();

        //有效，转换成json字符串返回
        return categoryList;
    }
}
