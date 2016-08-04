package com.fancy.validlib;

import android.util.Log;

/**
 * Created by sunflowerseat on 2016/8/4.
 */
public class RandomText {
    private int width;
    private int height;
    private int spacing;
    private int dengfen;

    public RandomText(int width, int height,int dengfen) {
        this.width = width;
        this.height = height;
        spacing = width / dengfen;
        Log.d("RandomText", "spacing:" + spacing);
        this.dengfen = dengfen;
    }

    public TextAttr getAttr(int textSize, int textHeight, int position) {
        TextAttr ta = new TextAttr(textSize);
        Log.d("RandomText", "textHeight:" + textHeight);
        Log.d("RandomText", "width:" + width);
        int startX = (textHeight/2) + (spacing * (position - 1));
        int endX = spacing * position - (textHeight/2);
        Log.d("RandomText", "startX:" + startX);
        Log.d("RandomText", "endX:" + endX);

        if (position == dengfen) {
            startX = (textHeight/2) + (spacing * (position - 1));
            endX = spacing * position - (textHeight);
        }
        int x = getRadomInt(startX, endX);

        Log.d("RandomText", "x:" + x);
        ta.setX(x);
        ta.setY(getRadomInt(textHeight, height - textHeight));
        ta.setAngle(getRadomInt(-60, 60));
        ta.setScale(getRadomDouble(0.8, 1.2));

        return ta;
    }

    public int getRadomInt(int start, int end) {
        return (int) (start + Math.random() * (end - start));
    }


    public double getRadomDouble(double start, double end) {
        return start + Math.random() * (end - start);
    }
}
