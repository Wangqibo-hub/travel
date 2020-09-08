package com.itheima.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-30 18:52
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取请求类型
            String action = request.getParameter("action");

            //获取当前类的字节码对象
            Class clazz = this.getClass();

            //通过反射获取指定的方法对象
            Method method = clazz.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            //暴力反射设置私有成员允许访问
            method.setAccessible(true);

            //调用方法执行
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
