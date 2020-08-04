package com.example.listmusic;

public class Music {
    private String tenbaihat;
    private String singer;
    private int hinh;
    private int file;


    public Music(String tenbaihat, String singer, int hinh, int file) {
        this.tenbaihat = tenbaihat;
        this.singer = singer;
        this.hinh = hinh;
        this.file = file;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
