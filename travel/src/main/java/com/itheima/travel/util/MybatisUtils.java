package com.itheima.travel.util;

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
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    //1.定义Threadlocal存储类型为SqlSession
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

	//获取 SqlSession 对象的静态方法
    public static SqlSession  getSession()
    {
        //2.从线程里面获取共享的SqlSession对象
        SqlSession sqlSession = threadLocal.get();
        //3.对象为空，创建一个新的sqlSession写入线程共享ThreadLocal中
        if(sqlSession==null){
            sqlSession = sqlSessionFactory.openSession();
            System.out.println(sqlSession+",初始化SqlSession资源");
            threadLocal.set(sqlSession);
        }
        //4.返回有效对象
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
        System.out.println(sqlSession+",释放SqlSession资源");
        //从线程共享变量里面移出sqlSession
        threadLocal.remove();
    }
}
