package dao.user;

import dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDaoImpl implements UserDao {

    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    /**
     * 根据账户信息获取用户信息
     * @param connection
     * @param account
     * @return
     * @throws SQLException
     */
    public User getUser(Connection connection, String account) throws SQLException {
        ResultSet resultSet = null;  //数据库结果集，取出的数据保存在这个变量里
        PreparedStatement preparedStatement = null;
        User user = null;

        logger.info("获取用户信息---->账户名称："+account);

        if (connection!=null) {
            String sql = "select * from user where account = ?";
            Object[] params = {account};  //存储参数
            resultSet = BaseDao.execute(connection, sql, params, resultSet, preparedStatement);
            if (resultSet.next()){
                user = new User();
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

    /**
     * 添加用户信息
     * @param connection  数据库连接
     * @param user  用户信息
     * @return 数值，0表示添加失败，大于0表示添加成功
     * @throws SQLException
     */
    public int insertUser(Connection connection, User user) throws SQLException {

        PreparedStatement preparedStatement = null;
        int insertRows = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        logger.info("添加用户信息---->账户："+user.getAccount()+"   密码："+user.getPassword());

        if (connection!=null) {
            String sql = "insert into user(`account`, `password`, `create_time`) values(?,?,?)";
            Object[] params = {user.getAccount(), user.getPassword(), df.format(new Date())};
            insertRows = BaseDao.execute(connection, sql, params, preparedStatement);
            BaseDao.closeResource(null,preparedStatement,null);
        }

        return insertRows;
    }

    /**
     * 修改用户密码
     * @param connection 数据库连接
     * @param user 用户信息
     * @return  0-修改失败，>0 修改成功
     * @throws SQLException
     */
    public int changePassword(Connection connection, User user) throws SQLException {

        PreparedStatement preparedStatement = null;
        int updateRows = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        logger.info("修改用户密码------->账户："+user.getAccount()+"  密码："+user.getPassword());

        if(connection!=null){
            String sql = "update user set password=?, change_time=? where account = ?";
            Object[] params = {user.getPassword(), df.format(new Date()), user.getAccount()};
            updateRows = BaseDao.execute(connection, sql, params, preparedStatement);
            BaseDao.closeResource(null,preparedStatement,null);
        }

        return updateRows;
    }

    /**
     * 保存歌曲信息
     * @param connection  数据库连接资源
     * @param account  账户名
     * @param musicId  歌曲id
     * @param pattern  播放模式
     * @return 0-添加失败；>0-添加成功
     * @throws SQLException
     */
    public int saveMusic(Connection connection, String account, int musicId, int pattern) throws SQLException {

        PreparedStatement preparedStatement = null;
        int updateRows = 0;

        logger.info("保存歌曲信息----->账户"+account+"  歌曲id："+musicId+"  播放模式："+pattern);

        if(connection!=null){
            String sql = "update user set music_id=?, pattern=? where account = ?";
            Object[] params = {musicId, pattern, account};
            updateRows = BaseDao.execute(connection, sql, params, preparedStatement);
            BaseDao.closeResource(null,preparedStatement,null);
        }

        return updateRows;
    }
}
