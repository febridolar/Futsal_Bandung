package com.apps.futsal_bandung;

public class PostDetail {

    private String alamat_detail;
    private String jam_operasi;
    private String fasilitas;
    private String telepon;
    private String tarif;
    private String img;

    public PostDetail(String alamat_detail, String jam_operasi, String fasilitas, String telepon, String tarif, String img) {
        this.alamat_detail = alamat_detail;
        this.jam_operasi = jam_operasi;
        this.fasilitas = fasilitas;
        this.telepon = telepon;
        this.tarif = tarif;
        this.img = img;
    }

    public String getAlamat_detail() {
        return alamat_detail;
    }

    public void setAlamat_detail(String alamat_detail) {
        this.alamat_detail = alamat_detail;
    }

    public String getJam_operasi() {
        return jam_operasi;
    }

    public void setJam_operasi(String jam_operasi) {
        this.jam_operasi = jam_operasi;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public PostDetail()
    {

    }
}
