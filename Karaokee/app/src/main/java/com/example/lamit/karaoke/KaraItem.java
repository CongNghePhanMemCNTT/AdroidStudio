package com.example.lamit.karaoke;

import android.widget.ImageButton;

/**
 * Created by LamIT on 22/01/2018.
 */

public class KaraItem {
    public String MaBaiHat;
    public String TenBaiHat;
    public String LoiBaiHat;
<<<<<<< HEAD



    public KaraItem(String maBaiHat, String tenBaiHat, String loiBaiHat) {
=======
    public int Image;

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public KaraItem(String maBaiHat, String tenBaiHat, String loiBaiHat, int imageButton) {
>>>>>>> c7e91adf369875c79c69381a1c15952be7196da8
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
