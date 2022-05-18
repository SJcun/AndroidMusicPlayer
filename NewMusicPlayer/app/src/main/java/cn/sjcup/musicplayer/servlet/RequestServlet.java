package cn.sjcup.musicplayer.servlet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.sjcup.musicplayer.util.CommonVariable;
import cn.sjcup.musicplayer.util.JsonUtil;
import cn.sjcup.musicplayer.util.Md5Util;
import cn.sjcup.musicplayer.util.PlayPattern;

/**
 * 负责向服务端发起请求
 */
public class RequestServlet {

    private  static String Ip = CommonVariable.getIp();
    private static final String LOGIN_SERVLET = Ip+"login";
    private static final String REGISTER_SERVLET =Ip+"SignUp";
    private static final String CHANGEPWD_SERVLET =Ip+"UpdatePwd";
    private static final String SAVE_USER_INFO =Ip+"SaveMusic";
    private static final String GET_MUSIC_LIST = Ip+"GetMusicList";
    private static HttpURLConnection conn;

    private static RequestServlet servletInstance = null;
    private static Object lockObject = new Object();   //同步对象
    private final String defaultResponse = "{'result':'与服务端通信失败'}";   //默认使用的http回应信息

    private RequestServlet(){}   //私有化构造器

    /**
     * 单例模式  （线程安全）
     * @return  返回实体类
     */
    public static RequestServlet getInstance(){
        if(servletInstance == null){
            synchronized (lockObject){
                if(servletInstance==null){
                    servletInstance = new RequestServlet();
                }
            }
        }
        return servletInstance;
    }

    /**
     * 获取http连接资源
     * @param path
     * @return
     */
    public HttpURLConnection getConn(String path){
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            //设置请求方式
            conn.setRequestMethod("GET");
            //超时时间
            conn.setConnectTimeout(500);   //等待500毫秒，无回应信息表示通信失败
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 将回应信息由输入流解析为字符串
     * @param is  流信息
     * @return
     */
    public String streamToString(InputStream is) {
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

    /**
     * 登录请求
     * @param account 账户
     * @param password 密码
     * @return 从服务端获取到的响应信息
     */
    public JSONObject login(String account, String password){
        JSONObject result = JsonUtil.getInstance().getJSON(defaultResponse);
        String path = LOGIN_SERVLET+"?account="+account+"&password="+ Md5Util.getInstance().getMD5String(password);  //这里传递的是经过加密后的密码

        HttpURLConnection conn;

        try {
            conn = getConn(path);
            int code = conn.getResponseCode();    //http相应状态吗，200代表相应成功
            if (code == 200){
                InputStream stream = conn.getInputStream();
                String str = streamToString(stream);
                result = JsonUtil.getInstance().getJSON(str);
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 注册新用户
     * @param account  账户
     * @param password  密码
     * @return  从服务端获取到的响应信息
     */
    public JSONObject Register(String account, String password){
        JSONObject result = JsonUtil.getInstance().getJSON(defaultResponse);

        String path = REGISTER_SERVLET+"?account="+account+"&password="+Md5Util.getInstance().getMD5String(password);

        HttpURLConnection conn;

        try {
            conn = getConn(path);
            int code = conn.getResponseCode();    //http相应状态吗，200代表相应成功
            if (code == 200){
                InputStream stream = conn.getInputStream();
                String str = streamToString(stream);
                result = JsonUtil.getInstance().getJSON(str);
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改密码
     * @param account 账户
     * @param newPassword 新密码
     * @param oldPassword 旧密码
     * @return 从服务端获取到的响应信息
     */
    public JSONObject changePwd(String account, String newPassword, String oldPassword){
        JSONObject result = JsonUtil.getInstance().getJSON(defaultResponse);

        String path = CHANGEPWD_SERVLET+"?account="+account+"&oldPassword="+Md5Util.getInstance().getMD5String(oldPassword)+"&newPassword="+Md5Util.getInstance().getMD5String(newPassword);

        HttpURLConnection conn;

        try {
            conn = getConn(path);
            int code = conn.getResponseCode();    //http相应状态吗，200代表相应成功
            if (code == 200){
                InputStream stream = conn.getInputStream();
                String str = streamToString(stream);
                result = JsonUtil.getInstance().getJSON(str);
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 获取歌曲列表
     * @return 歌曲列表
     */
    public JSONArray getMusicList(){
        JSONArray result = JsonUtil.getInstance().getJsonArray(defaultResponse);

        String path = GET_MUSIC_LIST;
        HttpURLConnection conn;

        try {
            conn = getConn(path);
            int code = conn.getResponseCode();
            if (code == 200){
                InputStream jsonArray = conn.getInputStream();
                String str = streamToString(jsonArray);
                result = JsonUtil.getInstance().getJsonArray(str);
                conn.disconnect();
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 保存用户信息
     * @param account 账户
     * @param musicId 歌曲id
     * @param playPattern 播放模式
     * @return
     */
    public JSONObject savePlayerInformation(String account, int musicId, PlayPattern playPattern){
        JSONObject result = JsonUtil.getInstance().getJSON(defaultResponse);

        int pattern = 0;
        if(playPattern == PlayPattern.PLAY_IN_ORDER){
            pattern = 0;
        }else if(playPattern == PlayPattern.PLAY_RANDOM){
            pattern = 1;
        }else{
            pattern = 2;
        }

        String path = SAVE_USER_INFO+"?account="+account+"&musicId="+musicId+"&pattern="+pattern;

        HttpURLConnection conn;

        try {
            conn = getConn(path);
            int code = conn.getResponseCode();    //http相应状态吗，200代表相应成功
            if (code == 200){
                InputStream stream = conn.getInputStream();
                String str = streamToString(stream);
                result = JsonUtil.getInstance().getJSON(str);
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
