package com.itheima.travel.service;

import com.itheima.travel.dao.IRouteDao;
import com.itheima.travel.model.PageBean;
import com.itheima.travel.model.Route;
import com.itheima.travel.util.DaoFactory;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-31 10:55
 */
public class RouteService {
    //实例化dao
    private IRouteDao iRouteDao = DaoFactory.getBean(IRouteDao.class);

    /**
    * @Description: 获取PageBean的方法
    * @Param: [cid, rname, curPage, pageSize]
    * @Return: com.itheima.travel.model.PageBean
    * @Author: Wangqibo
    * @Date: 2020/5/31/0031
    */
    public PageBean findByPage(int cid, String rname, int curPage, int pageSize){
        //调用dao方法获取总条数
        int count = iRouteDao.count(cid,rname);

        //调用dao方法获取当前页数据列表
        int start = (curPage - 1)*pageSize;
        int length = pageSize;
        List<Route> routeList = iRouteDao.findByPage(cid, rname, start, length);

        //封装pageBean并返回
        PageBean<Route> pageBean = PageBean.getPageBean(curPage, pageSize, count, routeList);

        return pageBean;
    }

    /**
    * @Description: 调用dao方法，获取Route对象
    * @Param: [rid]
    * @Return: com.itheima.travel.model.Route
    * @Author: Wangqibo
    * @Date: 2020/6/1/0001
    */
    public Route findRouteByRid(int rid){
        //调用dao方法获取Route对象返回
        Route route = iRouteDao.findRouteByrid(rid);
        return route;
    }
}
