package com.itheima.travel.dao;

import com.itheima.travel.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-29 18:53
 */
public interface IUserDao {
    
    /**
    * @Description: 根据用户名查询用户
    * @Param: [username]
    * @Return: com.itheima.travel.model.User
    * @Author: Wangqibo
    * @Date: 2020/5/29/0029
    */
    @Select("select * from tab_user where username = #{username}")
    User findUserByName(String username);
    
    /**
    * @Description: 插入用户
    * @Param: [user]
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/5/29/0029
    */
    @Insert("insert into tab_user values(null,#{username},#{password},#{name},#{birthday},#{sex},#{telephone},#{email})")
    int insertUser(User user);

}
