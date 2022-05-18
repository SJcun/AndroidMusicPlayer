package dao.music;

import dao.BaseDao;
import pojo.Music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作数据库上的歌曲信息
 */
public class MusicDaoImpl implements MusicDao {

    /**
     * 获取全部歌曲信息
     * @param connection  数据库连接
     * @return  歌曲信息
     * @throws SQLException
     */
    public List<Music> getMusicList(Connection connection) throws SQLException {

        ArrayList<Music> musicList = new ArrayList<Music>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        if (connection!=null) {
            String sql = "select * from music";
            Object[] params = {};
            resultSet = BaseDao.execute(connection, sql, params, resultSet, preparedStatement);
            while(resultSet.next()){
                Music music = new Music();
                music.setMusicId(resultSet.getInt("music_id"));
                music.setName(resultSet.getString("name"));
                music.setAuthor(resultSet.getString("author"));
                music.setAddress(resultSet.getString("address"));
                music.setImg(resultSet.getString("img"));
                music.setCreateTime(resultSet.getDate("create_time"));
                music.setChangeTime(resultSet.getDate("change_time"));
                music.setRemark(resultSet.getString("remark"));

                musicList.add(music);
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }

        return musicList;
    }
}
