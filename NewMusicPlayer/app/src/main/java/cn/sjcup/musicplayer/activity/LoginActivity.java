package cn.sjcup.musicplayer.activity;

import android.app.Activity;
import android.content.Intent;
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

//public class LoginActivity extends AppCompatActivity {
public class LoginActivity extends Activity {

    //一些声明
    private TextView mMainTitle;   //主标题
    private TextView mBack;   //返回
    private TextView mRegister;  //注册
    private TextView mChangePwd;  //修改密码
    private Button mLogin;   //登录按钮
    private EditText mUserName;  //输入账户
    private EditText mPwd;   //输入密码
    private String userName, pwd;     //登录所需的账户和密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();  //初始化一些控件

        initEvent();  //初始化相关事件
    }

    private void initEvent(){

        //监听返回键的点击事件
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //登录界面销毁
                LoginActivity.this.finish();
            }
        });

        //注册按钮
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //跳转到注册界面
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //修改密码按钮
        mChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到修改密码界面
                Intent intent = new Intent(LoginActivity.this, ChangePwdActivity.class);
                startActivity(intent);
            }
        });

        //登录按钮
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName=mUserName.getText().toString().trim();  //获取用户名。.trim是为了去除字符串两侧对于空格
                pwd=mPwd.getText().toString().trim();  //获取登录密码

                //检测是否为空字符串
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginThread();
            }
        });
    }

    private void loginThread(){
        //涉及到网络的请求都需要在子线程内完成
        new Thread(){
            public void run(){
                try {
                    JSONObject result = RequestServlet.login(userName, pwd);

                    //将数据传递到主线程
                    Message msg = new Message();
                    msg.what=1;
                    msg.obj = result;
                    handler.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                JSONObject result = (JSONObject) msg.obj;

                if(result == null || !result.optString("account").equals(userName)){
                    Toast.makeText(LoginActivity.this, "账户错误", Toast.LENGTH_SHORT).show();
                }
                else{
                    String password = result.optString("password");
                    if(pwd.equals(password)){
                        //密码正确
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        //传递数据
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("result",result.toString());   //把用户信息传递到播放界面

                        //销毁登录界面
                        LoginActivity.this.finish();

                        //跳转到主界面，登录成功的状态传递到 MainActivity 中
                        startActivity(intent);
                        return;
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

    private void initView(){
        mMainTitle = this.findViewById(R.id.tv_main_title);
        mBack = this.findViewById(R.id.tv_back);
        mRegister = this.findViewById(R.id.tv_register);
        mChangePwd = this.findViewById(R.id.tv_change_psw);
        mLogin = this.findViewById(R.id.btn_login);
        mUserName = this.findViewById(R.id.et_user_name);
        mPwd = this.findViewById(R.id.et_psw);

        mMainTitle.setText("登录");
    }
}
