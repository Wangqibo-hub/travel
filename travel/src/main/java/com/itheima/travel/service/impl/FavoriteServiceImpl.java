package com.itheima.travel.service.impl;

import com.itheima.travel.dao.IFavoriteDao;
import com.itheima.travel.dao.IRouteDao;
import com.itheima.travel.model.Favorite;
import com.itheima.travel.model.PageBean;
import com.itheima.travel.model.Route;
import com.itheima.travel.service.IFavoriteService;
import com.itheima.travel.util.DaoFactory;
import com.itheima.travel.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-01 22:54
 */
public class FavoriteServiceImpl implements IFavoriteService {

    //实例化dao
    private IFavoriteDao iFavoriteDao = DaoFactory.getBean(IFavoriteDao.class);

    @Override
    /**
    * @Description: 跟据uid和rid判断是否有收藏信息
    * @Param: [rid, uid]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/1/0001
    */
    public boolean isFavorite(int rid, int uid) {
        //调用到层方法查询收藏信息
        Favorite favorite = iFavoriteDao.findByRidAndUid(rid, uid);

        if (favorite != null) {
            //收藏信息有效返回true
            return true;
        }

        //收藏信息无效返回false
        return false;
    }


    /**
    * @Description: 根据rid与uid添加收藏，返回最新收藏数量
    * @Param: [rid, uid]
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/2/0002
    */
    public int addFavorite(int rid, int uid){
        //获取sqlSession
        SqlSession session = MybatisUtils.getSession();
        //创建dao接口代理
        IFavoriteDao favoriteDao = session.getMapper(IFavoriteDao.class);
        IRouteDao routeDao = session.getMapper(IRouteDao.class);

        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());

        //调用dao方法添加收藏
        favoriteDao.addFavorite(rid, date, uid);

        //调用dao方法更新收藏数量
        routeDao.updateCount(rid);

        //调用dao方法获取最新收藏数量
        Route route = routeDao.findRouteByrid(rid);

        //释放资源
        MybatisUtils.closeSession(session);

        //返回最新收藏数量
        return route.getCount();
    }

    /**
     * @Description: 获取我的的收藏的方法
     * @Param: [uid, curPage, pageSize]
     * @Return: com.itheima.travel.model.PageBean
     * @Author: Wangqibo
     * @Date: 2020/6/6/0006
     */
    @Override
    public PageBean findMyFavorite(int uid, int curPage, int pageSize){
        //调用dao方法获取总条数
        int count = iFavoriteDao.count(uid);

        //调用dao方法获取当前页数据列表
        List<Favorite> favoriteList = iFavoriteDao.findFavorite(uid, (curPage - 1) * pageSize, pageSize);

        //封装pageBean并返回
        PageBean<Favorite> pageBean = PageBean.getPageBean(curPage, pageSize, count, favoriteList);

        return pageBean;
    }
}
