package cn.sjcup.musicplayer.util;

import org.json.JSONArray;
import org.json.JSONObject;

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
            synchronized(Md5Util.class){
                if(jsonUtilInstance==null){
                    jsonUtilInstance = new JsonUtil();
                }
            }
        }
        return jsonUtilInstance;
    }

    /**
     * 将回应数据由字符串解析为json
     * @param str
     * @return
     */
    public JSONObject getJSON(String str){
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObj;
    }

    /**
     * 将字符串转换为json数组
     * @param jsonArray 字符串
     * @return json数组
     */
    public JSONArray getJsonArray(String jsonArray){
        JSONArray JsonArrayObj = null;
        try {
            JsonArrayObj = new JSONArray(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonArrayObj;
    }
}
