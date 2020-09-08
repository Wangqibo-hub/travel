package com.itheima.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.travel.model.PageBean;
import com.itheima.travel.model.Route;
import com.itheima.travel.service.IRouteService;
import com.itheima.travel.service.RouteService;
import com.itheima.travel.util.FactoryUtil;
import com.itheima.travel.util.ObjectMapperUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-31 11:04
 */
@WebServlet(name = "RouteServlet", urlPatterns = "/RouteServlet")
public class RouteServlet extends BaseServlet {

    //实例化业务层对象
    private RouteService routeService = new RouteService();
    private IRouteService iRouteService = (IRouteService) FactoryUtil.getInstance("IRouteService");

    /**
     * @Description: 分页获取旅游线路的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/5/31/0031
     */
    protected void findRoute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取cid, rname和curPage
        int cid = Integer.parseInt(request.getParameter("cid"));
        String rname = request.getParameter("rname");

        //判断有效性。设置默认值
        int curPage = 1;
        String curPageVal = request.getParameter("curPage");
        if (curPageVal != null && curPageVal != "") {
            curPage = Integer.parseInt(curPageVal);
        }

        int pageSize = 3;
        String pageSize1Val = request.getParameter("pageSize");
        if (pageSize1Val != null && pageSize1Val != "") {
            pageSize = Integer.parseInt(pageSize1Val);
        }

        //调用业务层方法获取类别数据
        PageBean pageBeanVal = routeService.findByPage(cid, rname, curPage, pageSize);

        //将pageBean转换成json字符串
        String pageBean = ObjectMapperUtil.getObjectMapper().writeValueAsString(pageBeanVal);

        //返回类别数据给前端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(pageBean);
    }

    /**
     * @Description: 根据rid获取线路详细信息的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/6/1/0001
     */
    protected void findRouteByRid(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取rid
        int rid = Integer.parseInt(request.getParameter("rid"));

        //调用业务层方法获取类别数据
        Route route = routeService.findRouteByRid(rid);

        //将pageBean转换成json字符串
        String routeStr = ObjectMapperUtil.getObjectMapper().writeValueAsString(route);

        //返回类别数据给前端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(routeStr);
    }


    /**
     * @Description: 分页获取收藏排行榜线路的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/6/2/0002
     */
    protected void findFavoriteRankByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取当前页和每页条数
        int curPage = 1;
        String curPageStr = request.getParameter("curPage");
        if (curPageStr != null) {
            curPage = Integer.parseInt(curPageStr);
        }

        int pageSize = 8;
        String pageSizeStr = request.getParameter("pageSize");
        if (pageSizeStr != null) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        //获取搜索条件
        String rname = request.getParameter("rname");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");

        HashMap<String, Object> map = new HashMap<>();
        map.put("rname", rname);
        map.put("minPrice", minPrice);
        map.put("maxPrice", maxPrice);

        //调用业务层方法获取PageBean
        PageBean pageBean = iRouteService.findFavoriteRoute(curPage, pageSize, map);

        //将PageBean转换为json对象
        response.setContentType("application/json;charset=utf-8");
        String pageBeanStr = new ObjectMapper().writeValueAsString(pageBean);
        response.getWriter().print(pageBeanStr);
    }

}
