package com.itheima.travel.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-01 22:22
 */
public class FactoryUtil {

    private static ResourceBundle resourceBundle;
    static {
        resourceBundle = ResourceBundle.getBundle("impl");
    }

    public static Object getInstance(String interfaceName) {

        try {
            String implClassFullName = resourceBundle.getString(interfaceName);

            return Class.forName(implClassFullName).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
