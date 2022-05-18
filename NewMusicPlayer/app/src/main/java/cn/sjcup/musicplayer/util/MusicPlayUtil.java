package cn.sjcup.musicplayer.util;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.sjcup.musicplayer.R;
import cn.sjcup.musicplayer.activity.MainActivity;
import cn.sjcup.musicplayer.player.PlayerControl;

import static cn.sjcup.musicplayer.activity.MainActivity.IMG;

/**
 * 播放歌曲工具类
 */
public class MusicPlayUtil {

    private MainActivity mainActivity = null;  //控制播放的实体类
    private PlayerControl mPlayerControl = null;  //音乐播放控制器

    private  MusicPlayUtil(){
    }

    public static MusicPlayUtil musicPlayUtil = new MusicPlayUtil();

    public static MusicPlayUtil getInstance(){
        return musicPlayUtil;
    }

    /**
     * 绑定主播放界面
     * @param activity
     */
    public void setMainActivity(MainActivity activity){
        this.mainActivity = activity;
        mPlayerControl = mainActivity.mPlayerControl;
    }

    /**
     * 获取歌曲列表
     * @return
     */
    public JSONArray getMusicList(){
        return mainActivity.sMusicList;
    }

    /**
     * 获取歌曲id
     * @return
     */
    public int getMusicId(){
        return mainActivity.musicId;
    }

    /**
     * 获取歌曲总数
     * @return
     */
    public int getMusicNum(){
        return mainActivity.songNum;
    }

    /**
     * 设置歌曲id
     * @param id
     */
    public void setMusicId(int id){
        mainActivity.musicId = id;
    }

    /**
     * 刷新有关歌曲的界面
     * @param playState
     */
    public void setMusicView(PlayState playState){
        try {
            JSONObject musicInfo = (JSONObject) mainActivity.sMusicList.get(mainActivity.musicId);
            String name = musicInfo.optString("name");
            String author = musicInfo.optString("author");
            String img = musicInfo.optString("img");
            mainActivity.playAddress=musicInfo.optString("address");
            mainActivity.mMusicPic.setImageUrl(IMG+img, R.mipmap.ic_launcher,R.mipmap.ic_launcher);
            mainActivity.mMusicName.setText(name);
            mainActivity.mMusicArtist.setText(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(playState == PlayState.PLAY_STATE_PLAY){
            if ( mPlayerControl != null) {
                mPlayerControl.stopPlay();
            }
            mPlayerControl.playOrPause(playState);
        }
    }
}
