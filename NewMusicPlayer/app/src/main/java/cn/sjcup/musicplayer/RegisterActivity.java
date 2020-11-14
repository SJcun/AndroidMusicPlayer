package cn.sjcup.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

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

import cn.sjcup.musicplayer.servlet.RequestServlet;

public class RegisterActivity extends Activity {

    private TextView mTitle;    //标题
    private TextView mBack;     //返回按钮
    private Button mRegister;     //注册按钮
    private EditText mName, mPwd, mPwdAgain;     //用户名，密码，再次输入的密码控件
    private String userName, pwd, pwdAgain;    //用户名，密码，再次输入的密码的控件的值
    private JSONObject registerInfo;  //注册返回信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();   //初始化界面

        initEvent();  //初始化事件
    }

    private void initView(){
        mTitle = this.findViewById(R.id.tv_main_title);
        mBack = this.findViewById(R.id.tv_back);
        mRegister = this.findViewById(R.id.btn_register);
        mName = this.findViewById(R.id.et_user_name);
        mPwd = this.findViewById(R.id.et_psw);
        mPwdAgain = this.findViewById(R.id.et_psw_again);

        mTitle.setText("注册");
    }

    private void initEvent(){

        //返回按钮
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });

        //注册按钮
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //获取输入框中的值
                userName = mName.getText().toString().trim();
                pwd = mPwd.getText().toString().trim();
                pwdAgain = mPwdAgain.getText().toString().trim();

                //先判断输入框是否有值
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(pwdAgain)) {
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                //判断两次输入的密码是否相同
                if (!pwd.equals(pwdAgain)) {
                    Toast.makeText(RegisterActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                }

                isExistThread(userName, pwd);   //先检测用户名是否已经存在
            }
        });
    }

    private void isExistThread(final String account, final String password){

        new Thread(){
            public void run(){
                try {
                    JSONObject result = RequestServlet.login(account, password);

                    Message msg = new Message();
                    msg.what=2;
                    msg.obj = result;
                    handler2.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private Handler handler2 = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 2) {
                JSONObject result = (JSONObject) msg.obj;

                if(result == null || !result.optString("account").equals(userName)){
                    //账户名未被注册
                    registerThread(userName, pwd);    //注册新账户
                }else{
                    //账户名已被注册
                    Toast.makeText(RegisterActivity.this, "用户名已被占用！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private void registerThread(final String account, final String password){

        new Thread(){
            public void run(){
                try {
                    JSONObject result = RequestServlet.Register(account, password);
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
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                registerInfo = (JSONObject) msg.obj;

                String result = registerInfo.optString("result");

                if(result.equals("注册成功！")){
                    Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    RegisterActivity.this.finish();
                }else if(result.equals("注册失败！")){
                    Toast.makeText(RegisterActivity.this, "注册失败，请重新注册", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
