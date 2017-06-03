package com.example.bro.cardpackopeninggame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

enum CardRarity {LEGEND, EPIC, RARE, COMMON}

public class Card extends Entity {
    private final Resources resource;
    private int durability = 1;

    private double drawRate;
    private CardRarity cardRarity;
    private int cardGemColor;
    private boolean isCardFace = false;
    private Bitmap imgCardFace;
    private Bitmap imgCardBack;

    public Card(CardRarity cardRarity, Resources res) {
        super(255);
        this.resource = res;
        this.cardRarity = cardRarity;

        this.imgCardBack =
                BitmapFactory.decodeResource(res, R.drawable.cardback);
        this.setCardFace(false);

        switch (cardRarity) {
            case LEGEND:
                this.cardGemColor = Color.parseColor("#FF8C00");
                this.imgCardFace =
                        BitmapFactory.decodeResource(res, R.drawable.l1);
                break;

            case EPIC:
                this.cardGemColor = Color.parseColor("#800080");
                this.imgCardFace =
                        BitmapFactory.decodeResource(res, R.drawable.e1);
                break;

            case RARE:
                this.cardGemColor = Color.BLUE;
                this.imgCardFace =
                        BitmapFactory.decodeResource(res, R.drawable.r1);
                break;

            case COMMON:
                this.cardGemColor = Color.WHITE;
                this.imgCardFace =
                        BitmapFactory.decodeResource(res, R.drawable.c1);
                break;
        }
        this.setImage(imgCardBack);
    }

    public void flipCardToFace() {
        if (!isCardFace()) setImage(imgCardFace);
    }

    public boolean isCardFace() {
        return isCardFace;
    }

    public void setCardFace(boolean cardFace) {
        isCardFace = cardFace;
    }

    public double getDrawRate() {
        return drawRate;
    }

    public void setDrawRate(double drawRate) {
        this.drawRate = drawRate;
    }

}

















