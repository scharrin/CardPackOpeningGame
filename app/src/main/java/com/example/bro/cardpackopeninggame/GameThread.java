package com.example.bro.cardpackopeninggame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.util.ArrayList;

import static android.graphics.BitmapFactory.decodeResource;
import static android.util.Log.e;

public class GameThread extends Thread {
    private final Resources resource;
    private boolean haveToStop = false;
    private SurfaceHolder surfaceHolder = null;

    private Entity cardPack = null;
    private Entity card = null;
    private Entity bg = null;
    private Entity btnNextPack;
    private GameTable gameTable = null;

    private Rect rectFrame = new Rect();
    private double widthCard;
    private double heightCard;
    private double cardPackWidth;
    private double cardPackHeight;
    private boolean isPackTouched = false;
    private ArrayList<Card> _drawnCards;
    private boolean isAllCardsShowFaces = false;

    public GameThread(SurfaceHolder holder, Resources res) {
        this.surfaceHolder = holder;
        this.resource = res;
        int frameWidth = holder.getSurfaceFrame().width();
        int frameHeight = holder.getSurfaceFrame().height();

        cardPack = new CardPack(res);
        cardPack.setImage(
                removeColor(decodeResource(
                        res,
                        R.drawable.cardpack
                ), Color.WHITE));

        bg = new Entity(255);
        bg.setImage(res, R.drawable.bg2);

        btnNextPack = new Entity(255);
        btnNextPack.setSize(frameWidth / 4, frameWidth / 8);
        btnNextPack.setPosition(
                ( frameWidth - btnNextPack.getSize().x ) / 2,
                frameHeight * 3 / 4
        );
        btnNextPack.setImage(
                decodeResource(
                        res,
                        R.drawable.btn_done
                )
        );
    }

    private Bitmap removeColor(Bitmap bitmap, int color) {
        int size = bitmap.getWidth() * bitmap.getHeight();
        int[] array = new int[size];
        bitmap.getPixels(array, 0, bitmap.getWidth(),
                0, 0, bitmap.getWidth(), bitmap.getHeight());

        for (int i = 0; i < array.length; i++) {
            if (array[i] == color)
                array[i] = Color.TRANSPARENT;
        }

        return Bitmap.createBitmap(array, 0, bitmap.getWidth(),
                bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
    }

    public void onSizeChanged(int width, int height) {
        rectFrame.set(0, 0, width, height);

        cardPackWidth = width / 2;
        cardPackHeight = cardPackWidth * 1.3;
        cardPack.setSize((int) cardPackWidth, (int) cardPackHeight);
        cardPack.setPosition(
                width / 2 - cardPack.getSize().x / 2,
                height / 2 - cardPack.getSize().y / 2
        );

        bg.setPosition(0, 0);
        bg.setSize(width, height);
    }

    public void on() {
        this.start();
    }

    public void off() {
        e("eee", "gameThread is off");
        isPackTouched = false;
        this.haveToStop = true;
    }

    @Override
    public void run() {
        super.run();

        while (!haveToStop) {
            // 화면 잠그기
            Canvas canvas = surfaceHolder.lockCanvas();

            // 화면 그리기
            draw(canvas);

            // 화면 풀기
            if (canvas != null)
                surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    // 화면 그리기
    private void draw(Canvas canvas) {
        bg.draw(canvas);

        if (!isPackTouched) cardPack.draw(canvas);

        if (isPackTouched) {
            for (Card _card : _drawnCards) {
                _card.draw(canvas);
            }
        }
    }

    public void flipCard(int x, int y) {
        // 만약에 카드팩을 터치했으면
        if (cardPack.getRect().contains(x, y)
                && !isPackTouched) {
            gameTable = new GameTable(resource, rectFrame);
            _drawnCards = gameTable.setLevel1();
            isPackTouched = true;
        } else if (isPackTouched) {
            for (Card _card : _drawnCards) {
                if (_card.getRect().contains(x, y)
                        && !_card.isCardFace()) {
                    _card.flipCardToFace();
                    _card.setCardFace(true);
                }
            }
        }
    }
}











