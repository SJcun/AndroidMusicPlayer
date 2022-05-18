package cn.sjcup.musicplayer.player;

import cn.sjcup.musicplayer.util.PlayState;

public interface PlayerControl {
    /*
     *播放
     */
    void playOrPause(PlayState playState);

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
