package com.itheima.admin.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    //初始化 SqlSessionFactory 对象
    static {
        try{
            //1 读取配置文件
            String resource ="sqlMapConfig.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    //ThreadLocal可以存储每个线程的共享对象，存储的数据在一个线程内共享
    /*
     * 使用步骤：
     *   1.定义ThreadLocal存储sqlSession
     *   2.获取SqlSession从ThreadLocal里面获取，如果ThreadLocal没有sqlSession就写入SqlSession到ThreadLocal，有就返回
     *   3.关闭SqlSession的时候将ThreadLocal里面存储的数据删除
     * */
    //1.定义ThreadLocal存储sqlSession
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

	//获取 SqlSession 对象的静态方法
    public static SqlSession  getSession()
    {
        //2.获取SqlSession从ThreadLocal里面获取，如果ThreadLocal没有sqlSession就写入SqlSession到ThreadLocal里面，有就返回
        SqlSession sqlSession = threadLocal.get();
        if(sqlSession==null){
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);//存入ThreadLocal里面的数据当前线程内共享
            System.out.println("初始化SqlSession对象："+sqlSession);
        }
        return sqlSession;
    }


    //根据给定的接口类型直接返回接口代理对象
    public static  <T> T getMapper(Class<T> interfaceClass){
        return getSession().getMapper(interfaceClass);
    }

    //关闭sqlSession
    public static void closeSession(SqlSession sqlSession){
        sqlSession.commit();
        sqlSession.close();
        System.out.println("关闭sqlSession:"+sqlSession);
        threadLocal.remove();
    }
}
