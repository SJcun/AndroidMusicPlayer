package cn.sjcup.musicplayer.util;

/**
 * 公共变量
 */
public class CommonVariable {

    private static String Ip="";  //服务端的ip地址

    /**
     * 设置ip
     * @param ip
     */
    public static void setIp(String ip){
        Ip = ip;
    }

    /**
     * 获取ip
     * @return
     */
    public static String getIp(){
        return Ip;
    }
}
