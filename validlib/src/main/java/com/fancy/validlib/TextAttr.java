package com.fancy.validlib;

/**
 * Created by sunflowerseat on 2016/8/4.
 */
public class TextAttr {
    private int x;
    private int y;
    private int angle;
    private int textsize;
    private double scale;

    public TextAttr(int textsize) {
        this.textsize = textsize;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public int getTextsize() {
        return (int) (textsize * scale);
    }

    public void setTextsize(int textsize) {
        this.textsize = textsize;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }




}
