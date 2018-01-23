package com.example.lamit.karaoke;

/**
 * Created by LamIT on 22/01/2018.
 */

public class KaraItem {
    public String MaBaiHat;
    public String TenBaiHat;
    public String LoiBaiHat;

    public KaraItem(String maBaiHat, String tenBaiHat, String loiBaiHat) {
        MaBaiHat = maBaiHat;
        TenBaiHat = tenBaiHat;
        LoiBaiHat = loiBaiHat;
    }

    public String getMaBaiHat() {
        return MaBaiHat;
    }

    public void setMaBaiHat(String maBaiHat) {
        MaBaiHat = maBaiHat;
    }

    public String getTenBaiHat() {
        return TenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        TenBaiHat = tenBaiHat;
    }

    public String getLoiBaiHat() {
        return LoiBaiHat;
    }

    public void setLoiBaiHat(String loiBaiHat) {
        LoiBaiHat = loiBaiHat;
    }
}
