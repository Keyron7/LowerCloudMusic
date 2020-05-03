package com.example.lowercloudmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private ImageView logo;
    private Button login;
    private Button facebook;
    private Button twitter;
    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        init();;
    }
    private void init(){
        logo=findViewById(R.id.logo);
        login=findViewById(R.id.login);
        facebook=findViewById(R.id.facebook_login);
        twitter=findViewById(R.id.twitter_login);
        logo.setImageResource(R.mipmap.yun);
        username = findViewById(R.id.user_name);
        password = findViewById(R.id.user_password);
        username.setText("kfc");
        password.setText("132");
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }






    //以下是隐藏软键盘的方法
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v instanceof EditText) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left) || !(event.getX() < right)
                    || !(event.getY() > top) || !(event.getY() < bottom);
        }
        return false;
    }

    private void hideKeyboard(IBinder token) {
        username.clearFocus();
        password.clearFocus();
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

}
