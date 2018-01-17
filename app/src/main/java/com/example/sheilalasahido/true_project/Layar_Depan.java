package com.example.sheilalasahido.true_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.audiofx.LoudnessEnhancer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class Layar_Depan extends View{

    private static Bitmap bg_menu = null;
    private static Bitmap btnPlay = null;
    private static Bitmap btnCredit = null;
    private static Bitmap title = null;
    //public static float lebar;
    //public static float tinggi;
    //private static Bitmap btnPause = null;

    private final static float[] LETAK_PLAY = {449/1280.0f, 268/720f, 833/1280.0f, 452/720.0f};

    private final static float[] Letak_About = new float[4];
    private final static float[] Letak_Title = new float[4];
    //private final static float[] LETAK_PLAY = {(lebar/2)-(btnPlay.getWidth()/2), (tinggi/2)-(btnPlay.getHeight()/2), (lebar/2)+(btnPlay.getWidth()/2), (tinggi/2)(btnPlay.getHeight()/2)};


    private Rect destination_BG;
    private Rect scaling_BG;
    private Rect destination_btnPlay;
    private Rect scaling_btnPlay;
    private Rect destination_btnCredit;
    private Rect scaling_btnCredit;
    private Rect destination_title;
    private Rect scaling_title;

    private MainActivity main_activity;

    public Layar_Depan(MainActivity context){
        super(context);
        this.main_activity = context;
        //lebar = getWidth();
        //tinggi = getHeight();
        if(bg_menu==null){
            bg_menu = Atur_Gambar.getBitmapAlpha8(main_activity, R.drawable.bg_sky);
        }
        scaling_BG = new Rect(0, 0, bg_menu.getWidth(), bg_menu.getHeight());
        if(btnPlay == null){
            btnPlay = Atur_Gambar.getBitmapAlpha8(main_activity, R.drawable.playbut320);
        }
        scaling_btnPlay = new Rect(0, 0, btnPlay.getWidth(), btnPlay.getHeight());
        if(btnCredit == null){
            btnCredit = Atur_Gambar.getBitmapAlpha8(main_activity, R.drawable.credit320);
        }
        scaling_btnCredit = new Rect(0, 0, btnCredit.getWidth(), btnCredit.getHeight());
        if(title == null){
            title = Atur_Gambar.getBitmapAlpha8(main_activity, R.drawable.mailwar);
        }
        scaling_title = new Rect(0, 0, title.getWidth(), title.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(bg_menu, scaling_BG, destination_BG, null);
        canvas.drawBitmap(btnPlay, scaling_btnPlay, destination_btnPlay, null);
        canvas.drawBitmap(btnCredit, scaling_btnCredit, destination_btnCredit, null);
        canvas.drawBitmap(title, scaling_title, destination_title, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        destination_BG = new Rect(0, 0, getWidth(), getHeight());
        destination_btnPlay = new Rect((int)(getWidth()*LETAK_PLAY[0]),
                (int)(getHeight()*LETAK_PLAY[1]),
                (int)(getWidth()*LETAK_PLAY[2]),
                (int)(getHeight()*LETAK_PLAY[3]));

        Letak_About[0]=getWidth()-btnCredit.getWidth();
        Letak_About[1]=getHeight()-btnCredit.getHeight();
        Letak_About[2]=getWidth();
        Letak_About[3]=getHeight();

        Letak_Title[0]=getWidth()/2-title.getWidth()/2;
        Letak_Title[1]=getHeight()/2-title.getHeight()-btnPlay.getHeight()/2;
        Letak_Title[2]=Letak_Title[0]+title.getWidth();
        Letak_Title[3]=Letak_Title[1]+title.getHeight();

        destination_btnCredit = new Rect((int)Letak_About[0], (int)Letak_About[1], (int)Letak_About[2], (int)Letak_About[3]);
        destination_title = new Rect((int)Letak_Title[0], (int)Letak_Title[1], (int)Letak_Title[2], (int)Letak_Title[3]);
    }

    @Override
    public boolean performClick(){
        return  super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            if((e.getX() > LETAK_PLAY[0] * getWidth()) && (e.getX() < LETAK_PLAY[2] * getWidth()) && (e.getY() > LETAK_PLAY[1] * getHeight()) && (e.getY() < LETAK_PLAY[3] * getHeight()))
            {
                Intent eIntent = new Intent("com.example.sheilalasahido.true_project.GameActivity");
                eIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                main_activity.startActivity(eIntent);
            }
            if((e.getX() > Letak_About[0]) && (e.getX() < Letak_About[2]) && (e.getY() > Letak_About[1]) && (e.getY() < Letak_About[3]))
            {
                main_activity.startActivity(new Intent("com.example.sheilalasahido.true_project.About"));
            }
        }
        return true;
    }
}
