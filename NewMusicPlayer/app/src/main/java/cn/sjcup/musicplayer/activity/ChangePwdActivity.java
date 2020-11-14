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

public class ChangePwdActivity extends Activity {

    private EditText mUserName;
    private EditText mPassword;
    private EditText mNewPwd;
    private EditText mNewPwdAgain;
    private TextView mTitle;
    private Button mChangePwd;
    private TextView mBack;

    private String userName, password, newPwd, newPwdAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);

        initView(); //初始化界面

        initEvent();  //初始化事件
    }

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

                isTrueThread();  //判断用户信息是否正确
            }
        });
    }

    private void isTrueThread(){
        new Thread(){
            public void run(){
                JSONObject result = RequestServlet.login(userName, password);

                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==1){
                JSONObject userInformation = (JSONObject) msg.obj;

                if(userInformation == null || !userInformation.optString("account").equals(userName)){
                    Toast.makeText(ChangePwdActivity.this, "该账户不存在", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!userInformation.optString("password").equals(password)){
                    Toast.makeText(ChangePwdActivity.this, "原密码错误", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    changePwdThread(); //修改密码线程
                }
            }
        }
    };

    private void changePwdThread(){
        new Thread(){
            public void run(){
                JSONObject result = RequestServlet.changePwd(userName, newPwd);

                Message msg = new Message();
                msg.what = 2;
                msg.obj = result;
                handler2.sendMessage(msg);
            }
        }.start();
    }

    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==2){
                JSONObject result = (JSONObject) msg.obj;

                String str = result.optString("result");
                if(str.equals("修改成功！")){
                    Toast.makeText(ChangePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    ChangePwdActivity.this.finish();
                }else if(str.equals("修改失败！")){
                    Toast.makeText(ChangePwdActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
