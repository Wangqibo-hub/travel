package com.itheima.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.travel.exception.CustomerErrorMsgException;
import com.itheima.travel.model.User;
import com.itheima.travel.service.IUserService;
import com.itheima.travel.service.UserService;
import com.itheima.travel.service.impl.OneUserServiceImpl;
import com.itheima.travel.util.FactoryUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-29 19:27
 */
@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {

    //实例化业务层对象
    private UserService userService = new UserService();

    /**
     * @Description: 处理用户注册的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/5/29/0029
     */
    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            //结局输出中文乱码
            response.setContentType("text/html;charset=utf-8");

            //3. 校验验证码
            String userCheck = request.getParameter("check");
            String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");

            if (!checkcode_server.equalsIgnoreCase(userCheck)) {
                //3.1 验证码错误，抛出自定义异常信息
                throw new CustomerErrorMsgException("验证码错误");
            }

            //3.2 验证码正确，封装用户并调用业务层注册用户的方法
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            BeanUtils.populate(user, map);
            boolean flag = userService.register(user);

            //3.2.1 注册成功返回true
            response.getWriter().print(flag);

        } catch (CustomerErrorMsgException e) {
            e.printStackTrace();
            //3.2.2 返回自定义异常，返回用户名已被注册异常
            response.getWriter().print(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            //3.2.3 返回系统异常，抛出运行时异常
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 处理用户登陆的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/5/29/0029
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            //结局输出中文乱码
            response.setContentType("text/html;charset=utf-8");

            //3. 校验验证码
            String userCheck = request.getParameter("checkCode");
            String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");

            if (!checkcode_server.equalsIgnoreCase(userCheck)) {
                //3.1 验证码错误，抛出自定义异常信息
                throw new CustomerErrorMsgException("验证码错误");
            }

            //2.2 验证码正确，调用业务层判断用户登陆的方法
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User loginUser = userService.login(username, password);

            //2.2.1 登陆成功返回true，并将用户信息写入session
            response.getWriter().print("true");
            request.getSession().setAttribute("loginUser", loginUser);

        } catch (CustomerErrorMsgException e) {
            e.printStackTrace();
            //3.2.2 返回自定义异常，返回用户名已被注册异常
            response.getWriter().print(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            //3.2.3 返回系统异常，抛出运行时异常
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 处理登陆数据请求的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/5/29/0029
     */
    private void getUserLoginData(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //结局输出中文乱码
        response.setContentType("text/html;charset=utf-8");

        //1.从session获取登陆的用户对象
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        //2. 将java的用户对象转换成json发送给前端
        ObjectMapper objectMapper = new ObjectMapper();
        String userData = objectMapper.writeValueAsString(loginUser);

        response.getWriter().print(userData);
    }

    /**
     * @Description: 处理用户退出请求的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/5/29/0029
     */
    private void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //销毁session
        request.getSession().invalidate();

        //跳转到登陆页面
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

    /**
     * @Description: 处理异步检验用户名请求的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/5/29/0029
     */
    private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //结局输出中文乱码
            response.setContentType("text/html;charset=utf-8");

            //2.获取用户名
            String username = request.getParameter("username");

            //3.调用业务层判断用户是否存在的方法
            boolean flag = userService.checkUsername(username);

            //3.2.1 用户不存在，返回true
            response.getWriter().print(flag);

        } catch (CustomerErrorMsgException e) {
            //3.2.2 返回自定义异常，用户名已被注册异常
            e.printStackTrace();
            response.getWriter().print(e.getMessage());
        } catch (IOException e) {
            //3.2.3 返回系统异常，抛出运行时异常
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //private IUserService userService2 = new OneUserServiceImpl();
    private IUserService userService2 = (IUserService) FactoryUtil.getInstance("IUserService");
    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
       userService2.addUser();
    }
}
