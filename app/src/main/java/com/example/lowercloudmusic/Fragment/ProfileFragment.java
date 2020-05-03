package com.example.lowercloudmusic.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lowercloudmusic.R;
import com.example.lowercloudmusic.util.httphelper.Callback;
import com.example.lowercloudmusic.util.httphelper.Httputil;
import com.example.lowercloudmusic.util.httphelper.picloadutil.MemoryCache;
import com.example.lowercloudmusic.util.httphelper.picloadutil.MyGlide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class ProfileFragment extends Fragment {
    private ImageView touxiang;
    private ImageView like;
    private ImageView right;
    private ImageView chat;
    private ImageView maplogo;
    private TextView code;
    private TextView name;
    private TextView fllowers;
    private TextView posts;
    private TextView fllowing;
    private String base_url = "http://47.99.165.194/user/detail";
    private String uid = "uid=69076641";
    private Handler mhandler = new Handler(Looper.getMainLooper());
    private HashMap<String,String> informaintion =new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,null);
        initview(view);
        return view;
    }


    private void initview(View view) {
        touxiang=view.findViewById(R.id.touxiang);
        like=view.findViewById(R.id.like);
        right=view.findViewById(R.id.right);
        chat=view.findViewById(R.id.chat);
        maplogo=view.findViewById(R.id.map_logo);
        code=view.findViewById(R.id.code);
        name=view.findViewById(R.id.name);
        fllowers=view.findViewById(R.id.fllowers);
        posts=view.findViewById(R.id.posts);
        fllowing=view.findViewById(R.id.fllowing);
        like.setImageResource(R.mipmap.like);
        right.setImageResource(R.mipmap.ok);
        chat.setImageResource(R.mipmap.message);
        maplogo.setImageResource(R.mipmap.pin);
        String url = base_url+"?"+uid;
        Httputil.getInstance().execute(url, new Callback() {
            @Override
            public void onResponse(String response) {
                pasrson(response);
            }

            @Override
            public void onFailed(Exception e) {
                Log.d(TAG, "onFailed: 777777777777");
            }
        });
    }

    private void pasrson(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("code")==200){
                            String createDays = jsonObject.getString("createDays");
                            JSONObject proflie = jsonObject.getJSONObject("profile");
                            String nickname = proflie.getString("nickname");
                            String city = proflie.getString("city");
                            String avatarUrl = proflie.getString("avatarUrl");
                            String followeds = proflie.getString("followeds");
                            String eventCount = proflie.getString("eventCount");
                            informaintion.put("fllowing",createDays);
                            informaintion.put("name",nickname);
                            informaintion.put("code",city);
                            informaintion.put("touxiangurl",avatarUrl);
                            informaintion.put("fllowers",followeds);
                            informaintion.put("posts",eventCount);
                            MyGlide.with(getContext()).load(avatarUrl).setRatio(1f).setCompress(10).into(touxiang);
                            code.setText(informaintion.get("code"));
                            name.setText(informaintion.get("name"));
                            fllowers.setText(informaintion.get("fllowers"));
                            posts.setText(informaintion.get("posts"));
                            fllowing.setText(informaintion.get("fllowing"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


    }
}

