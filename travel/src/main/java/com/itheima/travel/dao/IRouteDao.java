package com.itheima.travel.dao;

import com.itheima.travel.model.Route;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-31 10:48
 */
public interface IRouteDao {

   /**
   * @Description: 获取总条数的方法
   * @Param: [cid, rname]
   * @Return: int
   * @Author: Wangqibo
   * @Date: 2020/5/31/0031
   */
    //@Select("select count(*) from tab_route where cid = #{cid}")
    int count(@Param("cid") int cid, @Param("rname") String rname);

   /**
   * @Description: 获取当前页数据列表的方法
   * @Param: [cid, rname, start, length]
   * @Return: java.util.List<com.itheima.travel.model.Route>
   * @Author: Wangqibo
   * @Date: 2020/5/31/0031
   */
   // @Select("select * from tab_route where cid=#{cid} limit #{start},#{length}")
    List<Route> findByPage(@Param("cid") int cid,@Param("rname") String rname, @Param("start") int start,@Param("length") int length);


    /**
    * @Description: 根据rid查询Route
    * @Param: [rid]
    * @Return: com.itheima.travel.model.Route
    * @Author: Wangqibo
    * @Date: 2020/6/1/0001
    */
    @Select("select * from tab_route where rid = #{rid}")
    @Results({
            @Result(id = true, column = "rid", property = "rid"),
            @Result(
                    property = "category",
                    column = "cid",
                    one = @One(select = "com.itheima.travel.dao.ICategoryDao.findCategoryByCid")
            ),
            @Result(
                    property = "seller",
                    column = "sid",
                    one = @One(select = "com.itheima.travel.dao.ISellerDao.findSellerBySid")
            ),
            @Result(
                    property = "routeImgList",
                    column = "rid",
                    many = @Many(select = "com.itheima.travel.dao.IRouteImgDao.findRouteImgByRid")
            )
    })
    Route findRouteByrid(int rid);


    /**
    * @Description: 更新收藏数量
    * @Param: [rid]
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/2/0002
    */
    @Update("update tab_route set `count`=`count`+1 where rid=#{rid}")
    int updateCount(int rid);


    /**
    * @Description: 查询排行榜线路总条数
    * @Param: []
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/2/0002
    */
    //@Select("SELECT COUNT(*) FROM tab_route")
    int countForFavorite(@Param("map") Map<String, Object> conditionMap);


    /**
    * @Description: 获取当前页数据列表
    * @Param: [start, length]
    * @Return: java.util.List<com.itheima.travel.model.Route>
    * @Author: Wangqibo
    * @Date: 2020/6/2/0002
    */
    //@Select("SELECT * FROM tab_route ORDER BY `count` DESC LIMIT #{start},#{length}")
    List<Route> findFavoriteRankByPage(@Param("start") int start,
                                       @Param("length") int length,
                                       @Param("map") Map<String, Object> conditionMap);
}
