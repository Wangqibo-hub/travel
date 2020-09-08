package com.itheima.admin.dao;

import com.itheima.admin.model.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-03 20:21
 */
public interface IRouteDao {

    /**
    * @Description: //查询总条数的方法
    * @Param: []
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/3/0003
    */
    int count(@Param("rname") String rname);


    /**
    * @Description: //查询当前页数据列表的方法
    * @Param: [start, length]
    * @Return: java.util.List<com.itheima.admin.model.Route>
    * @Author: Wangqibo
    * @Date: 2020/6/3/0003
    */
    List<Route> findRouteByPage(@Param("start") int start,
                                @Param("length") int length,
                                @Param("rname") String rname);



    /**
    * @Description: //根据rid查询线路的方法
    * @Param: [rid]
    * @Return: com.itheima.admin.model.Route
    * @Author: Wangqibo
    * @Date: 2020/6/4/0004
    */
    Route findRouteById(int rid);


    /**
    * @Description: //更新线路信息的方法
    * @Param: [route]
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/4/0004
    */
    int updateRoute(Route route);

    /**
    * @Description: //删除单条线路的方法
    * @Param: [rid]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/5/0005
    */
    int deleteRoute(int rid);


    /**
    * @Description: //批量删除线路的方法
    * @Param: [rids]
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/5/0005
    */
    int deleteMultipleRoute(String[] rids);


    /**
    * @Description: //添加线路的方法
    * @Param: [routr]
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/6/0006
    */
    int addRoute(Route route);
}
