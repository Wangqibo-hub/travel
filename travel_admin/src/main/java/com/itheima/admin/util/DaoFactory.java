package com.itheima.admin.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DaoFactory {

    //目标：增强每一个dao接口中的方法，在执行方法前进行初始化资源，执行方法后关闭资源
    public static <T> T getBean(Class<T> daoInterfaceClass){
        //Proxy.newProxyInstance(增强类的类加载器,增强类的接口数组字节码对象，事件处理程序)  返回增强后的代理类
        return (T)Proxy.newProxyInstance(
                daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},
                (proxy,  method, args)->{
                    //proxy,是返回的代理对象
                    //method,dao接口执行的方法
                    //args,执行方法的参数

                    //1.初始化资源
                    SqlSession sqlSession  = MybatisUtils.getSession();
                    T dao = sqlSession.getMapper(daoInterfaceClass);

                    //2.执行dao接口中的方法
                    Object obj = method.invoke(dao,args);

                    //3.关闭资源
                    MybatisUtils.closeSession(sqlSession);

                    return obj;
                }
        );
    }
}
