package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.Music;
import pojo.User;

import java.util.List;

/**
 * json工具类
 */
public class JsonUtil {

    /**
     * 私有化构造器
     */
    private JsonUtil(){}

    private static JsonUtil jsonUtilInstance = null;  //json工具类实体

    /**
     * 单例模式，获取实体类（线程安全）
     * @return
     */
    public static JsonUtil getInstance(){
        if(jsonUtilInstance==null){
            synchronized(JsonUtil.class){
                if(jsonUtilInstance==null){
                    jsonUtilInstance = new JsonUtil();
                }
            }
        }
        return jsonUtilInstance;
    }

    /**
     * 用户信息打包为json数据
     * @param user  用户信息
     * @return  json字符串
     */
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

    /**
     * 歌曲信息打包为json数据
     * @param music  歌曲信息
     * @return  json字符串
     */
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

    /**
     * 歌曲list打包为Json串
     * @param musicList  歌曲信息
     * @return  json字符串
     */
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
