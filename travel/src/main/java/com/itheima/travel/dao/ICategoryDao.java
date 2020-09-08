package com.itheima.travel.dao;

import com.itheima.travel.model.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-30 23:09
 */
public interface ICategoryDao {

    /**
    * @Description: 查询分类列表的方法
    * @Param: []
    * @Return: java.util.List<com.itheima.travel.model.Category>
    * @Author: Wangqibo
    * @Date: 2020/5/30/0030
    */
    @Select("select * from tab_category order by cid")
    List<Category> findAll();


    /**
    * @Description: 根据cid查询Category
    * @Param: [cid]
    * @Return: com.itheima.travel.model.Category
    * @Author: Wangqibo
    * @Date: 2020/6/1/0001
    */
    @Select("select * from tab_category where cid = #{cid}")
    Category findCategoryByCid(int cid);
}
