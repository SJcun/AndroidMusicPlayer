package cn.sjcup.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONObject;

import cn.sjcup.musicplayer.servlet.RequestServlet;

import static cn.sjcup.musicplayer.servlet.RequestServlet.login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
