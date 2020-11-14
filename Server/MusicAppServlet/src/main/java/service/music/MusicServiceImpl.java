package service.music;

import dao.BaseDao;
import dao.music.MusicDao;
import dao.music.MusicDaoImpl;
import org.junit.Test;
import pojo.Music;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicServiceImpl implements MusicService {

    private MusicDao musicDao;

    public MusicServiceImpl() {
        musicDao = new MusicDaoImpl();
    }

    public List<Music> getMusicList() {
        List<Music> musicList = new ArrayList<Music>();
        Connection connection = null;

        try {
            connection = BaseDao.getConnextion();
            musicList = musicDao.getMusicList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return musicList;
    }

}
