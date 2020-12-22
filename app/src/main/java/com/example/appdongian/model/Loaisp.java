package com.example.appdongian.model;

public class Loaisp {
        public int id;
        public String Tenloaisp;
        public String Hinhanhsp;

    public void setId(int id) {
        this.id = id;
    }

    public void setTenloaisp(String tenloaisp) {
        Tenloaisp = tenloaisp;
    }

    public void setHinhanhsp(String hinhanhsp) {
        Hinhanhsp = hinhanhsp;
    }

    public int getId() {
        return id;
    }

    public String getTenloaisp() {
        return Tenloaisp;
    }

    public String getHinhanhsp() {
        return Hinhanhsp;
    }

    public  Loaisp(int id, String Tenloaisp, String Hinhanhsp){
            this.id=id;
            this.Tenloaisp=Tenloaisp;
            this.Hinhanhsp=Hinhanhsp;
        }
}

