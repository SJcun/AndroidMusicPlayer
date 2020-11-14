package servlet.music;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pojo.Music;
import service.music.MusicService;
import service.music.MusicServiceImpl;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetMusicListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String response = "";

        MusicService musicService = new MusicServiceImpl();
        List<Music> musicList =  musicService.getMusicList();
        if(musicList.size()>0){
            JsonUtil jsonUtil = new JsonUtil();
            response = jsonUtil.getJsonArray(musicList);
        }
        else{
            response = "{'result':'获取歌曲列表失败！'}";
        }

        resp.getWriter().append(response).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
