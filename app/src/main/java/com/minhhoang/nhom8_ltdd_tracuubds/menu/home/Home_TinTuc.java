package com.minhhoang.nhom8_ltdd_tracuubds.menu.home;

import java.io.Serializable;

public class Home_TinTuc implements Serializable {
    private String tenTinTuc, noiDungTT, nguoiDang;
    private int anh;

    public Home_TinTuc(){

    }
    public Home_TinTuc(String tenTinTuc, String noiDungTT, String nguoiDang, int anh) {
        this.tenTinTuc = tenTinTuc;
        this.noiDungTT = noiDungTT;
        this.nguoiDang = nguoiDang;
        this.anh = anh;
    }

    public String getTenTinTuc() {
        return tenTinTuc;
    }

    public String getNguoiDang() {
        return nguoiDang;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public void setTenTinTuc(String tenTinTuc) {
        this.tenTinTuc = tenTinTuc;
    }

    public void setNguoiDang(String nguoiDang) {
        this.nguoiDang = nguoiDang;
    }

    public String getNoiDungTT() {
        return noiDungTT;
    }

    public void setNoiDungTT(String noiDungTT) {
        this.noiDungTT = noiDungTT;
    }
}
