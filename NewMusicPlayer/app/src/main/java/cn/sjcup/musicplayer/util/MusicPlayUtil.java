package cn.sjcup.musicplayer.util;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.sjcup.musicplayer.R;
import cn.sjcup.musicplayer.activity.MainActivity;
import cn.sjcup.musicplayer.player.PlayerControl;

import static cn.sjcup.musicplayer.activity.MainActivity.IMG;

public class MusicPlayUtil {

    private MainActivity mainActivity = null;
    private PlayerControl mPlayerControl = null;

    private  MusicPlayUtil(){
    }

    public static MusicPlayUtil musicPlayUtil = new MusicPlayUtil();

    public static MusicPlayUtil getInstance(){
        return musicPlayUtil;
    }

    //绑定MainActivity
    public void setMainActivity(MainActivity activity){
        this.mainActivity = activity;
        mPlayerControl = mainActivity.mPlayerControl;
    }

    //获取歌曲列表
    public JSONArray getMusicList(){
        return mainActivity.sMusicList;
    }

    //获取歌曲id
    public int getMusicId(){
        return mainActivity.musicId;
    }

    //获取歌曲总数
    public int getMusicNum(){
        return mainActivity.songNum;
    }

    //设置歌曲id
    public void setMusicId(int id){
        mainActivity.musicId = id;
    }

    //设置有关歌曲的界面
    public void setMusicView(MainActivity.IsPlay playState){
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
        if(playState == MainActivity.IsPlay.play){
            if ( mPlayerControl != null) {
                mPlayerControl.stopPlay();
            }
            mPlayerControl.playOrPause(playState);
        }
    }
}
