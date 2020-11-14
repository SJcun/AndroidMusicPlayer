package dao.user;

import dao.BaseDao;
import org.junit.Test;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDaoImpl implements UserDao {
    public User getUser(Connection connection, String account) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = new User();

        if (connection!=null) {
            String sql = "select * from user where account = ?";
            Object[] params = {account};  //存储参数
            resultSet = BaseDao.execute(connection, sql, params, resultSet, preparedStatement);
            System.out.println(resultSet.toString());
            if (resultSet.next()){
                user.setAccount(account);
                user.setMusicId(resultSet.getInt("music_id"));
                user.setPassword(resultSet.getString("password"));
                user.setPattern(resultSet.getInt("pattern"));
                user.setCreateTime(resultSet.getDate("create_time"));
                user.setChangeTime(resultSet.getDate("change_time"));
                user.setRemark(resultSet.getString("remark"));
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }

        return user;
    }

    public int insertUser(Connection connection, User user) throws SQLException {

        PreparedStatement preparedStatement = null;
        int insertRows = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        if (connection!=null) {
            String sql = "insert into user(`account`, `password`, `create_time`) values(?,?,?)";
            Object[] params = {user.getAccount(), user.getPassword(), df.format(new Date())};
            insertRows = BaseDao.execute(connection, sql, params, preparedStatement);
            BaseDao.closeResource(null,preparedStatement,null);
        }

        return insertRows;
    }

    public int changePassword(Connection connection, User user) throws SQLException {

        PreparedStatement preparedStatement = null;
        int updateRows = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        if(connection!=null){
            String sql = "update user set password=?, change_time=? where account = ?";
            Object[] params = {user.getPassword(), df.format(new Date()), user.getAccount()};
            updateRows = BaseDao.execute(connection, sql, params, preparedStatement);
            BaseDao.closeResource(null,preparedStatement,null);
        }

        return updateRows;
    }

    public int saveMusic(Connection connection, String account, int musicId, int pattern) throws SQLException {

        PreparedStatement preparedStatement = null;
        int updateRows = 0;

        if(connection!=null){
            String sql = "update user set music_id=?, pattern=? where account = ?";
            Object[] params = {musicId, pattern, account};
            updateRows = BaseDao.execute(connection, sql, params, preparedStatement);
            BaseDao.closeResource(null,preparedStatement,null);
        }

        return updateRows;
    }
}
