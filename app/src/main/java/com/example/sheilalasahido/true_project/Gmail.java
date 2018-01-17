package com.example.sheilalasahido.true_project;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Gmail extends CharacterUtama {
    public static Bitmap globalBitmap;

    public Gmail(TampilanGame view, GameActivity game){
        super(view, game);
        if(globalBitmap == null){
            globalBitmap = Atur_Gambar.getScaledBitmapAlpha8(game, R.drawable.gmail1);
        }
        this.bitmap = globalBitmap;
        this.lebar = this.bitmap.getWidth();
        this.tinggi = this.bitmap.getHeight();
        this.y = game.getResources().getDisplayMetrics().heightPixels/4;
    }

    @Override
    public void onTap(){
        super.onTap();
    }

    @Override
    public void move(){
        super.move();
        /*if(row != 3){
            if(speedY > getTabSpeed() / 3 && speedY < getMaxSpeed() * 1/3){
                row = 0;
            }else if(speedY > 0){
                row = 1;
            }else{
                row = 2;
            }
        }*/
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
    }

    @Override
    public void dead() {
        //this.row = 3;
        //this.frameTime = 3;
        super.dead();
    }
}
