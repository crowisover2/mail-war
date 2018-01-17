package com.example.sheilalasahido.true_project;

import android.graphics.Canvas;

public class Bullet extends Sprite{

    private SignalProjectile peluru;

    public static int AbsisP = 0, OrdinatP = -100;

    public Bullet(TampilanGame view, GameActivity game){
        super(view, game);
        peluru = new SignalProjectile(view, game);
        peluru.init(AbsisP, OrdinatP);
        this.lebar = peluru.lebar;
        this.tinggi = peluru.tinggi;
    }

    @Override
    public void draw(Canvas canvas){
        peluru.draw(canvas);
    }

    @Override
    public void move(){
        peluru.move();
    }

    @Override
    public void setSpeedX(float speedX){peluru.setSpeedX(speedX);}

    @Override
    public int getX(){return peluru.getX();}

    @Override
    public int getY(){return peluru.getY();}
}
