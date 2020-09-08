package com.itheima.admin.web.servlet;

import com.itheima.admin.model.Category;
import com.itheima.admin.model.PageBean;
import com.itheima.admin.model.Route;
import com.itheima.admin.service.ICategoryService;
import com.itheima.admin.service.IRouteService;
import com.itheima.admin.util.FactoryUtil;
import com.itheima.admin.util.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-03 20:33
 */
@WebServlet(name = "RouteServlet", urlPatterns = "/route")
@MultipartConfig
public class RouteServlet extends BaseServlet {

    //实例化IRouteService
    private IRouteService iRouteService = (IRouteService) FactoryUtil.getInstance("IRouteService");

    //实例化ICategoryService
    private ICategoryService iCategoryService = (ICategoryService) FactoryUtil.getInstance("ICategoryService");

    /**
    * @Description: 获取分页数据的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/3/0003
    */
    private void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取当前页和每页条数
        int curPage = 1;
        String curPageStr = request.getParameter("curPage");
        if (curPageStr != null && curPageStr != "") {
            curPage = Integer.parseInt(curPageStr);
        }

        int pageSize = 6;
        String pageSizeStr = request.getParameter("pageSize");
        if (pageSizeStr != null && pageSizeStr != "") {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        //获取搜索线路名称
        String rname = request.getParameter("rname");
        request.setAttribute("rname",rname);

        //调用service方法获取pageBran对象
        PageBean pageBean = iRouteService.getPageBean(curPage, pageSize, rname);

        //将pageBean写入请求域
        request.setAttribute("pageBean", pageBean);

        //转发跳转到route_list.jsp页面
        request.getRequestDispatcher("/pages/route/route_list.jsp").forward(request,response);
    }


    /**
    * @Description: 处理修改线路请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/4/0004
    */
    private void toUpdateUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取rid
        int rid = Integer.parseInt(request.getParameter("rid"));

        //调用业务方法获取Route对象，写入请求域
        Route route = iRouteService.findRouteById(rid);
        request.setAttribute("route",route);

        //调用业务方法获取类别集合，写入请求域
        List<Category> categoryList = iCategoryService.findAll();
        request.setAttribute("categoryList", categoryList);

        //转发跳转到route_update.jsp页面
        request.getRequestDispatcher("/pages/route/route_update.jsp").forward(request,response);
    }



    /**
    * @Description: //处理修改线路信息请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/4/0004
    */
    private void updateData(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取表单信息，封装到Route中
        Route route = new Route();
        Map<String, String[]> map = request.getParameterMap();
        BeanUtils.populate(route, map);

        //调用业务方法，更新Route
        iRouteService.updateRoute(route);

        //转发跳转到route_list.jsp页面
        request.getRequestDispatcher("route?action=findByPage").forward(request,response);
    }


    /**
    * @Description: //处理删除单条线路请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/5/0005
    */
    private void deleteData(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取rid
        int rid = Integer.parseInt(request.getParameter("rid"));

        //调用业务方法，删除线路
        boolean b = iRouteService.deleteRoute(rid);

        //重定向跳转到route_list.jsp页面
        response.sendRedirect(request.getContextPath() + "route?action=findByPage");
    }

    /**
    * @Description: 处理批量删除线路请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/5/0005
    */
    private void delMultipleRoute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取rid数组
        String[] rids = request.getParameterValues("rid");

        //调用业务方法，删除线路
        boolean b = iRouteService.delMultipleRoute(rids);

        //重定向跳转到route_list.jsp页面
        response.sendRedirect(request.getContextPath() + "route?action=findByPage");
    }

    /**
    * @Description: 处理跳转到添加线路页面的请求
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/6/0006
    */
    private void toAddUI(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取类别列表数据，存入请求域，
        List<Category> categoryList = iCategoryService.findAll();
        request.setAttribute("categoryList", categoryList);

        //转发到route_add.jsp页面
        request.getRequestDispatcher("/pages/route/route_add.jsp").forward(request, response);
    }



    /**
    * @Description: //处理添加线路的请求
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/6/0006
    */
    private void addData(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //1.获取表单数据封装到Route中
        Route route = new Route();
        BeanUtils.populate(route,request.getParameterMap());

        //1.1目标：上传图片到服务器
        //获取上传文件对象
        Part part = request.getPart("rimage");
        //校验文件类型，只允许（jpg,png,bmp,gif）上传
        //--获取源文件的扩展名
        String header = part.getHeader("content-disposition");
        String fileExtName = header.substring(header.lastIndexOf(".") + 1, header.length() - 1);
        //--校验
        if("jpg".equalsIgnoreCase(fileExtName) || "png".equalsIgnoreCase(fileExtName)
                || "bmp".equalsIgnoreCase(fileExtName) || "gif".equalsIgnoreCase(fileExtName)){

            //校验文件大小，只允许小于500kb的上传
            //--获取文件大小
            long size = part.getSize();//单位：字节b
            //--校验
            if(size/1024<500){
                //定义上传文件名字
                //--UUID,通用唯一码
                String uuid = UuidUtil.getUuid();
                //--文件名
                String fileName = uuid+"."+fileExtName;

                //将上传文件写入磁盘
                //--定义服务器相对路径目录位置
                String realPath = "img/product/small2/";
                //--根据相对路径目录位置得到绝对路径
                String path = request.getServletContext().getRealPath(realPath);
                //--判断路径是否存在，如果不存在创建路径的目录
                File file = new File(path);
                if(!file.exists()){
                    file.mkdir();
                }
                //--写入磁盘
                part.write(path+"/"+fileName);

                //清楚临时文件
                part.delete();

                //将上传文件存储在服务器上的相对路径封装到Route对象中
                //--上传文件的相对路径
                String fileRealPath = realPath+fileName;
                //--封装到Route中
                route.setRimage(fileRealPath);

                //2.调用业务添加线路数据
                iRouteService.addRoute(route);

                //3.获取结果，添加成功跳转到分页列表数据
                response.sendRedirect(request.getContextPath()+"/route?action=findByPage");
            }else{
                response.getWriter().print("上传文件内容过大，上传失败");
            }
        }else{
            response.getWriter().print("上传文件类型不支持，上传失败");
        }
    }
}
