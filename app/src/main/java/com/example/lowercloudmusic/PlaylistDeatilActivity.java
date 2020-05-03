package com.example.lowercloudmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PlaylistDeatilActivity extends AppCompatActivity {
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_deatil);
        initview();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaylistDeatilActivity.this,MediaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initview() {
        back = findViewById(R.id.back1);
    }
}
