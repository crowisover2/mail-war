package com.example.sheilalasahido.true_project;

import android.graphics.Bitmap;

public class Antena extends Sprite{

    public static Bitmap globalBitmap;

    public Antena(TampilanGame view, GameActivity game){
        super(view, game);
        if(globalBitmap == null){
            globalBitmap = Atur_Gambar.getScaledBitmapAlpha8(game, R.drawable.antenna1);
        }

        this.bitmap = globalBitmap;
        this.lebar = this.bitmap.getWidth();
        this.tinggi = this.bitmap.getHeight();
    }

    public void init(int x, int y){
        this.x = x;
        this.y = y;
    }
}
