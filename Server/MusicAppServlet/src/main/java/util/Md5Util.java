package util;

import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * md5码加密工具类
 */
public class Md5Util {
    /**
     * 私有化构造器
     */
    private Md5Util(){}

    private static Md5Util md5UtilInstance = null;  //md5码加密工具类实体

    /**
     * 单例模式，获取实体类（线程安全）
     * @return
     */
    public static Md5Util getInstance(){
        if(md5UtilInstance==null){
            synchronized(Md5Util.class){
                if(md5UtilInstance==null){
                    md5UtilInstance = new Md5Util();
                }
            }
        }
        return md5UtilInstance;
    }

    public String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
