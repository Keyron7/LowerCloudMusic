package com.example.lowercloudmusic.Base;

public class Music {
    private int id;
    private String title;
    private String artist;
    private String url;
    private String imagurl;
    private int durationg;
    public Music(int id, String title, String artist, String url, int durationg){
        this.artist=artist;
        this.durationg=durationg;
        this.id=id;
        this.title=title;
        this.url=url;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getUrl() {
        return url;
    }

    public int getDurationg() {
        return durationg;
    }
}
