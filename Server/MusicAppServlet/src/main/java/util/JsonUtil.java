package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.Music;
import pojo.User;

import java.util.List;

public class JsonUtil {

    //user转json
    public String getUserJson(User user){
        JSONObject userJson = new JSONObject();

        if(user!=null){
            userJson.put("userId", user.getUserId());
            userJson.put("account", user.getAccount());
            userJson.put("password", user.getPassword());
            userJson.put("music_id", user.getMusicId());
            userJson.put("pattern", user.getPattern());
            userJson.put("createTime", user.getCreateTime());
            userJson.put("changeTime", user.getChangeTime());
            userJson.put("remark", user.getRemark());
        }

        return userJson.toString();
    }

    //music转json
    public String getMusicJson(Music music){
        JSONObject musicJson = new JSONObject();

        if(music != null){
            musicJson.put("musicId", music.getMusicId());
            musicJson.put("name", music.getName());
            musicJson.put("author", music.getAuthor());
            musicJson.put("address", music.getAddress());
            musicJson.put("img", music.getImg());
            musicJson.put("createTime", music.getCreateTime());
            musicJson.put("changeTime", music.getChangeTime());
            musicJson.put("remark", music.getRemark());
        }

        return musicJson.toString();
    }

    //List<Music>转JsonArray
    public String getJsonArray(List<Music> musicList){

        JSONObject musicJson = new JSONObject();
        JSONArray musicJsonArray = new JSONArray();

        for (Music music:musicList)
        {
            musicJson = new JSONObject();

            musicJson.put("musicId", music.getMusicId());
            musicJson.put("name", music.getName());
            musicJson.put("author", music.getAuthor());
            musicJson.put("address", music.getAddress());
            musicJson.put("img", music.getImg());
            musicJson.put("createTime", music.getCreateTime());
            musicJson.put("changeTime", music.getChangeTime());
            musicJson.put("remark", music.getRemark());

            musicJsonArray.add(musicJson);
        }

        return musicJsonArray.toString();
    }
}
