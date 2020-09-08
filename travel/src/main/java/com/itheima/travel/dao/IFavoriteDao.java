package com.itheima.travel.dao;

import com.itheima.travel.model.Favorite;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-01 22:48
 */
public interface IFavoriteDao {

    /**
    * @Description: 根据rid和uid查询收藏信息
    * @Param: [rid, uid]
    * @Return: com.itheima.travel.model.Favorite
    * @Author: Wangqibo
    * @Date: 2020/6/1/0001
    */
    @Select("select * from tab_favorite where rid = #{rid} and uid = #{uid}")
    Favorite findByRidAndUid(@Param("rid") int rid,@Param("uid") int uid);

    /**
    * @Description: 添加收藏
    * @Param: [rid, date, uid]
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/2/0002
    */
    @Insert("insert into tab_favorite values(#{rid},#{date},#{uid})")
    int addFavorite(@Param("rid") int rid,
                    @Param("date") String date,
                    @Param("uid") int uid);


    /**
    * @Description: //查询我的收藏总条数
    * @Param: [uid]
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/6/0006
    */
    @Select("select COUNT(*) from tab_favorite where uid = #{uid}")
    int count(int uid);


    /**
    * @Description: //分页查询我的收藏旅游线路
    * @Param: [uid, start, length]
    * @Return: java.util.List<com.itheima.travel.model.Favorite>
    * @Author: Wangqibo
    * @Date: 2020/6/6/0006
    */
    @Select("select * from tab_favorite where uid = #{uid} order by date desc limit #{start},#{length}")
    @Results({
            @Result(
                    property = "route",
                    column = "rid",
                    one = @One(select = "com.itheima.travel.dao.IRouteDao.findRouteByrid")
            )}
    )
    List<Favorite> findFavorite(@Param("uid") int uid,@Param("start") int start,@Param("length") int length);
}
