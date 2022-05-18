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
 * 修改用户密码
 */
public class UpdatePwdServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(UpdatePwdServlet.class);

    /**
     * httpget请求
     * @param req  请求信息
     * @param resp  回应信息
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String response = "";

        //从android端获取用户名和密码
        String userAccount = req.getParameter("account");
        String newPassword = req.getParameter("newPassword");
        String oldPassword = req.getParameter("oldPassword");

        UserService userService = new UserServiceImpl();
        try{
            boolean flag = userService.updatePwd(userAccount, oldPassword, newPassword);
            if(flag){
                response = "{'result':'修改成功！'}";
            }
            else{
                response = "{'result':'修改失败，请检查用户名和密码'}";
            }
        }
        catch (ServerException e){
            //自定义异常
            response = "{'result':'"+ e.getMessage()+"'}";
        }

        logger.info("修改密码请求：---->请求数据："+req.getQueryString()+"    响应数据："+response);

        resp.getWriter().append(response).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
