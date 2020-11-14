package cn.sjcup.musicplayer.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.sjcup.musicplayer.R;
import cn.sjcup.musicplayer.player.PlayerControl;
import cn.sjcup.musicplayer.player.PlayerPresenter;
import cn.sjcup.musicplayer.player.PlayerViewControl;
import cn.sjcup.musicplayer.service.PlayerService;
import cn.sjcup.musicplayer.servlet.RequestServlet;

public class MainActivity extends Activity {

    private String account;    //账户
    public int musicId;   //歌曲id
    public int playPattern;  //播放模式
    public String playAddress;  //音乐文件地址
    public static final String IMG = "http://192.168.43.230:8080/musicplayer/image/";    //音乐图片的通用地址
    private boolean isUserTouchProgressBar = false;   //判断手是否触摸进度条的状态
    private Intent musicIntent;
    private PlayerConnection mPlayerConnection;
    public int songNum = 0;  //歌曲总数

    private SeekBar mSeekBar;  //进度条
    private Button mPlayOrPause;
    private Button mPlayPattern;
    private Button mPlayLast;
    private Button mPlayNext;
    private Button mPlayMenu;
    private Button mQuit;
    private TextView mMusicName;
    private TextView mMusicArtist;
    private SmartImageView mMusicPic;

    public final int PLAY_IN_ORDER = 0;   //顺序播放
    public final int PLAY_RANDOM = 1;    //随机播放
    public final int PLAY_SINGLE = 2;    //单曲循环

    //播放状态
    public final int PLAY_STATE_PLAY=1;   //在播
    public final int PLAY_STATE_PAUSE=2;  //暂停
    public final int PLAY_STATE_STOP=3;   //未播

    public static JSONArray sMusicList;

    //public PlayerViewControl mPlayerViewControl = ViewControl;
    private PlayerControl mPlayerControl = new PlayerPresenter(this);

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUserData();   //初始化用户信息

        initView(); //初始化界面

        initEvent();   //初始化事件

        //initService();  //初始化服务
    }

    //初始化用户信息
    private void initUserData(){
        Intent intent = getIntent();

        String userStr = intent.getStringExtra("result");
        JSONObject userData = RequestServlet.getJSON(userStr);
        account = userData.optString("account");
        musicId = userData.optInt("music_id");
        playPattern = userData.optInt("pattern");
    }

    //初始化服务
    private void initService(){
        musicIntent = new Intent(this, PlayerService.class);
        startService(musicIntent);

        initBindService();   //绑定服务
    }

    private void initBindService() {
        //Log.d(Tag, "initBindService --> ");
        Intent intent=new Intent(this, PlayerService.class);
        if (mPlayerConnection == null) {
            mPlayerConnection = new PlayerConnection();
        }
        bindService(intent, mPlayerConnection, BIND_AUTO_CREATE);
    }

    private class PlayerConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //Log.d(Tag, "onServiceConnected "+service);
            //mplayerControl
            mPlayerControl = (PlayerPresenter) service;
            //mplayerControl
            //playerControl.registerViewController(playerViewControl);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //Log.d(Tag, "onServiceDisconnected ");
            mPlayerControl=null;
        }
    }

    //初始化事件
    private void initEvent(){

        //播放/暂停按钮
        mPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayerControl!=null){
                    mPlayerControl.playOrPause(IsPlay.notPlay);
                }
            }
        });

        //播放上一首
        mPlayLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayerControl!=null){
                    mPlayerControl.playLast();
                }
            }
        });

        //播放下一首
        mPlayNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayerControl!=null){
                    mPlayerControl.playNext();
                }
            }
        });

        //播放模式
        mPlayPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPattern = (playPattern+1)%3;

                if (playPattern==0) {
                    mPlayPattern.setBackgroundResource(R.drawable.xunhuanbofang);
                }else if(playPattern==1){
                    mPlayPattern.setBackgroundResource(R.drawable.suijibofang);
                } else if (playPattern==2) {
                    mPlayPattern.setBackgroundResource(R.drawable.danquxunhuan);
                }
            }
        });

        //音乐列表
        mPlayMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MusicListActivity.class);
                startActivity(intent);
            }
        });

        //退出按钮
        mQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "正在保存信息…", Toast.LENGTH_SHORT).show();
                saveDataToDB();
            }
        });

        //进度条
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //进度条发生改变
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //手已经触摸上去了拖动
                isUserTouchProgressBar=true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                int touchProgress=seekBar.getProgress();
                //停止拖动
                if ( mPlayerControl != null) {
                    mPlayerControl.seekTo(touchProgress);
                }
                isUserTouchProgressBar=false;
            }
        });
    }

    public PlayerViewControl mPlayerViewControl = new PlayerViewControl() {
        @Override
        public void onPlayerStateChange(int state) {
            //根据播放状态来修改UI
            switch (state) {
                case PLAY_STATE_PLAY:
                    //播放中的话，我们要修改按钮显示为暂停
                    mPlayOrPause.setBackgroundResource(R.drawable.bofangb);
                    break;
                case PLAY_STATE_PAUSE:
                case PLAY_STATE_STOP:
                    mPlayOrPause.setBackgroundResource(R.drawable.bofang);
                    break;
            }
        }

        @Override
        public void onSeekChange(final int seek) {
            //改变播放进度，有一个条件：当用户的手触摸到进度条的时候，就不更新。
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!isUserTouchProgressBar) {
                        mSeekBar.setProgress(seek);
                        if(seek==100) {
                            mPlayerControl.playNext();
                        }
                    }
                }
            });
        }
    };

    //保存数据到数据库里
    private void saveDataToDB(){
        new Thread() {
            public void run () {
                try {
                    JSONObject result = RequestServlet.savePlayerInformation(account, musicId, playPattern);
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = result;
                    handler1.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    Handler handler1 = new Handler(){
        public void handleMessage(android.os.Message msg) {
            try {
                if (msg.what == 1) {
                    JSONObject result = (JSONObject) msg.obj;
                    stop();
                    MainActivity.this.finish();
                    Toast.makeText(MainActivity.this, "已退出", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    //初始化界面
    private void initView(){
        mSeekBar = (SeekBar) this.findViewById(R.id.seek_bar);
        mPlayOrPause = (Button) this.findViewById(R.id.play_or_pause_btn);
        mPlayPattern = (Button) this.findViewById(R.id.play_way_btn);
        mPlayLast= (Button) this.findViewById(R.id.play_last_btn);
        mPlayNext = (Button) this.findViewById(R.id.play_next_btn);
        mPlayMenu = (Button) this.findViewById(R.id.play_menu_btn);
        mQuit=(Button) this.findViewById(R.id.quit_btn);
        mMusicName = (TextView) this.findViewById(R.id.text_view_name);
        mMusicArtist = (TextView) this.findViewById(R.id.text_view_artist);
        mMusicPic = (SmartImageView) this.findViewById(R.id.siv_icon);

        //模式转换
        if (playPattern==PLAY_IN_ORDER) {
            mPlayPattern.setBackgroundResource(R.drawable.xunhuanbofang);
        }else if(playPattern==PLAY_RANDOM){
            mPlayPattern.setBackgroundResource(R.drawable.suijibofang);
        } else if (playPattern==PLAY_SINGLE) {
            mPlayPattern.setBackgroundResource(R.drawable.danquxunhuan);
        }

        //获取音乐列表
        getMusicListThread();
    }

    public enum IsPlay{
        play, notPlay
    }

    //设置有关歌曲的界面
    public void setMusicView(IsPlay playState){
        try {
            JSONObject musicInfo = (JSONObject) sMusicList.get(musicId);
            String name = musicInfo.optString("name");
            String author = musicInfo.optString("author");
            String img = musicInfo.optString("img");
            playAddress=musicInfo.optString("address");
            mMusicPic.setImageUrl(IMG+img,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
            mMusicName.setText(name);
            mMusicArtist.setText(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(playState == IsPlay.play){
            if ( mPlayerControl != null) {
                mPlayerControl.stopPlay();
            }
            mPlayerControl.playOrPause(playState);
        }
    }

    //获取音乐列表
    private void getMusicListThread(){
        new Thread(){
            @Override
            public void run() {
                try{
                    JSONArray result = RequestServlet.getMusicList();
                    Message msg = new Message();
                    msg.what = 2;
                    msg.obj = result;
                    handler2.sendMessage(msg);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private Handler handler2 = new Handler(){
        public void handleMessage(android.os.Message msg) {
            try {
                if (msg.what == 2) {
                    sMusicList = (JSONArray) msg.obj;
                    songNum = sMusicList.length();

                    //根据用户数据和歌曲列表初始化有关歌曲的界面
                    setMusicView(IsPlay.notPlay);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    private void stop() {
        mPlayerControl.stopPlay();
        //stopService(musicIntent);
    }
}
