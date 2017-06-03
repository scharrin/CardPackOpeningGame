package com.example.bro.cardpackopeninggame;

import android.content.res.Resources;
import android.graphics.Rect;

import java.util.ArrayList;

class GameTable {
    public static final int SIZE_Y = 5;
    public static final int SIZE_X = 8;
    private final Resources resource;
    private final Rect rectFrame;

    private Card[][] array;

    public GameTable(Resources res, Rect rectFrame) {
        this.resource = res;
        this.rectFrame = rectFrame;
    }

    public ArrayList<Card> setLevel1() {

        array = new Card[SIZE_Y][SIZE_X];
        //automatically initialize with 0x0 array

        CardPack cardPack = new CardPack(resource);
        ArrayList<Card> _drawnCards = cardPack.drawCards();

        addCard(1, 0, _drawnCards.get(0));
        addCard(2, 1, _drawnCards.get(1));
        addCard(3, 2, _drawnCards.get(2));
        addCard(3, 0, _drawnCards.get(3));
        addCard(1, 2, _drawnCards.get(4));
        return _drawnCards;
    }

    private void addCard(int indexY, int indexX, Card _cardToPlace) {

        int widthFrame = rectFrame.width();
        int heightFrame = rectFrame.height();

        int widthCard = widthFrame / 3;   //// x
        int heightCard = (int) (widthCard * 1.2);
        _cardToPlace.setSize(widthCard, heightCard);

        int positionX = indexX * widthCard;
        int positionY = indexY * heightCard - heightCard / 2;
        _cardToPlace.setPosition(positionX, positionY);

        array[indexY][indexX] = _cardToPlace;

    }

}










