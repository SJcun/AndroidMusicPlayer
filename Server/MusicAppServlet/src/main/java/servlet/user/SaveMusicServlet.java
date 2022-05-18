package servlet.user;

import Expection.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.user.UserService;
import service.user.UserServiceImpl;
import servlet.music.GetMusicListServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 保存歌曲信息到用户表内
 */
public class SaveMusicServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(SaveMusicServlet.class);

    /**
     * http中的get请求
     * @param req  请求信息
     * @param resp  响应信息
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String response = "";

        //从android端获取用户名和密码
        String userAccount = req.getParameter("account");
        int musicId = Integer.parseInt(req.getParameter("musicId"));
        int pattern = Integer.parseInt(req.getParameter("pattern"));

        UserService userService = new UserServiceImpl();
        try{
            boolean flag = userService.updateMusic(userAccount, musicId, pattern);
            if(flag){
                response = "{'result':'保存成功！'}";
            }else{
                response = "{'result':'保存失败！'}";
            }
        }
        catch (ServerException e){
            //自定义异常
            response = "{'result':'"+ e.getMessage()+"'}";
        }

        logger.info("保存歌曲信息请求：---->请求数据："+req.getQueryString()+"    响应数据："+response);

        resp.getWriter().append(response).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
