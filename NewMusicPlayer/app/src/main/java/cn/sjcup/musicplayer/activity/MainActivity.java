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

import java.io.InputStream;
import java.util.Properties;

import cn.sjcup.musicplayer.R;
import cn.sjcup.musicplayer.player.PlayerControl;
import cn.sjcup.musicplayer.player.PlayerPresenter;
import cn.sjcup.musicplayer.player.PlayerViewControl;
import cn.sjcup.musicplayer.service.PlayerService;
import cn.sjcup.musicplayer.servlet.RequestServlet;
import cn.sjcup.musicplayer.util.CommonVariable;
import cn.sjcup.musicplayer.util.JsonUtil;
import cn.sjcup.musicplayer.util.MusicPlayUtil;
import cn.sjcup.musicplayer.util.PlayPattern;
import cn.sjcup.musicplayer.util.PlayState;

public class MainActivity extends Activity {

    private static final String Ip = CommonVariable.getIp();
    private String account;    //账户
    public int musicId;   //歌曲id
    public PlayPattern playPattern;  //播放模式
    public String playAddress;  //音乐文件地址
    public static final String IMG = Ip+"image/";    //音乐图片的通用地址
    private boolean isUserTouchProgressBar = false;   //判断手是否触摸进度条的状态
    private Intent musicIntent;
    private PlayerConnection mPlayerConnection;
    public int songNum = 0;  //歌曲总数

    //以下是绑定界面使用
    private SeekBar mSeekBar;  //进度条
    private Button mPlayOrPause;  //播放按钮
    private Button mPlayPattern;  //播放模式按钮
    private Button mPlayLast;  //播放上一首按钮
    private Button mPlayNext;  //播放下一首按钮
    private Button mPlayMenu;  //打开音乐列表
    private Button mQuit;   //退出按钮
    public TextView mMusicName;  //显示歌曲名称
    public TextView mMusicArtist;  //显示演唱者
    public SmartImageView mMusicPic;  //显示歌曲图片

    public static JSONArray sMusicList;  //存储歌曲信息


    public PlayerControl mPlayerControl = new PlayerPresenter(this);
    private MusicPlayUtil musicPlayUtil = MusicPlayUtil.getInstance();   //获取工具类实体类对象

    /**
     * 退回手机主界面的事件
     * @param keyCode
     * @param event
     * @return
     */
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

    /**
     * 界面建立默认执行的事件
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicPlayUtil.setMainActivity(this);   //把本对象交给工具类
        initUserData();   //初始化用户信息
        initView(); //初始化界面
        initEvent();   //初始化事件
    }

    /**
     * 初始化用户信息（用户信息由登录界面传递过来，经登录界面验证通过的用户信息是正确的信息）
     */
    private void initUserData(){
        Intent intent = getIntent();

        String userStr = intent.getStringExtra("result");
        JSONObject userData = JsonUtil.getInstance().getJSON(userStr);
        account = userData.optString("account");
        musicId = userData.optInt("music_id");
        int num = userData.optInt("pattern");

        if(num==0){
            playPattern = PlayPattern.PLAY_IN_ORDER;
        }else if(num==1){
            playPattern = PlayPattern.PLAY_RANDOM;
        }else{
            playPattern = PlayPattern.PLAY_SINGLE;
        }
    }

    /**
     * 初始化用户信息（已弃用）
     */
    private void initService(){
        musicIntent = new Intent(this, PlayerService.class);
        startService(musicIntent);

        initBindService();   //绑定服务
    }

    /**
     * 绑定服务（已经弃用）
     */
    private void initBindService() {
        //Log.d(Tag, "initBindService --> ");
        Intent intent=new Intent(this, PlayerService.class);
        if (mPlayerConnection == null) {
            mPlayerConnection = new PlayerConnection();
        }
        bindService(intent, mPlayerConnection, BIND_AUTO_CREATE);
    }

    /**
     * 服务相关（已经弃用，代码无用）
     */
    private class PlayerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPlayerControl = (PlayerPresenter) service;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mPlayerControl=null;
        }
    }

    /**
     * 初始化相关事件，为按钮设置监听
     */
    private void initEvent(){

        //播放/暂停按钮
        mPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayerControl!=null){
                    mPlayerControl.playOrPause(PlayState.PLAY_STATE_STOP);
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

                if (playPattern==PlayPattern.PLAY_SINGLE) {
                    mPlayPattern.setBackgroundResource(R.drawable.xunhuanbofang);
                    playPattern = PlayPattern.PLAY_IN_ORDER;
                }else if(playPattern==PlayPattern.PLAY_IN_ORDER){
                    mPlayPattern.setBackgroundResource(R.drawable.suijibofang);
                    playPattern = PlayPattern.PLAY_RANDOM;
                } else if (playPattern==PlayPattern.PLAY_RANDOM) {
                    mPlayPattern.setBackgroundResource(R.drawable.danquxunhuan);
                    playPattern = PlayPattern.PLAY_SINGLE;
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

    /**
     * 界面刷新的实现类（内部类）
     */
    public PlayerViewControl mPlayerViewControl = new PlayerViewControl() {
        @Override
        public void onPlayerStateChange(PlayState state) {
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

        /**
         * 刷新进度条
         * @param seek  百分比的进度
         */
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

    /**
     * 保存数据到数据库里（这里使用了一个匿名对象开启线程，不太好，后续改进）
     */
    private void saveDataToDB(){
        new Thread() {
            public void run () {
                try {
                    JSONObject result = RequestServlet.getInstance().savePlayerInformation(account, musicId, playPattern);
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

    /**
     * 保存完毕后控制app退出
     */
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

    /**
     * 初始化界面。绑定界面部件和获取歌曲信息
     */
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
        if (playPattern== PlayPattern.PLAY_IN_ORDER) {
            mPlayPattern.setBackgroundResource(R.drawable.xunhuanbofang);
        }else if(playPattern==PlayPattern.PLAY_RANDOM){
            mPlayPattern.setBackgroundResource(R.drawable.suijibofang);
        } else if (playPattern==PlayPattern.PLAY_SINGLE) {
            mPlayPattern.setBackgroundResource(R.drawable.danquxunhuan);
        }

        //获取音乐列表
        getMusicListThread();
    }

    /**
     * 获取音乐列表
     */
    private void getMusicListThread(){
        new Thread(){
            @Override
            public void run() {
                try{
                    JSONArray result = RequestServlet.getInstance().getMusicList();
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

    /**
     * 获取到歌曲信息就要刷新播放主界面
     */
    private Handler handler2 = new Handler(){
        public void handleMessage(android.os.Message msg) {
            try {
                if (msg.what == 2) {
                    sMusicList = (JSONArray) msg.obj;
                    songNum = sMusicList.length();

                    //根据用户数据和歌曲列表初始化有关歌曲的界面
                    musicPlayUtil.setMusicView(PlayState.PLAY_STATE_STOP);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    /**
     * 停止播放事件
     */
    private void stop() {
        mPlayerControl.stopPlay();
        //stopService(musicIntent);
    }
}
