package com.example.lowercloudmusic.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lowercloudmusic.Base.Ablum;
import com.example.lowercloudmusic.Base.Adapter.AblumAdapter;
import com.example.lowercloudmusic.Base.Recommend;
import com.example.lowercloudmusic.Base.Adapter.TwoAblumAdapter;
import com.example.lowercloudmusic.MediaActivity;
import com.example.lowercloudmusic.R;
import com.example.lowercloudmusic.util.httphelper.Callback;
import com.example.lowercloudmusic.util.httphelper.Httputil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {
    private List<Ablum> ablumslist;
    private List<Recommend> recommendList;
    private RecyclerView recyclerViewimage;
    private RecyclerView recyclerViewrec;
    private AblumAdapter ablumAdapter;
    private TwoAblumAdapter twoAblumAdapter;
    private TextView homename;
    private TextView homealbum;
    private ImageView album;
    private ImageButton imageButton;
    private String base_url = "http://47.99.165.194";
    private String banner = "/album/newest";
    private String rec ="/top/playlist?cat=欧美&limit=20";
    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        ablumslist = new ArrayList<>();
        recommendList = new ArrayList<>();
        initView(view);
        initdata();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewrec.setLayoutManager(layoutManager);
        twoAblumAdapter = new TwoAblumAdapter(recommendList);
        recyclerViewrec.setAdapter(twoAblumAdapter);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);//设置为横向排列
        recyclerViewimage.setLayoutManager(layout);
        ablumAdapter = new AblumAdapter(ablumslist);
        recyclerViewimage.setAdapter(ablumAdapter);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MediaActivity.class);
//                intent.putExtra("ID",0);
                startActivity(intent);
            }
        });
        homealbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MediaActivity.class);
//                intent.putExtra("ID",0);
                startActivity(intent);
            }
        });
        homename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MediaActivity.class);
//                intent.putExtra("ID",0);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initView(View view) {
        recyclerViewrec = view.findViewById(R.id.two_album);
        recyclerViewimage = view.findViewById(R.id.ablum_rec);
        album = view.findViewById(R.id.ablum);
        imageButton = view.findViewById(R.id.into);
        homename =view.findViewById(R.id.home_name);
        homealbum = view.findViewById(R.id.home_ablum);

    }

    private void initdata() {
        String url = base_url+banner;
        Httputil.getInstance().execute(url, new Callback() {
            @Override
            public void onResponse(String response) {
                parsonbanner(response);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
        String url1 = base_url+rec;
        Httputil.getInstance().execute(url1, new Callback() {
            @Override
            public void onResponse(String response) {
                parsonrec(response);
            }

            @Override
            public void onFailed(Exception e) {
                Log.d(TAG, "onFailed: 777777777777");
            }
        });
    }

    private void parsonrec(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("code")==200){
                                JSONArray playlists=jsonObject.getJSONArray("playlists");
                                for (int i = 0;i<playlists.length();i++){
                                    JSONObject jsonObject1 = playlists.getJSONObject(i);
                                    Recommend recommend = new Recommend();
                                    recommend.setId(jsonObject1.getInt("id"));
                                    recommend.setImageurl(jsonObject1.getString("coverImgUrl"));
                                    recommend.setSongname(jsonObject1.getString("name"));
                                    JSONObject jsonObject2 = jsonObject1.getJSONObject("creator");
                                    recommend.setName(jsonObject2.getString("nickname"));
                                    recommendList.add(recommend);
                                }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void parsonbanner(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("code")==200){
                            JSONArray ablums = jsonObject.getJSONArray("albums");
                            for (int i = 0;i<5;i++){
                                JSONObject jsonObject1 = ablums.getJSONObject(i);
                                Ablum ablum = new Ablum();
                                ablum.setAblumid(jsonObject1.getInt("id"));
                                ablum.setImageUrl(jsonObject1.getString("picUrl"));
                                ablum.setImageid(i);
                                ablumslist.add(ablum);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
