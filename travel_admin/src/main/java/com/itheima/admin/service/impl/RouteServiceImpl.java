package com.itheima.admin.service.impl;

import com.itheima.admin.dao.IRouteDao;
import com.itheima.admin.model.PageBean;
import com.itheima.admin.model.Route;
import com.itheima.admin.service.IRouteService;
import com.itheima.admin.util.DaoFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-03 20:28
 */
public class RouteServiceImpl implements IRouteService {

    //实例化IRouteDao
    private IRouteDao iRouteDao = DaoFactory.getBean(IRouteDao.class);

    /**
    * @Description: 查询当前页数据封装到pageBean
    * @Param: [curPage, pageSize]
    * @Return: com.itheima.admin.model.PageBean
    * @Author: Wangqibo
    * @Date: 2020/6/3/0003
    */
    @Override
    public PageBean getPageBean(int curPage, int pageSize, String rname) {

        //调用dao方法获取总条数
        int count = iRouteDao.count(rname);

        //调用到方法获取当前页数据列表
        List<Route> routeList = iRouteDao.findRouteByPage((curPage - 1) * pageSize, pageSize, rname);

        //获取pageBean
        PageBean<Route> pageBean = PageBean.getPageBean(curPage, pageSize, count, routeList);

        //返回 PageBean
        return pageBean;
    }


    /**
    * @Description: //根据rid查询线路信息的方法
    * @Param: [rid]
    * @Return: com.itheima.admin.model.Route
    * @Author: Wangqibo
    * @Date: 2020/6/4/0004
    */
    @Override
    public Route findRouteById(int rid){
        //调用dao方法获取Route对象返回
        Route route = iRouteDao.findRouteById(rid);
        return route;
    };


    /**
    * @Description: //更新线路信息的方法
    * @Param: [route]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/4/0004
    */
    @Override
    public boolean updateRoute(Route route){
        //调用dao方法更新线路信息
        int i = iRouteDao.updateRoute(route);

        return i>0;
    };

    /**
    * @Description: //删除单条线路的方法
    * @Param: [rid]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/5/0005
    */
    @Override
    public boolean deleteRoute(int rid){
        //调用dao方法删除线路
        int i = iRouteDao.deleteRoute(rid);
        return i>0;
    };


    /**
    * @Description: //批量删除线路的方法
    * @Param: [rids]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/5/0005
    */
    @Override
    public boolean delMultipleRoute(String[] rids){
        //调用dao方法批量删除线路
        int i = iRouteDao.deleteMultipleRoute(rids);
        return i>0;
    };

    /**
    * @Description: //添加线路的方法
    * @Param: [route]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/6/0006
    */
    @Override
    public boolean addRoute(Route route) {
        //封装剩余数据
        //  上架日期为系统当前日期
        route.setRdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //  是否主题旅游,"0"为否,"1"为是
        route.setIsThemeTour("0");
        //  收藏数量,默认设置为0
        route.setCount(0);
        //  商家id默认为1: 商家为黑马程序员
        route.setSid(1);
        //图片暂时不上传, 设置一个固定路径
        //route.setRimage("img/product/small/m3c9823bc50368af0fe83eff14a5587c0a.jpg");
        //调用dao方法添加Route
        int i = iRouteDao.addRoute(route);
        return i>0;
    }
}
