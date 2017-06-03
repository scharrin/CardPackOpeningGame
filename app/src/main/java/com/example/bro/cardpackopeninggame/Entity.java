package com.example.bro.cardpackopeninggame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

class Entity {
    private Point position = new Point(0, 0);
    private Point speed = new Point(0, 0);
    private Point size = new Point(0, 0);
    private Bitmap image = null;

    private Rect rectSrc = new Rect();
    private Rect rectDst = new Rect();
    private Paint paint = new Paint();
    private boolean dead = false;
    private long setTime = 0;

    public Entity(int alpha) {
        paint.setAlpha(alpha);
    }

    public void draw(Canvas canvas) {
        // 그림 원본에서 전체 가져오기
        rectSrc.set(0, 0, image.getWidth(), image.getHeight());

        // 그림을 그릴 영역
        rectDst.set(position.x, position.y,
                position.x + size.x, position.y + size.y);

        if (canvas != null)
            canvas.drawBitmap(image, rectSrc, rectDst, paint);
    }

    public void move(Rect rectFrame) {
        position.x += speed.x;
        position.y += speed.y;

        if (position.x < 0 || position.x > rectFrame.right - size.x)
            speed.x *= -1;
        if (position.y < 0 || position.y > rectFrame.bottom - size.y)
            speed.y *= -1;
    }

    public void setImage(Resources res, int bg) {
        Bitmap bitmap = BitmapFactory.
                decodeResource(res, bg);
        setImage(bitmap);
    }

    public Rect getRect() {
        rectDst.set(position.x, position.y,
                position.x + size.x, position.y + size.y);

        return rectDst;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getSize() {
        return size;
    }

    public void setSize(Point size) {
        this.size = size;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setPosition(int x, int y) {
        this.position.set(x, y);
    }

    public void setSize(int width, int height) {
        this.size.set(width, height);
    }

}













