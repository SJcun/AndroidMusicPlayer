package cn.sjcup.musicplayer.player;

import cn.sjcup.musicplayer.activity.MainActivity;

public interface PlayerControl {
    /*
     *播放
     */
    void playOrPause(MainActivity.IsPlay playState);

    /*
    播放上一首
     */
    void playLast();

    /*
    播放下一首
     */
    void playNext();

    /*
    停止播放
     */
    void stopPlay();

    /*
    设置播放进度
     */
    void seekTo(int seek);
}
