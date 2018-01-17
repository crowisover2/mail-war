package com.example.sheilalasahido.true_project;

import android.graphics.Canvas;

public class Rintangan extends EHape{

    public Rintangan(TampilanGame view, GameActivity bermain){
        super(view, bermain);

        inisiasiPos();
    }

    public void inisiasiPos(){
        int tinggi = Fame.getResources().getDisplayMetrics().heightPixels;
        int jarak = tinggi/4 - Tampilan.getSpeedX();
        if(jarak<tinggi/5){
            jarak = tinggi/5;
        }
        int random = (int)(Math.random()*tinggi*2/5);
        int y2 = (tinggi/10) + random + jarak;

        init(Fame.getResources().getDisplayMetrics().widthPixels, y2);
    }
}
