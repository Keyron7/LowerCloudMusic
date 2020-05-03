package com.example.lowercloudmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lowercloudmusic.Base.Adapter.PlaylistAdapter;
import com.example.lowercloudmusic.Base.myablum;
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

public class LibraryFragment extends Fragment {
    private List<myablum> myablums;
    private RecyclerView recyclerView;
    private PlaylistAdapter adapter;
    private ImageButton imageButton;
    private TextView libalbum;
    private TextView libname;
    private String baseurl = "http://47.99.165.194/user/playlist";
    private String libraryurl = "?uid=69076641&limit=20";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library,null);
        myablums = new ArrayList<>();
        initview(view);
        initdata();
        recyclerView = view.findViewById(R.id.rec_library);
        adapter = new PlaylistAdapter(myablums);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MediaActivity.class);
//                intent.putExtra("ID",1);
                startActivity(intent);
            }
        });
        libalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MediaActivity.class);
//                intent.putExtra("ID",1);
                startActivity(intent);
            }
        });
        libname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MediaActivity.class);
//                intent.putExtra("ID",1);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initview(View view) {
        imageButton = view.findViewById(R.id.into1);
        libalbum = view.findViewById(R.id.home_ablum1);
        libname = view.findViewById(R.id.home_name1);

    }

    private void initdata() {
        String url = baseurl+libraryurl;
        Httputil.getInstance().execute(url, new Callback() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);
                parson(response);
            }

            @Override
            public void onFailed(Exception e) {
                Log.d(TAG, "onFailed: 777777777777");
            }
        });

    }

    private void parson(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response!=null){
                    try {
                        Log.d(TAG, "run: "+response);
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("code")==200){
                            JSONArray playlist = jsonObject.getJSONArray("playlist");
                            for (int i = 0;i<20;i++){
                                JSONObject jsonObject1 = playlist.getJSONObject(i);
                                myablum myablum = new myablum();
                                myablum.setId(jsonObject1.getInt("id"));
                                myablum.setName(jsonObject1.getString("name"));
                                myablum.setTrackCount(jsonObject1.getInt("trackCount"));
                                JSONObject jsonObject2 = jsonObject1.getJSONObject("creator");
                                myablum.setName(jsonObject2.getString("nickname"));
                                myablums.add(myablum);
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
