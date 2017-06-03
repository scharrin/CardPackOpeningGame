package com.example.bro.cardpackopeninggame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GameView gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
