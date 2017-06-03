package com.example.bro.cardpackopeninggame;

import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class CardPack extends Entity {

    ////71.65%	22.84%	4.42%	1.10%
    private final int[] drawRate = {110, 422, 2284, 7165};
    private final Resources resource;

    public CardPack(Resources res) {
        super(255);
        this.resource = res;
    }

    private CardRarity getRandomCardRarity() {
        CardRarity _picked = null;
        double _sumRates = 0;
        for (int aDrawRate : drawRate)
            _sumRates += aDrawRate;
        int _pickedNumber = (int) (Math.random() * _sumRates + 1);
        Log.e(TAG, String.valueOf(_pickedNumber));

        _sumRates = 0;
        for (int i = 0; i < drawRate.length; i++) {
            _sumRates += drawRate[i];
            Log.e(TAG, String.valueOf(_sumRates));
            if (_pickedNumber <= _sumRates) {
                _picked = CardRarity.values()[i];
                Log.e(TAG, _picked.toString());
                break;
            }
        }
        return _picked;
    }

    public ArrayList<Card> drawCards() {
        ArrayList<Card> drawnCrads = new ArrayList<>();
        int drawCount = 5;
        for (int i = 0; i < drawCount; i++) {
            drawnCrads.add(
                    new Card(
                            getRandomCardRarity(),
                            resource
                    )
            );
        }
        return drawnCrads;
    }
}

















