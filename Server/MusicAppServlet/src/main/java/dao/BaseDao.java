package dao;

import Test.LogTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//操作数据库的公共类
public class BaseDao {

    private static Logger logger = LoggerFactory.getLogger(BaseDao.class);
    private static String driver = null;  //数据库驱动
    private static String url = null;  //数据库连接url
    private static String username = null;  //数据库账户
    private static String password = null;  //数据库连接密码

    /**
     * 静态方法区，当类被加载时执行。读取保存在properties中的数据库连接信息
     */
    static {
        try {
            InputStream in = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");//读取配置文件返回数据流
            Properties properties = new Properties();
            properties.load(in);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            StringBuilder builder = new StringBuilder("读取数据库配置文件：\n");
            builder.append("driver："+driver+"\n");
            builder.append("url："+url+"\n");
            builder.append("username："+username+"\n");
            builder.append("password："+password+"\n");
            logger.info(builder.toString());

            //加载驱动
            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return  数据库连接资源
     * @throws SQLException  可能会产生的异常，向上抛出
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 释放资源
     * @param connection  数据库连接资源
     * @param preparedStatement  数据库操作资源
     * @param resultSet  数据库结果集
     * @return 全部释放成功，返回true，否则返回false
     */
    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        boolean flag = true;
        if (resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        if (preparedStatement!=null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 查询公共类
     * @param connection 数据库连接
     * @param sql  真正执行的sql语句
     * @param params  参数信息
     * @param resultSet 查询出的结果集
     * @param preparedStatement  数据库操作类类，可以传入带占位符的SQL语句
     * @return  查询出的结果集
     * @throws SQLException  可能会出现数据库错误，需要向上抛出
     */
    public static ResultSet execute(Connection connection, String sql,Object[] params,ResultSet resultSet,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            //setObject占位符从1开始，数组从0开始
            preparedStatement.setObject(i+1,params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    /**
     * 增、删、改公共方法
     * @param connection 数据库连接
     * @param sql  执行的sql语句
     * @param params  参数信息
     * @param preparedStatement  数据库操作类类，可以传入带占位符的SQL语句
     * @return 受影响的行数
     * @throws SQLException  可能会出现数据库问题，向上抛出
     */
    public static int execute(Connection connection, String sql,Object[] params,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            //setObject占位符从1开始，数组从0开始
            preparedStatement.setObject(i+1,params[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }

}
