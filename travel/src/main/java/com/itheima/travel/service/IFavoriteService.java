package com.itheima.travel.service;

import com.itheima.travel.model.PageBean;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-01 22:52
 */
public interface IFavoriteService {

    /**
    * @Description: 跟据uid和rid判断是否有收藏信息
    * @Param: [rid, uid]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/1/0001
    */
    public boolean isFavorite(int rid, int uid);


    /**
    * @Description: 根据rid与uid添加收藏，返回最新收藏数量
    * @Param: [rid, uid]
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/2/0002
    */
    int addFavorite(int rid, int uid);

    /**
     * @Description: 获取我的收藏的方法
     * @Param: [uid, curPage, pageSize]
     * @Return: com.itheima.travel.model.PageBean
     * @Author: Wangqibo
     * @Date: 2020/6/6/0006
     */
    public PageBean findMyFavorite(int uid, int curPage, int pageSize);
}
