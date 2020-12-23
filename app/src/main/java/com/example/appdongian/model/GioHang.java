package com.example.appdongian.model;

public class GioHang {
    public int idsp;
    public String tensp;
    public long giasp;
    public String hinhanhsp;
    public int soluongsp;
    public GioHang(int idsp, String tensp, long giasp, String hinhanhsp, int soluongsp) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhanhsp = hinhanhsp;
        this.soluongsp = soluongsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public void setHinhanhsp(String hinhanhsp) {
        this.hinhanhsp = hinhanhsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    public int getIdsp() {
        return idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public String getHinhanhsp() {
        return hinhanhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }
}
