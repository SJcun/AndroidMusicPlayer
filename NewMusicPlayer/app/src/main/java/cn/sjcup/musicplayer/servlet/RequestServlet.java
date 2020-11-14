package cn.sjcup.musicplayer.servlet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestServlet {

    private static final String LOGIN_SERVLET = "http://192.168.43.230:8080/musicplayer/login";
    private static final String REGISTER_SERVLET ="http://192.168.43.230:8080/musicplayer/SignUp";
    private static final String CHANGEPWD_SERVLET ="http://192.168.43.230:8080/musicplayer/UpdatePwd";
    private static final String SAVE_USER_INFO ="http://192.168.43.230:8080/musicplayer/SaveMusic";
    private static final String GET_MUSIC_LIST = "http://192.168.43.230:8080/musicplayer/GetMusicList";
    private static HttpURLConnection conn;
    private static JSONObject JSONobj;

    public static HttpURLConnection getConn(String path){
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            //设置请求方式
            conn.setRequestMethod("GET");
            //超时时间
            conn.setConnectTimeout(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static JSONObject getJSON(String str){
        try {
            JSONobj = new JSONObject(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONobj;
    }

    //解析输入流为String
    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        //new一个StringBuffer用于字符串拼接
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            //当输入流内容读取完毕时
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            //关闭流数据
            is.close();
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //登录
    public static JSONObject login(String account, String password){
        JSONObject result = null;
        String path = LOGIN_SERVLET+"?account="+account+"&password="+password;

        HttpURLConnection conn;

        try {
            conn = getConn(path);
            int code = conn.getResponseCode();    //http相应状态吗，200代表相应成功
            if (code == 200){
                InputStream stream = conn.getInputStream();
                String str = streamToString(stream);
                result = getJSON(str);
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    //注册
    public static JSONObject Register(String account, String password){
        JSONObject result = null;

        String path = REGISTER_SERVLET+"?account="+account+"&password="+password;

        HttpURLConnection conn;

        try {
            conn = getConn(path);
            int code = conn.getResponseCode();    //http相应状态吗，200代表相应成功
            if (code == 200){
                InputStream stream = conn.getInputStream();
                String str = streamToString(stream);
                result = getJSON(str);
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static JSONObject changePwd(String account, String newPassword){
        JSONObject result = null;

        String path = CHANGEPWD_SERVLET+"?account="+account+"&newPassword="+newPassword;

        HttpURLConnection conn;

        try {
            conn = getConn(path);
            int code = conn.getResponseCode();    //http相应状态吗，200代表相应成功
            if (code == 200){
                InputStream stream = conn.getInputStream();
                String str = streamToString(stream);
                result = getJSON(str);
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray getJsonArray(String jsonArray){
        JSONArray JsonArrayObj = null;
        try {
            JsonArrayObj = new JSONArray(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonArrayObj;
    }

    //获取歌曲列表
    public static JSONArray getMusicList(){
        JSONArray result = null;

        String path = GET_MUSIC_LIST;
        HttpURLConnection conn;

        try {
            conn = getConn(path);
            int code = conn.getResponseCode();
            if (code == 200){
                InputStream jsonArray = conn.getInputStream();
                String str = streamToString(jsonArray);
                result = getJsonArray(str);
                conn.disconnect();
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public static JSONObject savePlayerInformation(String account,int musicId,int playPattern){
        JSONObject result = null;

        String path = SAVE_USER_INFO+"?account="+account+"&musicId="+musicId+"&pattern="+playPattern;

        HttpURLConnection conn;

        try {
            conn = getConn(path);
            int code = conn.getResponseCode();    //http相应状态吗，200代表相应成功
            if (code == 200){
                InputStream stream = conn.getInputStream();
                String str = streamToString(stream);
                result = getJSON(str);
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
