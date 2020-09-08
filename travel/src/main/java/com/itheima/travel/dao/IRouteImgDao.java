package com.itheima.travel.dao;

import com.itheima.travel.model.RouteImg;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-01 20:33
 */
public interface IRouteImgDao {

    /**
    * @Description: 根据rid查询RouteImg集合
    * @Param: [rid]
    * @Return: java.util.List<com.itheima.travel.model.RouteImg>
    * @Author: Wangqibo
    * @Date: 2020/6/1/0001
    */
    @Select("select * from tab_route_img where rid = #{rid}")
    List<RouteImg> findRouteImgByRid(int rid);
}
