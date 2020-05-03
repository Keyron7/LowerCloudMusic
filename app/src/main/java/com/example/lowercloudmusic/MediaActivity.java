package com.example.lowercloudmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lowercloudmusic.Base.Music;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;

public class MediaActivity extends AppCompatActivity {
    private ArrayList<Music> musicList = new ArrayList<Music>();
    private ImageButton back;
    private TextView name;
    private ImageButton details;
    private ImageView ablumlogo;
    private RadioButton playrandowm;
    private RadioButton playsequence;
    private ImageButton previous;
    private ImageButton playorpause;
    private ImageButton next;
    private TextView versonprevious;
    private TextView versonnext;
    private SeekBar seekBar;
    private TextView musiccur;
    private TextView musiclength;
    private MediaPlayer player;
    private Intent intent ;
    private int id;

    int position1=0;
    int count=0;

    int duration=0; //获取时长(ms)
    int current=0; //当前音乐播放位置
    boolean isOver=false; //是否播放完毕

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.login);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
//        if (ActivityCompat.checkSelfPermission( MediaActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED ) {
//            ActivityCompat.requestPermissions(MediaActivity.this,
//                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, 123);
//            return;
//        }
//        player = new MediaPlayer();
        initview();
//        musicList=ScannerMusic();
//        count=musicList.size();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this,PlaylistDeatilActivity.class);
                startActivity(intent);
            }
        });
//        playorpause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isOver=false;
//                if( player.isPlaying() ){
//                    player.pause();
//                    playorpause.setImageResource(R.mipmap.pasue);
//                }else{
//                    player.start();
//                    playorpause.setImageResource(R.mipmap.pb);
//                    //后续，再次启动子线程
//                    new Thread() {
//                        @Override
//                        public void run() {
//                            while ( !isOver ) {
//                                try {
//                                    Thread.sleep(50); //每50ms执行一次mTimerTask任务
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                mHandler.sendEmptyMessage(0); //直接发送一个what=0的空消息
//                            }
//                        }
//                    }.start();
//                }
//            }
//        });
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                position1+=1;
//                if(position1>=count)
//                {
//                    position1=0;
//                }
//                Music m=musicList.get(position1);
//
//                name.setText("正在播放："+m.getTitle());
//                String music_url=m.getUrl(); //获得音乐路径
//                isOver=false;
//                playorpause.setImageResource(R.mipmap.pasue);
//                try{
//                    player.reset(); // 重置
//                    player.setDataSource(music_url);
//                    player.prepare(); // 准备
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                duration=player.getDuration(); //获取音乐时长(ms)
//                SimpleDateFormat sdf=new SimpleDateFormat("mm:ss");
//                String time=sdf.format(duration);
//                musiclength.setText(""+time);
//                seekBar.setMax(duration); //给SeekBar设置最大时长
//                player.start(); //播放
//                new Thread() {
//                    @Override
//                    public void run() {
//                        while ( !isOver ) {
//                            try {
//                                Thread.sleep(50); //每50ms执行一次mTimerTask任务
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            mHandler.sendEmptyMessage(0); //直接发送一个what=0的空消息
//                        }
//                    }
//                }.start();
//            }
//        });
//        previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                position1-=1;
//                if(position1<=0)
//                {
//                    position1=count-1;
//                }
//                Music m=musicList.get(position1);
//
//                name.setText("正在播放："+m.getTitle());
//                String music_url=m.getUrl(); //获得音乐路径
//                isOver=false;
//                playorpause.setImageResource(R.mipmap.pasue);
//                try{
//                    player.reset(); // 重置
//                    player.setDataSource(music_url);
//                    player.prepare(); // 准备
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                duration=player.getDuration(); //获取音乐时长(ms)
//                SimpleDateFormat sdf=new SimpleDateFormat("mm:ss");
//                String time=sdf.format(duration);
//                musiclength.setText(""+time);
//                seekBar.setMax(duration); //给SeekBar设置最大时长
//                player.start(); //播放
//                new Thread() {
//                    @Override
//                    public void run() {
//                        while ( !isOver ) {
//                            try {
//                                Thread.sleep(50); //每50ms执行一次mTimerTask任务
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            mHandler.sendEmptyMessage(0); //直接发送一个what=0的空消息
//                        }
//                    }
//                }.start();
//
//            }
//        });
//        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                position1+=1;
//                if(position1>=count)
//                {
//                    position1=0;
//                }
//                Music m=musicList.get(position1);
//                name.setText("正在播放："+m.getTitle());
//                String music_url=m.getUrl(); //获得音乐路径
//                isOver=false;
//                playorpause.setImageResource(R.mipmap.pasue);
//                try{
//                    mp.reset(); // 重置
//                    mp.setDataSource(music_url);
//                    mp.prepare(); // 准备
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                duration=mp.getDuration(); //获取音乐时长(ms)
//                SimpleDateFormat sdf=new SimpleDateFormat("mm:ss");
//                String time=sdf.format(duration);
//                musiclength.setText(""+time);
//                seekBar.setMax(duration); //给SeekBar设置最大时长
//                mp.start(); //播放
//                new Thread() {
//                    @Override
//                    public void run() {
//                        while ( !isOver ) {
//                            try {
//                                Thread.sleep(50); //每50ms执行一次mTimerTask任务
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            mHandler.sendEmptyMessage(0); //直接发送一个what=0的空消息
//                        }
//                    }
//                }.start();
//            }
//        });
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                current=seekBar.getProgress();
//                musiccur.setText(""+current);
//                player.seekTo(current);
//            }
//        });
    }
//    private Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            if (msg.what==0){
//                if (!isOver){
//                    current = player.getCurrentPosition();
//                    seekBar.setProgress(current);
//                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
//                    String time = simpleDateFormat.format(current);
//                    musiccur.setText(""+time);
//                }
//            }
//        }
//    };
//    public ArrayList<Music> ScannerMusic() {
//        ArrayList<Music> list=new ArrayList<>();
//        //  查询媒体数据库
//        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.TITLE);
//        // 遍历媒体数据库
//        if ( cursor.moveToFirst() ){
//            while ( !cursor.isAfterLast() ) {
//                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
//                String tilte =cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
//                String artist=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
//                String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
//                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
//                Music m = new Music(id, tilte,artist, url, duration);
//                list.add(m);
//                cursor.moveToNext();
//            }
//            cursor.close();
//        }
//        return list;
//    }




    private void initview() {
        back  = findViewById(R.id.back);
        name = findViewById(R.id.name);
        details = findViewById(R.id.details);
        ablumlogo = findViewById(R.id.ablum_logo);
        playrandowm = findViewById(R.id.rbtn_playrandowm);
        playsequence = findViewById(R.id.rbtn_playsequence);
        previous = findViewById(R.id.ibtn_previous);
        previous.setImageResource(R.mipmap.balck);
        playorpause = findViewById(R.id.ibtn_playorpause);
        next = findViewById(R.id.ibtn_next);
        versonnext = findViewById(R.id.verson_next);
        versonprevious = findViewById(R.id.verson_previous);
        seekBar = findViewById(R.id.seekBar);
        musiccur = findViewById(R.id.music_cur);
        musiclength = findViewById(R.id.music_length);
        intent = getIntent();
    }
}
