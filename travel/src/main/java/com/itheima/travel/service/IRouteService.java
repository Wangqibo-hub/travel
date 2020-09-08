package com.itheima.travel.service;

import com.itheima.travel.model.PageBean;

import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-02 22:11
 */
public interface IRouteService {


    /**
    * @Description: 根据当前页和每页大小获取pageBean返回
    * @Param: [curPage, pageSize]
    * @Return: com.itheima.travel.model.PageBean
    * @Author: Wangqibo
    * @Date: 2020/6/2/0002
    */
    PageBean findFavoriteRoute(int curPage, int pageSize, Map<String, Object> conditionMap);

}
