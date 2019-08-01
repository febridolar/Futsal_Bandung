package com.apps.futsal_bandung;

public class ItemRecycle {

    private int mImageResource;
    private String nama;
    private String alamat;

    public ItemRecycle(int imageResource, String text1, String text2) {
        mImageResource = imageResource;
        nama = text1;
        alamat = text2;
    }

    public int getmImageResource(){
        return mImageResource;
    }

    public String getText1(){
        return nama;
    }

    public String getText2(){
        return alamat;
    }

}
