package service.user;

import Expection.ServerException;
import dao.BaseDao;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.ResponseMange;
import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 处理与用户相关的逻辑
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 根据账户获取用户数据，判断密码是否正确，如果正确，返回相关的用户数据，否则，返回null
     * @param account  账户
     * @param pwd   密码
     * @return
     */
    public User getUser(String account, String pwd) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            user = userDao.getUser(connection, account);
            if(user==null){   //没有找到该账户对应的用户数据或者密码不正确
                logger.warn("未查询到用户信息---->账户："+account);
            }
            else if(!user.getPassword().equals(pwd)){
                logger.warn("查询用户的密码错误---->账户："+account);
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(ResponseMange.SQLWRONG);
            throw new ServerException(ResponseMange.SQLWRONG);  //向上抛出异常
        }finally {
            BaseDao.closeResource(connection, null, null);
        }

        return user;
    }

    /**
     * 修改用户密码
     * @param account  账户
     * @param oldPwd  旧密码
     * @param newPwd  新密码
     * @return true-修改成功 false-修改失败
     */
    public boolean updatePwd(String account, String oldPwd, String newPwd) {
        boolean flag = false;
        Connection connection = null;
        try {

            User user = getUser(account, oldPwd);
            if(user==null){
                return false;
            }

            user = new User(account, newPwd);
            connection = BaseDao.getConnection();
            if(userDao.changePassword(connection, user)>0){
                flag = true;
                logger.info("用户密码修改成功---->账户："+account);
            }
            else{
                logger.info("用户密码修改失败---->账户："+account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(ResponseMange.SQLWRONG);
            throw new ServerException(ResponseMange.SQLWRONG);  //向上抛出异常
        }
        catch (ServerException e) {
            throw new ServerException(ResponseMange.SQLWRONG);  //向上抛出异常
        }
        finally {
            BaseDao.closeResource(connection, null, null);
        }

        return flag;
    }

    /**
     * 添加用户信息
     * @param account  账户
     * @param password  密码
     * @return  布尔值，true-添加成功  false-添加失败
     */
    public boolean insertUser(String account, String password) {
        boolean flag = false;
        Connection connection = null;

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);

        try{
            connection = BaseDao.getConnection();
            if(userDao.insertUser(connection, user)>0){
                flag = true;
                logger.info("添加用户成功---->账户："+account);
            }else{
                logger.info("添加用户失败---->账户："+account);
            }
        }catch (SQLException e) {
            if(e.getErrorCode()==1062){
                logger.error("账户名重复----->账户："+account);
                throw new ServerException("账户名重复");
            }
            logger.error(ResponseMange.SQLWRONG);
            throw new ServerException(ResponseMange.SQLWRONG);  //向上抛出异常
        }finally {
            BaseDao.closeResource(connection, null, null);
        }

        return flag;
    }

    /**
     * 向用户信息中添加歌曲信息
     * @param account  账户
     * @param musicId  歌曲id
     * @param pattern  播放模式
     * @return  true-添加成功  false-添加失败
     */
    public boolean updateMusic(String account, int musicId,  int pattern) {
        boolean flag = false;
        Connection connection = null;

        try{
            connection = BaseDao.getConnection();
            if(userDao.saveMusic(connection, account, musicId,pattern)>0){
                flag = true;
                logger.info("向用户信息中添加歌曲信息成功---->账户："+account);
            }else{
                logger.info("向用户信息中添加歌曲信息失败---->账户："+account);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            logger.error(ResponseMange.SQLWRONG);
            throw new ServerException(ResponseMange.SQLWRONG);  //向上抛出异常
        }finally {
            BaseDao.closeResource(connection, null, null);
        }

        return flag;
    }
}
