package com.example.lowercloudmusic.util.httphelper;

public interface Callback {
    void onResponse(String response);

    void onFailed(Exception e);
}
