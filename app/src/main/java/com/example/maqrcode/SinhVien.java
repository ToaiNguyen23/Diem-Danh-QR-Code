package com.example.maqrcode;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class SinhVien {
    public String hoten;
    public String mssv;
    public String buoi1;
    public String buoi2;
    public String buoi3;
    public String buoi4;
    public String buoi5;
    public String buoi6;

    public SinhVien(){

    }
    public SinhVien(String hoten,String mssv,String buoi1,String buoi2,String buoi3, String buoi4,String buoi5,String buoi6){
        this.hoten = hoten;
        this.mssv = mssv;
        this.buoi1=buoi1;
        this.buoi2 =buoi2;
        this.buoi3 =buoi3;
        this.buoi4=buoi4;
        this.buoi5=buoi5;
        this.buoi6 = buoi6;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getBuoi1() {
        return buoi1;
    }

    public void setBuoi1(String buoi1) {
        this.buoi1 = buoi1;
    }

    public String getBuoi2() {
        return buoi2;
    }

    public void setBuoi2(String buoi2) {
        this.buoi2 = buoi2;
    }

    public String getBuoi3() {
        return buoi3;
    }

    public void setBuoi3(String buoi3) {
        this.buoi3 = buoi3;
    }

    public String getBuoi4() {
        return buoi4;
    }

    public void setBuoi4(String buoi4) {
        this.buoi4 = buoi4;
    }

    public String getBuoi5() {
        return buoi5;
    }

    public void setBuoi5(String buoi5) {
        this.buoi5 = buoi5;
    }

    public String getBuoi6() {
        return buoi6;
    }

    public void setBuoi6(String buoi6) {
        this.buoi6 = buoi6;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("hoten", hoten);
        result.put("mssv", mssv);
        result.put("buoi1", buoi1);
        result.put("buoi2", buoi2);
        result.put("buoi3", buoi3);
        result.put("buoi4", buoi4);
        result.put("buoi5", buoi5);
        result.put("buoi6", buoi6);

        return result;
    }
}
