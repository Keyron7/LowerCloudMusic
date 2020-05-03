package com.example.lowercloudmusic.Base;

import android.media.Image;

public class Recommend {
    private String songname;
    private String name;
    private String Imageurl;
    private int id;

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return Imageurl;
    }

    public void setImageurl(String imageurl) {
        Imageurl = imageurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
