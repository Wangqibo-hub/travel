package com.itheima.travel.web.servlet;

import com.itheima.travel.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-30 23:21
 */
@WebServlet(name = "CategoryServlet", urlPatterns = "/CategoryServlet")
public class CategoryServlet extends BaseServlet {

    //实例化业务层方法
    private CategoryService categoryService = new CategoryService();

    protected void findCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //处理json数据中含有中文乱码
        response.setContentType("application/json;charset=utf-8");

        //调用业务层方法获取类别数据
        String categoryList = categoryService.findCategory();

        //返回类别数据给前端
        response.getWriter().print(categoryList);
    }

}
