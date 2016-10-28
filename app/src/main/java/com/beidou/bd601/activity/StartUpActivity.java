package com.beidou.bd601.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.beidou.bd601.R;

/**
 * Created by wangkuan on 2016/10/20.
 */
public class StartUpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 消除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_startup);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        new Handler().postDelayed(new Runnable(){
            public void run() {

                    Intent intent = new Intent(StartUpActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();

            }
        }, 1500);

    }



}
