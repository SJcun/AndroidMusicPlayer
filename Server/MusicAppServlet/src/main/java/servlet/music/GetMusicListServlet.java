package servlet.music;

import Expection.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.Music;
import pojo.ResponseMange;
import service.music.MusicService;
import service.music.MusicServiceImpl;
import service.user.UserServiceImpl;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 获取歌曲服务，继承于HttpServlet
 */
public class GetMusicListServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(GetMusicListServlet.class);

    /**
     * 重写get请求
     * @param req 请求
     * @param resp 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String response = "";  //响应字符串数据

        MusicService musicService = new MusicServiceImpl();
        try{
            List<Music> musicList =  musicService.getMusicList();
            if(musicList.size()>0){
                response = JsonUtil.getInstance().getJsonArray(musicList);
            }
            else{
                response = "{'result':'"+ ResponseMange.SQLGETMUSICNONEDATA+"'}";
            }
        }
        catch (ServerException e){
            //自定义异常
            response = "{'result':'"+ e.getMessage()+"'}";
        }

        logger.info("获取歌曲信息：---->请求数据："+req.getQueryString()+"    响应数据："+response);

        resp.getWriter().append(response).flush();
    }

    /**
     * post请求转发到get请求一块处理
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
