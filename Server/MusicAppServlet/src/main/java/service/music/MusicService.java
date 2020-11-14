package service.music;

import pojo.Music;

import java.util.List;

public interface MusicService {

    //获取所有的歌曲信息
    public List<Music> getMusicList();
}
