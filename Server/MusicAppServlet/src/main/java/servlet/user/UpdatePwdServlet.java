package servlet.user;

import pojo.User;
import service.user.UserService;
import service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdatePwdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String response = "";

        //从android端获取用户名和密码
        String userAccount = req.getParameter("account");
        String newPassword = req.getParameter("newPassword");

        //先验证旧密码与数据库中的是否相同
        UserService userService = new UserServiceImpl();
        User user = userService.login(userAccount);
        if(user != null){
            boolean flag = userService.updatePwd(userAccount, newPassword);
            if(flag){
                response = "{'result':'修改成功！'}";
            }
            else{
                response = "{'result':'修改失败！'}";
            }
        }

        resp.getWriter().append(response).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
