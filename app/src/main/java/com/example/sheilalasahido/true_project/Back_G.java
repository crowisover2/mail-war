package com.example.sheilalasahido.true_project;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Back_G extends Sprite{
        public static Bitmap globalBitmap;

        public Back_G(TampilanGame tampilan, GameActivity game){
            super(tampilan, game);
            if(globalBitmap == null){
                globalBitmap = Atur_Gambar.getDownScaledBitmapAlpha8(game, R.drawable.bg_sky);
            }
            this.bitmap = globalBitmap;
        }

        @Override
        public void draw(Canvas canvas){
            double factor = (1.0*canvas.getHeight())/bitmap.getHeight();
            if(-x>bitmap.getWidth()){
                x+=bitmap.getWidth();
            }

            int endBitmap = Math.min(-x+(int)(canvas.getWidth()/factor), bitmap.getWidth());
            //Log.d("Back_G",String.valueOf(canvas.getWidth()));
            int endCanvas = (int)((endBitmap+x)*factor)+1;
            src.set(-x, 0, endBitmap, bitmap.getHeight());
            dst.set(0,0, endCanvas, canvas.getHeight());
            canvas.drawBitmap(this.bitmap, src, dst, null);

            if(endBitmap == bitmap.getWidth()){
                src.set(0, 0,(int) (canvas.getWidth() / factor), bitmap.getHeight());
                dst.set(endCanvas, 0, endCanvas + canvas.getWidth(), canvas.getHeight());
                canvas.drawBitmap(this.bitmap, src, dst, null);
            }
        }
}
