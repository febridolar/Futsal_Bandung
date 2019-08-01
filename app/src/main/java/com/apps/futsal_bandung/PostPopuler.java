package com.apps.futsal_bandung;

public class PostPopuler {
    private String nama;
    private String alamat;
    private String img;
    private Double rating;

    public PostPopuler(String nama, String alamat, String img, Double rating) {
        this.nama = nama;
        this.alamat = alamat;
        this.img = img;
        this.rating = rating;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public PostPopuler()
    {

    }
}
