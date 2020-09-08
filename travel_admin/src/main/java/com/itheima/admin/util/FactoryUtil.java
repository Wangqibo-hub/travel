package com.itheima.admin.util;

import java.util.ResourceBundle;

/*
* 工厂类，用于根据接口的名字动态创建实现类对象
* */
public class FactoryUtil {

    private static ResourceBundle resourceBundle;//专门用于解析properties配置文件
    static {
        resourceBundle = ResourceBundle.getBundle("impl");
    }

    /**
     * 根据接口名字获取实现类对象
     * @param interfaceName
     * @return Object
     */
    public static Object getInstance(String interfaceName){
        try {
            //根据接口名字获取对应实现类全名
            String implClassName = resourceBundle.getString(interfaceName);
            //使用反射创建实现类对象返回
            return Class.forName(implClassName).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
