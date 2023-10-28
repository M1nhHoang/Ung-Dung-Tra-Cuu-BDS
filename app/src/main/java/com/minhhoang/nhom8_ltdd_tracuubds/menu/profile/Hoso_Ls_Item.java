package com.minhhoang.nhom8_ltdd_tracuubds.menu.profile;

public class Hoso_Ls_Item {
    private int imgResource;
    private String tieude;
    private String noidung;

    public Hoso_Ls_Item(int imgResource, String tieude, String noidung) {
        this.imgResource = imgResource;
        this.tieude = tieude;
        this.noidung = noidung;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
