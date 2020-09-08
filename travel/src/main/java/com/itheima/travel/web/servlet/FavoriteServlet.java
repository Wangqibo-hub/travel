package com.itheima.travel.web.servlet;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.travel.model.PageBean;
import com.itheima.travel.model.User;
import com.itheima.travel.service.IFavoriteService;
import com.itheima.travel.util.FactoryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-01 22:57
 */
@WebServlet(name = "FavoriteServlet", urlPatterns = "/FavoriteServlet")
public class FavoriteServlet extends BaseServlet {

    //实例化业务层
    private IFavoriteService iFavoriteService = (IFavoriteService) FactoryUtil.getInstance("IFavoriteService");

    /**
    * @Description: 判断线路是否被收藏的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/1/0001
    */
    protected void isFavoriteByRid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收rid
        int rid = Integer.parseInt(request.getParameter("rid"));

        //获取uid
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        //登陆用户无效返回false
        if (loginUser == null) {
            response.getWriter().print("flase");
            return;
        }

        //调用业务层方法根据rid和uid获取收藏信息
        boolean flag = iFavoriteService.isFavorite(rid, loginUser.getUid());

        //返回给前端
        response.getWriter().print(flag);
    }

    /**
    * @Description: 添加收藏的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/2/0002
    */
    protected void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取rid
        int rid = Integer.parseInt(request.getParameter("rid"));

        //获取登陆用户
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            //登录用户无效，返回false
            response.getWriter().print("false");
            return;
        }

        //登陆用户有效，获取uid并调用业务方法添加收藏
        int uid = loginUser.getUid();
        int count = iFavoriteService.addFavorite(rid, uid);

        //将最新的收藏数量返回给前端
        response.getWriter().print(count);
    }

    /**
     * @Description: 处理显示我的收藏的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/6/6/0006
     */
    protected void myFavorite(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取uid
        int uid = Integer.parseInt(request.getParameter("uid"));

        //获取当前页和每页条数
        int curPage = 1;
        String curPageStr = request.getParameter("curPage");
        if (curPageStr != null) {
            curPage = Integer.parseInt(curPageStr);
        }

        int pageSize = 12;
        String pageSizeStr = request.getParameter("pageSize");
        if (pageSizeStr != null) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        //调用业务层方法获取pageBean数据
        PageBean pageBean = iFavoriteService.findMyFavorite(uid, curPage, pageSize);

        //将PageBean转换为json对象
        response.setContentType("application/json;charset=utf-8");
        //ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        //String pageBeanStr = objectMapper.writeValueAsString(pageBean);
        String pageBeanStr = new ObjectMapper().writeValueAsString(pageBean);
        response.getWriter().print(pageBeanStr);
    }

}
