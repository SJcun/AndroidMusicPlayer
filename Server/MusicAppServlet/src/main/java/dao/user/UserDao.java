package dao.user;

import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {

    //获取用户信息
    public User getUser(Connection connection, String account) throws SQLException;

    //添加用户信息
    public int insertUser(Connection connection, User user) throws SQLException;

    //更改密码
    public int changePassword(Connection connection, User user) throws SQLException;

    //退出时保存当前所听歌曲和播放模式
    public int saveMusic(Connection connection, String account, int musicId, int pattern) throws SQLException;
}
