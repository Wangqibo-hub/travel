package com.itheima.admin.service;

import com.itheima.admin.model.PageBean;
import com.itheima.admin.model.Route;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-03 20:26
 */
public interface IRouteService {


    /**
    * @Description: //查询当前页数据封装到pageBean
    * @Param: [curPage, pageSize]
    * @Return: com.itheima.admin.model.PageBean
    * @Author: Wangqibo
    * @Date: 2020/6/3/0003
    */
    PageBean getPageBean(int curPage, int pageSize, String rname);


    /**
    * @Description: //根据rid查询线路信息的方法
    * @Param: [rid]
    * @Return: com.itheima.admin.model.Route
    * @Author: Wangqibo
    * @Date: 2020/6/4/0004
    */
    Route findRouteById(int rid);


    /**
    * @Description: //更新线路信息的方法
    * @Param: [route]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/4/0004
    */
    boolean updateRoute(Route route);

    /**
    * @Description: //删除单条线路的方法
    * @Param: [rid]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/5/0005
    */
    boolean deleteRoute(int rid);


    /**
    * @Description: //批量删除线路的方法
    * @Param: [rids]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/5/0005
    */
    boolean delMultipleRoute(String[] rids);


    /**
    * @Description: //添加线路的方法
    * @Param: [route]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/6/0006
    */
    boolean addRoute(Route route);
}
