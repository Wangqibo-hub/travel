package com.itheima.travel.service;

import com.itheima.travel.dao.IUserDao;
import com.itheima.travel.exception.CustomerErrorMsgException;
import com.itheima.travel.model.User;
import com.itheima.travel.util.DaoFactory;
import com.itheima.travel.util.Md5Util;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-29 19:02
 */
public class UserService {

    //实例化dao层对象
    private IUserDao iUserDao = DaoFactory.getBean(IUserDao.class);

    /**
    * @Description: 注册用户的方法
    * @Param: [user]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/5/29/0029
    */
    public boolean register(User user) throws Exception {

        //调用dao层查询方法判断用户是否已经存在
        User userByName = iUserDao.findUserByName(user.getUsername());

        if (userByName != null) {
            //用户存在，抛出用户名已被注册信息异常
            throw new CustomerErrorMsgException("用户名已被注册");
        }

        //用户不存在，对密码进行加密
        String password = Md5Util.getMD5(user.getPassword());
        user.setPassword(password);

        // 调用业务层插入用户的方法
        iUserDao.insertUser(user);

        //插入成功返回true
        return true;
    }

    /**
    * @Description: 判断用户登陆的方法
    * @Param: [username, password]
    * @Return: com.itheima.travel.model.User
    * @Author: Wangqibo
    * @Date: 2020/5/29/0029
    */
    public User login(String username, String password) throws Exception {

        //调用dao层查询方法判断用户是否存在
        User userByName = iUserDao.findUserByName(username);

        if (userByName == null) {
            //用户不存在，抛出用户名或密码错误异常
            throw new CustomerErrorMsgException("用户名或密码错误");
        }

        //用户存在，对传递过来的密码进行MD5加密
        String md5 = Md5Util.getMD5(password);
        String userByNamePassword = userByName.getPassword();

        //判断密码是否一致
        if (userByNamePassword.equals(md5)) {
            //一致返回true
            return userByName;
        } else {
            //不一致，抛出用户名或密码错误异常
            throw new CustomerErrorMsgException("用户名或密码错误");
        }
    }

    /**
    * @Description: 根据用户名判断是否存在用户的方法
    * @Param: [username]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/5/29/0029
    */
    public boolean checkUsername(String username) throws CustomerErrorMsgException {

        //调用dao层查询方法判断用户是否已经存在
        User userByName = iUserDao.findUserByName(username);

        if (userByName != null) {
            //用户存在，抛出用户名已被注册信息异常
            throw new CustomerErrorMsgException("用户名已被注册");
        }
        //用户不存在，返回true
        return true;
    }
}
