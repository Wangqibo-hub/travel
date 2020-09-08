package com.ithiema.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-30 21:58
 */
public class JedisTest {

    @Test
    public void test(){
        //1.创建jedis连接池
        //创建配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxWaitMillis(3000);
        //根据配置对象创建连接池对象
        JedisPool pool = new JedisPool(jedisPoolConfig, "localhost", 6379);

        //2.从连接池获取jedis对象
        Jedis jedis = pool.getResource();

        //3.写入数据
        jedis.set("name","播仔");
        jedis.set("password","123456");

        //4.读取数据
        String name = jedis.get("name");
        String password = jedis.get("password");
        System.out.println("name="+name);
        System.out.println("password=" + password);

        //5.删除数据
        jedis.del("password");
        String s = jedis.get("password");
        System.out.println("password删除后=" + s);

        //6.关闭连接
        jedis.close();

        //7.关闭连接池
        pool.close();
    }
}
