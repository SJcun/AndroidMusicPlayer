package servlet.user;

import Expection.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.user.UserService;
import service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册处理请求，继承自HttpServlet，遵循http协议
 */
public class SignUpServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(SignUpServlet.class);

    /**
     * Http协议Get请求方式
     * @param req 请求
     * @param resp 响应信息
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String response = "";

        //从android端获取用户名和密码
        String userAccount = req.getParameter("account");
        String userPassword = req.getParameter("password");

        UserService userService = new UserServiceImpl();
        try{
            boolean flag = userService.insertUser(userAccount, userPassword);
            if(flag){
                response = "{'result':'注册成功！'}";
            }else{
                response = "{'result':'注册失败，请更换用户名'}";
            }
        }
        catch (ServerException e){
            response = "{'result':'"+e.getMessage()+"'}";
        }

        logger.info("注册信息请求：---->请求数据："+req.getQueryString()+"    响应数据："+response);

        resp.getWriter().append(response).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
