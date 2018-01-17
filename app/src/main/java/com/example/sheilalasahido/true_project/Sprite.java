package com.example.sheilalasahido.true_project;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

 public abstract class Sprite {
    protected Bitmap bitmap;
    protected int tinggi, lebar;
    protected int x,y;
    protected float speedX, speedY;
    protected Rect src, dst;
    protected byte col, row;
    protected byte colNr=1;
    protected short frametime, frametimecounter;
    protected GameActivity Fame;
    protected TampilanGame Tampilan;

    public Sprite (TampilanGame tampilan, GameActivity game){
        Tampilan = tampilan;
        Fame = game;
        frametime = 1;
        src = new Rect();
        dst = new Rect();
    }

    public void draw(Canvas canvas){
        src.set(col*lebar, row*tinggi, (col+1)*lebar, (row+1)*tinggi);
        dst.set(x,y,x+lebar,y+tinggi);
        canvas.drawBitmap(bitmap, src, dst, null);
    }

    public void move(){
        x+=speedX; y+=speedY;
    }

    protected void changeToNextFrame(){
        this.frametimecounter++;
        if(this.frametimecounter>=this.frametime){
            this.col = (byte)((this.col+1)%this.colNr);
            this.frametimecounter=0;
        }
    }

    public boolean isOutOfRange(){return this.x+lebar<0;}

     public boolean isColliding(Sprite sprite){
         if(this.x < sprite.x + sprite.lebar && this.x + this.lebar > sprite.x && this.y < sprite.y + sprite.tinggi && this.y + this.tinggi > sprite.y){
             return true;
         }
         return false;
     }

    public boolean isCollidingRadius(Sprite sprite, float factor){
        int m1x = this.x+(this.lebar>>1);
        int m1y = this.y+(this.tinggi>>1);
        int m2x = sprite.x+(this.lebar>>1);
        int m2y= sprite.y+(this.tinggi>>1);
        int dx = m1x-m2x;
        int dy = m1y-m2y;
        int d = (int)Math.sqrt(dy*dy+dx*dx);

        if(d<(this.lebar+sprite.lebar)*factor||d<(this.tinggi+sprite.tinggi)*factor){return true;}
        else return false;
    }

    public boolean isTouching(int x, int y){return(x>this.x&&x<this.x+lebar&&y>this.y&&y<this.y+tinggi);}

    public void onCollision(){}

    public boolean isTouchingEdge(){return isTouchingGround()||isTouchingSky();}

    public boolean isTouchingSky(){return this.y<0;}

    public boolean isPassed(){return this.x + this.lebar < Tampilan.getPlayer().getX();}

    public boolean isTouchingGround(){
        return this.y + this.tinggi>this.Tampilan.getHeight()*(1f*/*45*/35)/720;
    }

    public int getX(){return this.x;}
    public void setX(int x){this.x = x;}

    public int getY(){return y;}
    public void setY(int y){this.y = y;}

    public float getSpeedX(){return  this.speedX;}
    public float getSpeedY(){return  speedY;}

    public void setSpeedX(float speedX){this.speedX = speedX;}
    public void setSpeedY(float speedY){this.speedY = speedY;}

    public int getLebar(){return lebar;}
    public int getTinggi(){return tinggi;}

    private int getCollisionTolerance(){return Fame.getResources().getDisplayMetrics().heightPixels/50;}
}
