package cn.sjcup.musicplayer.player;

import cn.sjcup.musicplayer.util.PlayState;

public interface PlayerViewControl {
    /*
        播放状态的通知
         */
    void onPlayerStateChange(PlayState state);

    /*
    播放进度的改变
     */
    void onSeekChange(int seek);
}
