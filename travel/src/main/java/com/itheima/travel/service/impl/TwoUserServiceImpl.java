package com.itheima.travel.service.impl;

import com.itheima.travel.service.IUserService;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-01 22:04
 */
public class TwoUserServiceImpl implements IUserService {

    /**
    * @Description: 添加用户的方法
    * @Param: []
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/1/0001
    */
    @Override
    public boolean addUser() {
        System.out.println("执行用户添加第二种业务处理");
        return true;
    }
}
