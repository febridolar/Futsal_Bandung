package com.apps.futsal_bandung;

public class Post {

    private String nama_tempat;
    private String alamat;
    private String foto_url;

    public Post(String nama_tempat, String alamat, String foto_url) {
        this.nama_tempat = nama_tempat;
        this.alamat = alamat;
        this.foto_url = foto_url;
    }

    public String getNama_tempat() {
        return nama_tempat;
    }

    public void setNama_tempat(String nama_tempat) {
        this.nama_tempat = nama_tempat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }
    public Post()
    {

    }
}
