package servlet.user;

import org.junit.Test;
import pojo.User;
import service.user.UserService;
import service.user.UserServiceImpl;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        String responseJson = "";

        //从android端获取用户名和密码
        String userAccount = req.getParameter("account");
        String userPassword = req.getParameter("password");

        //与数据库中的密码进行比对
        UserService userService = new UserServiceImpl();
        User user = userService.login(userAccount);

        if(user != null){
            JsonUtil jsonUtil = new JsonUtil();
            responseJson = jsonUtil.getUserJson(user);
        }

        resp.getWriter().append(responseJson).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
