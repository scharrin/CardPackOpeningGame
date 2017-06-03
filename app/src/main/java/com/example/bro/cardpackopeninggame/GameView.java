package com.example.bro.cardpackopeninggame;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView
        implements SurfaceHolder.Callback {
    private GameThread gameThread = null;

    public GameView(Context context) {
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread = new GameThread(holder, getResources());
        gameThread.on();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        gameThread.onSizeChanged(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameThread.off();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            gameThread.flipCard(x, y);
        }
        return super.onTouchEvent(event);
    }
}
