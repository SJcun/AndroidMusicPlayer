package cn.sjcup.musicplayer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import cn.sjcup.musicplayer.R;
import cn.sjcup.musicplayer.servlet.RequestServlet;

/**
 * 控制密码修改
 */
public class ChangePwdActivity extends Activity {

    private EditText mUserName;  //账户
    private EditText mPassword;  //密码
    private EditText mNewPwd;  //新密码
    private EditText mNewPwdAgain;  //重新输入的新密码
    private TextView mTitle;  //标头
    private Button mChangePwd;  //确认修改按钮
    private TextView mBack;  //返回按钮

    private String userName, password, newPwd, newPwdAgain;

    /**
     * 界面创建默认执行的事件
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);

        initView(); //初始化界面

        initEvent();  //初始化事件
    }

    /**
     * 初始化界面，绑定界面控件
     */
    private void initView(){
        mUserName = this.findViewById(R.id.et_user_name);
        mPassword = this.findViewById(R.id.et_psw);
        mNewPwd = this.findViewById(R.id.et_new_psw);
        mNewPwdAgain = this.findViewById(R.id.et_new_psw_again);
        mTitle = this.findViewById(R.id.tv_main_title);
        mChangePwd = this.findViewById(R.id.btn_change_psw);
        mBack = this.findViewById(R.id.tv_back);

        mTitle.setText("修改密码");
    }

    /**
     * 初始化事件，为按钮设置监听
     */
    private void initEvent(){
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePwdActivity.this.finish();
            }
        });

        mChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = mUserName.getText().toString().trim();
                password = mPassword.getText().toString().trim();
                newPwd = mNewPwd.getText().toString().trim();
                newPwdAgain = mNewPwdAgain.getText().toString().trim();

                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(ChangePwdActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                } else if(TextUtils.isEmpty(password)){
                    Toast.makeText(ChangePwdActivity.this, "请输入原密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(newPwd)){
                    Toast.makeText(ChangePwdActivity.this, "请输入新密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(newPwdAgain)){
                    Toast.makeText(ChangePwdActivity.this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!newPwd.equals(newPwdAgain)){
                    Toast.makeText(ChangePwdActivity.this, "两次新密码输入不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(newPwd.equals(password)){
                    Toast.makeText(ChangePwdActivity.this, "新密码与原密码相同", Toast.LENGTH_SHORT).show();
                }

                changePwdThread(); //修改密码线程;
            }
        });
    }

    /**
     * 修改密码的线程（目前线程都是使用匿名创建的，后续改进）
     */
    private void changePwdThread(){
        new Thread(){
            public void run(){
                JSONObject result = RequestServlet.getInstance().changePwd(userName, newPwd, password);

                Message msg = new Message();
                msg.what = 2;
                msg.obj = result;
                handler2.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 刷新界面信息
     */
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==2){
                JSONObject result = (JSONObject) msg.obj;

                String str = result.optString("result");
                if(str.equals("修改成功！")){
                    Toast.makeText(ChangePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    ChangePwdActivity.this.finish();
                }else{
                    Toast.makeText(ChangePwdActivity.this, str, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
