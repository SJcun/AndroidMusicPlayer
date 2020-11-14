package service.user;

import dao.BaseDao;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import org.junit.Test;
import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    public User login(String account) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnextion();
            user = userDao.getUser(connection, account);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }

        return user;
    }

    public boolean updatePwd(String account, String password) {
        boolean flag = false;
        Connection connection = null;

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);

        try {
            connection = BaseDao.getConnextion();
            if(userDao.changePassword(connection, user)>0){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }

        return flag;
    }

    public boolean insertUser(String account, String password) {
        boolean flag = false;
        Connection connection = null;

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);

        try{
            connection = BaseDao.getConnextion();
            if(userDao.insertUser(connection, user)>0){
                flag = true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }

        return flag;
    }

    public boolean updateMusic(String account, int musicId,  int pattern) {
        boolean flag = false;
        Connection connection = null;

        try{
            connection = BaseDao.getConnextion();
            if(userDao.saveMusic(connection, account, musicId,pattern)>0){
                flag = true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }

        return flag;
    }
}
