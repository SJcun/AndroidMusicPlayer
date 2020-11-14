package dao.music;

import pojo.Music;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface MusicDao {

    //获取所有歌曲信息
    public List<Music> getMusicList(Connection connection) throws SQLException;
}
