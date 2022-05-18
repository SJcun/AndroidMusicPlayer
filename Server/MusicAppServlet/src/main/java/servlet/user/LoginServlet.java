package servlet.user;

import Expection.ServerException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.ResponseMange;
import pojo.User;
import service.user.UserService;
import service.user.UserServiceImpl;
import servlet.music.GetMusicListServlet;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录逻辑处理
 */
public class LoginServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    /**
     * get请求
     *
     * @param req  请求信息
     * @param resp 回应信息
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        String responseJson = "";

        //从android端获取用户名和密码
        String userAccount = req.getParameter("account");
        String userPassword = req.getParameter("password");

        try {
            UserService userService = new UserServiceImpl();
            User user = userService.getUser(userAccount, userPassword);
            if (user != null) {
                responseJson = JsonUtil.getInstance().getUserJson(user);
            } else {
                responseJson = "{'result':'" + ResponseMange.SQLGETUSERNONEDATA + "'}";
            }
        } catch (ServerException e) {
            //自定义异常
            responseJson = "{'result':'" + e.getMessage() + "'}";
        }

        logger.info("登录请求：---->请求数据：" + req.getQueryString() + "    响应数据：" + responseJson);

        resp.getWriter().append(responseJson).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
