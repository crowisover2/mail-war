package com.example.sheilalasahido.true_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TampilanGame extends SurfaceView implements Runnable{

    private static GameActivity game;
    private static Rintangan obstacle;
    private static TowerSignal DANGER;
    volatile static private boolean paused = true;
    private static boolean isGameOver = false;
    private static boolean isRintanganExist = false;

    private static Back_G back_g;
    private static CharacterUtama player;
    private static int score;

    public static final long UPDATE_INTERVAL = 50;

    private static Timer timer = new Timer();
    private static TimerTask timerTask;

    private static ButtonPause btnPAUSE;
    private static ButtonShoot btnSHOOT;

    private static List<Bullet> bullet = new ArrayList<>();

    private static SurfaceHolder holder;

    public TampilanGame(Context context){
        super(context);
        this.game = (GameActivity) context;
        setFocusable(true);

        holder = getHolder();
        player = new Gmail(this, game);

        back_g = new Back_G(this, game);
        bullet.add(new Bullet(this, game));

        btnPAUSE = new ButtonPause(this, game);
        btnSHOOT = new ButtonShoot(this, game);
    }

    public CharacterUtama getPlayer(){return player;}

    public void draw(){
        if(holder.getSurface().isValid()) {
            Canvas canvas = holder.lockCanvas();
            drawCanvas(canvas, true);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawCanvas(Canvas canvas, boolean drawPlayer){
        back_g.draw(canvas);
        obstacle.draw(canvas);
        DANGER.draw(canvas);

        if(drawPlayer){
            player.draw(canvas);

            for(Bullet p : bullet){
                p.OrdinatP = player.y;
                p.AbsisP = player.x;
                p.draw(canvas);
            }
        }

        btnPAUSE.draw(canvas);
        btnSHOOT.draw(canvas);

        Paint teks = new Paint();
        teks.setColor(Color.BLACK);
        teks.setTextSize((int) (this.getHeight() / 21.0f));
        String writ = String.format("%s", score);
        canvas.drawText(writ, 0, (int) (this.getHeight() / 21.0f), teks);
    }

    private void BulletCheckOutOfRange(){
        for (int i = 0; i < bullet.size(); i++) {
            if(this.bullet.get(i) != null){
                if (bullet.get(i).getX() + bullet.get(i).lebar >= this.getWidth()) {
                    this.bullet.remove(i);
                    i--;
                }
            }
        }
    }

    private void checkOutOfRange() {
        if (this.obstacle.isOutOfRange()) {
            this.obstacle.inisiasiPos();
        }
        if (this.DANGER.isOutOfRange()) {
            this.DANGER.inisiasiPos();
        }

        for (int i = 0; i < bullet.size(); i++) {
            if(this.bullet.get(i) != null){
                if (bullet.get(i).getX() + bullet.get(i).lebar >= DANGER.getX()) {
                    this.bullet.remove(i);
                    DANGER.health--;
                    i--;
                    if(DANGER.health<=0){
                        DANGER.health=5;
                        DANGER.inisiasiPos();
                    }
                }
            }
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        if (timerTask != null) {
            timerTask.cancel();
        }
    }

    public void pause(){
        stopTimer();
        paused = true;
    }

    private void setUpTimerTask() {
        stopTimer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                TampilanGame.this.run();
            }
        };
    }

    private void startTimer() {
        setUpTimerTask();
        timer = new Timer();
        timer.schedule(timerTask, UPDATE_INTERVAL, UPDATE_INTERVAL);
    }

    public void resume(){
        paused = false;
        startTimer();
    }

    public void run() {
        BulletCheckOutOfRange();
        if(!isRintanganExist){
            obstacle = new Rintangan(this, game);
            DANGER = new TowerSignal(this, game);
            isRintanganExist = true;
        }
        else checkCollision();

        checkOutOfRange();
        //createObstacle();
        move();
        draw();
    }

    private void checkCollision(){
        if(player.isColliding(obstacle)){
            gameOver();
        }
        else score++;

        if(player.isColliding(DANGER))gameOver();
    }

    private void move(){
        obstacle.setSpeedX(-getSpeedX());
        obstacle.move();

        DANGER.setSpeedX(-getSpeedX()/4);
        DANGER.move();
        /*for(PowerUp p : powerUps){
            p.move();
        }*/

        for(Bullet p : bullet){
            p.setSpeedX(getBulletX());
            p.move();
        }

        back_g.setSpeedX(-getSpeedX()/2);
        back_g.move();

        btnPAUSE.move();
        btnSHOOT.move();
        /*frontground.setSpeedX(-getSpeedX()*4/3);
        frontground.move();*/

        player.move();
    }

    public void gameOver(){
        isGameOver = true;
        pause();
    }

    public int getBulletX(){
        int speedDefault = this.getWidth() / 45;
        int speedIncrease = this.getWidth() / 600;
        int speed = speedDefault + speedIncrease;
        return speed + speed;
    }

    public int getSpeedX(){
        // 16 @ 720x1280 px
        int speedDefault = this.getWidth() / 45;

        // 1,2 every 4 points @ 720x1280 px
        int speedIncrease = this.getWidth() / 600;
        int speed = speedDefault + speedIncrease;
        return Math.min(speed, speedDefault+speedDefault);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
        // Just to remove the stupid warning
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(paused && !isGameOver){
                resume();
            }else if(btnPAUSE.isTouching((int) event.getX(), (int) event.getY()) && !this.paused){
                pause();
            }
            else if(btnSHOOT.isTouching((int) event.getX(), (int) event.getY()) /*&& !isBulletmove*/){
                bullet.add(new Bullet(this, game));
                //score++;
            }
            else this.player.onTap();
        }
        return true;
    }

}
