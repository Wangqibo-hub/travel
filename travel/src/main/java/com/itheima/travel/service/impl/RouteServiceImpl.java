package com.itheima.travel.service.impl;

import com.itheima.travel.dao.IFavoriteDao;
import com.itheima.travel.dao.IRouteDao;
import com.itheima.travel.model.Favorite;
import com.itheima.travel.model.PageBean;
import com.itheima.travel.model.Route;
import com.itheima.travel.service.IRouteService;
import com.itheima.travel.util.DaoFactory;

import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-02 22:13
 */
public class RouteServiceImpl implements IRouteService {

    //实例化dao
    private IRouteDao iRouteDao = DaoFactory.getBean(IRouteDao.class);
    private IFavoriteDao iFavoriteDao = DaoFactory.getBean(IFavoriteDao.class);

    /**
    * @Description: 根据当前页和每页大小获取pageBean返回
    * @Param: [curPage, pageSize]
    * @Return: com.itheima.travel.model.PageBean
    * @Author: Wangqibo
    * @Date: 2020/6/2/0002
    */
    @Override
    public PageBean findFavoriteRoute(int curPage, int pageSize, Map<String, Object> conditionMap) {

        //调用dao方法获取总条数count
        int count = iRouteDao.countForFavorite(conditionMap);

        //调用dao方法获取线路列表
        int start = (curPage - 1) * pageSize;
        int length = pageSize;
        List<Route> routeList = iRouteDao.findFavoriteRankByPage(start, length,conditionMap);

        //封装成pageBean返回
        PageBean<Route> pageBean = PageBean.getPageBean(curPage, pageSize, count, routeList);

        return pageBean;
    }
}
