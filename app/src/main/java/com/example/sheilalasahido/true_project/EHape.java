package com.example.sheilalasahido.true_project;

import android.graphics.Bitmap;

public class EHape extends Sprite{

    public static Bitmap globalBitmap;

    public EHape(TampilanGame view, GameActivity game){
        super(view, game);
        if(globalBitmap == null){
            globalBitmap = Atur_Gambar.getScaledBitmapAlpha8(game, R.drawable.obstacle);
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
