package service.music;

import Expection.ServerException;
import dao.BaseDao;
import dao.music.MusicDao;
import dao.music.MusicDaoImpl;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import pojo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 处理有关歌曲的逻辑类
 */
public class MusicServiceImpl implements MusicService {

    private MusicDao musicDao;

    public MusicServiceImpl() {
        musicDao = new MusicDaoImpl();
    }

    private Logger logger = LoggerFactory.getLogger(MusicServiceImpl.class);

    /**
     * 获取音乐列表信息
     * @return  所有音乐信息
     */
    public List<Music> getMusicList() {
        List<Music> musicList;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            musicList = musicDao.getMusicList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(ResponseMange.SQLWRONG);
            throw new ServerException(ResponseMange.SQLWRONG);  //向上抛出异常
        } finally {
            //由于资源连接不会被gc回收，所以一定要手动关闭数据库连接
            BaseDao.closeResource(connection, null, null);
        }

        return musicList;
    }

}
