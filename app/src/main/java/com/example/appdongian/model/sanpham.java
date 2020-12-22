package com.example.appdongian.model;

import java.io.Serializable;

public class sanpham implements Serializable {
    public void setId(int id) {
        this.id = id;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public void setGiasanpham(int giasanpham) {
        this.giasanpham = giasanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public void setMotasanpham(String motasanpham) {
        this.motasanpham = motasanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public int id;
    public String tensanpham;
    public int giasanpham;
    public String hinhanhsanpham;
    public String motasanpham;
    public int idsanpham;
    public int getId() {
        return id;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public int getGiasanpham() {
        return giasanpham;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public String getMotasanpham() {
        return motasanpham;
    }

    public int getIdsanpham() {
        return idsanpham;
    }


    public sanpham(int id, String tensanpham, int giasanpham, String hinhanhsanpham, String motasanpham, int idsanpham) {
        this.id = id;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.hinhanhsanpham = hinhanhsanpham;
        this.motasanpham = motasanpham;
        this.idsanpham = idsanpham;
    }
}
