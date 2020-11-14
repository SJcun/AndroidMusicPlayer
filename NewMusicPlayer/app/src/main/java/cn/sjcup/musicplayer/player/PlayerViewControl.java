package cn.sjcup.musicplayer.player;

public interface PlayerViewControl {
    /*
        播放状态的通知
         */
    void onPlayerStateChange(int state);

    /*
    播放进度的改变
     */
    void onSeekChange(int seek);
}
