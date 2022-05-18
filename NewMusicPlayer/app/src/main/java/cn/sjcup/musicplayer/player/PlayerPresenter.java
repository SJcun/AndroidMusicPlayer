package cn.sjcup.musicplayer.player;

import android.media.MediaPlayer;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import cn.sjcup.musicplayer.activity.MainActivity;
import cn.sjcup.musicplayer.util.CommonVariable;
import cn.sjcup.musicplayer.util.MusicPlayUtil;
import cn.sjcup.musicplayer.util.PlayPattern;
import cn.sjcup.musicplayer.util.PlayState;

/**
 * 音乐播放逻辑实现类
 */
public class PlayerPresenter implements PlayerControl {

    private MediaPlayer mMediaPlayer = null;  //歌曲播放使用的工具  MediaPlayer

    private static final String ADDRESS = CommonVariable.getIp()+"music/";  //固定的地址，音乐资源存储的地址（需要经过http请求）
    private PlayerViewControl mViewController = null;   //音乐播放界面控制类
    private MainActivity mMainActivity = null;   //控制音乐播放的主界面
    private MusicPlayUtil musicPlayUtil = MusicPlayUtil.getInstance();   //获取工具类实体类对象

    public PlayState mCurrentState = PlayState.PLAY_STATE_STOP;   //当前播放状态，默认状态是停止播放

    private Timer mTimer;   //定时器
    private SeekTimeTask mTimeTask;

    public PlayerPresenter(MainActivity activity){
        this.mMainActivity = activity;
    }

    /**
     * 播放或者暂停播放的实现逻辑
     * @param playState  播放状态
     */
    @Override
    public void playOrPause(PlayState playState) {
        if(mViewController == null){
            this.mViewController = mMainActivity.mPlayerViewControl;
        }

        if (mCurrentState == PlayState.PLAY_STATE_STOP || playState == PlayState.PLAY_STATE_PLAY) {
            try {
                mMediaPlayer = new MediaPlayer();
                //指定播放路径
                mMediaPlayer.setDataSource(ADDRESS + mMainActivity.playAddress);
                //准备播放
                mMediaPlayer.prepareAsync();
                //播放
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayer.start();
                    }
                });
                mCurrentState = PlayState.PLAY_STATE_PLAY;
                startTimer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (mCurrentState == PlayState.PLAY_STATE_PLAY) {
            //如果当前的状态为播放，那么就暂停
            if (mMediaPlayer != null) {
                mMediaPlayer.pause();
                mCurrentState = PlayState.PLAY_STATE_PAUSE;
                stopTimer();
            }
        } else if (mCurrentState == PlayState.PLAY_STATE_PAUSE) {
            //如果当前的状态为暂停，那么继续播放
            if (mMediaPlayer != null) {
                mMediaPlayer.start();
                mCurrentState = PlayState.PLAY_STATE_PLAY;
                startTimer();
            }
        }

        mViewController.onPlayerStateChange(mCurrentState);
    }

    /**
     * 播放上一首
     */
    @Override
    public void playLast() {
        // 顺序播放
        if (mMainActivity.playPattern == PlayPattern.PLAY_IN_ORDER) {
            if (mMainActivity.musicId == 0) {
                mMainActivity.musicId = mMainActivity.songNum-1;
                musicPlayUtil.setMusicView(PlayState.PLAY_STATE_PLAY);
            } else {
                mMainActivity.musicId = mMainActivity.musicId - 1;
                musicPlayUtil.setMusicView(PlayState.PLAY_STATE_PLAY);
            }
        }

        //随机播放（这个逻辑有点问题）
        else if (mMainActivity.playPattern == PlayPattern.PLAY_RANDOM) {
            mMainActivity.musicId = ( mMainActivity.musicId+(int)(1+Math.random()*(20-1))) % mMainActivity.songNum ;
            musicPlayUtil.setMusicView(PlayState.PLAY_STATE_PLAY);
        }
        //单曲循环
        else if(mMainActivity.playPattern==PlayPattern.PLAY_SINGLE){
            musicPlayUtil.setMusicView(PlayState.PLAY_STATE_PLAY);
        }
    }

    /**
     * 播放下一首
     */
    @Override
    public void playNext() {
        // 顺序播放
        if (mMainActivity.playPattern == PlayPattern.PLAY_IN_ORDER) {

            mMainActivity.musicId = (mMainActivity.musicId + 1) % mMainActivity.songNum;
            musicPlayUtil.setMusicView(PlayState.PLAY_STATE_PLAY);

        }
        //随机播放
        else if (mMainActivity.playPattern == PlayPattern.PLAY_RANDOM) {
            mMainActivity.musicId = (mMainActivity.musicId+(int)(1+Math.random()*(20-1+1))) % mMainActivity.songNum ;
            musicPlayUtil.setMusicView(PlayState.PLAY_STATE_PLAY);
        }
        //单曲循环
        else if(mMainActivity.playPattern == PlayPattern.PLAY_SINGLE){
            musicPlayUtil.setMusicView(PlayState.PLAY_STATE_PLAY);
        }
    }

    /**
     * 停止播放
     */
    @Override
    public void stopPlay() {
        if (mMediaPlayer != null ) {
            mMediaPlayer.stop();
            mCurrentState= PlayState.PLAY_STATE_STOP;
            stopTimer();
            //更新播放状态
            if (mViewController != null) {
                mViewController.onPlayerStateChange(mCurrentState);
            }
            mMediaPlayer.release();//释放资源
            mMediaPlayer=null;
        }
    }

    /**
     * 拖动进度条后的逻辑处理
     * @param seek  百分比的一个进度
     */
    @Override
    public void seekTo(int seek) {
        //0~100之间
        //需要做一个转换，得到的seek其实是一个百分比
        if (mMediaPlayer != null) {
            //getDuration()获取音频时长
            int tarSeek=(int)(seek*1f/100*mMediaPlayer.getDuration());
            mMediaPlayer.seekTo(tarSeek);
        }
    }

    /**
     * 开启定时器，开启后，按一定频率刷新进度条进度。间隔越短，进度条越顺滑
     */
    private void startTimer() {
        if (mTimer == null) {
            mTimer=new Timer();
        }
        if (mTimeTask == null) {
            mTimeTask = new SeekTimeTask();
        }
        mTimer.schedule(mTimeTask,0,500);
    }

    /**
     * 停止计时器
     */
    private void stopTimer() {
        if (mTimeTask != null) {
            mTimeTask.cancel();
            mTimeTask=null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer=null;
        }
    }

    /**
     * 刷新进度条的实际执行者
     */
    private class SeekTimeTask extends TimerTask {

        @Override
        public void run() {
            //获取当前的播放进度
            if (mMediaPlayer != null && mViewController!=null) {
                int currentPosition = mMediaPlayer.getCurrentPosition();
                //记录百分比
                int curPosition=(int)(currentPosition*1.0f/mMediaPlayer.getDuration()*100);
                if(curPosition<=100) {
                    mViewController.onSeekChange(curPosition);
                }
            }
        }
    }
}
