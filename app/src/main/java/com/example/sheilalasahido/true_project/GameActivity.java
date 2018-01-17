package com.example.sheilalasahido.true_project;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

public class GameActivity extends Activity {

    TampilanGame layar;
    private static final long DOUBLE_BACK_TIME = 1000;
    private long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // Log.d("GameActivity","test");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        super.onCreate(savedInstanceState);
        layar = new TampilanGame(this);
        setContentView(layar);
    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - backPressed < DOUBLE_BACK_TIME){
            super.onBackPressed();
        }else{
            backPressed = System.currentTimeMillis();
            Toast.makeText(this, getResources().getString(R.string.on_back_press), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause(){
        layar.pause();
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        layar.resume();
    }
}
