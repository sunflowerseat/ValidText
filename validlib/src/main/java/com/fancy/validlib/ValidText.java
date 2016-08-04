package com.fancy.validlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by sunflowerseat on 2016/7/27.
 */
public class ValidText extends View {
    String titleText = "";
    String[] texts = null;
    int textSize = 60;
    int textLength = 4;
    boolean clickable = true;
    PreValid preValid = null;

    //文本的边界
    Rect bound = new Rect();

    Paint paint = new Paint();

    public ValidText(Context context) {
        this(context, null);
    }

    public ValidText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ValidText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ValidText, defStyleAttr, 0);
        int arrayLength = typedArray.getIndexCount();
        for (int i = 0; i < arrayLength; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.ValidText_android_textSize) {
                textSize = (int) typedArray.getDimension(attr, 40);

            } else if (attr == R.styleable.ValidText_android_clickable) {
                clickable = typedArray.getBoolean(attr, true);

            }
        }

        Log.d("ValidText", "textSize:" + textSize);
        titleText = getRandomText();
        if (clickable) {
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeText();
                }
            });
        }

        typedArray.recycle();
    }


    private String getRandomChar() {
        String[] chars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int random = (int) (Math.random() * (chars.length - 1));
        return chars[random];
    }

    private String getRandomText() {
        String s = "";
        texts = new String[textLength];
        for (int i = 0; i < textLength; i++) {
            String randomChar = getRandomChar();
            s += randomChar;
            texts[i] = randomChar;
        }
        return s;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            paint.setTextSize(textSize);
            paint.getTextBounds(titleText, 0, titleText.length(), bound);
            float textWidth = bound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            paint.setTextSize(textSize);
            paint.getTextBounds(titleText, 0, titleText.length(), bound);
            float textHeight = bound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(Color.parseColor("#00000000"));
        bound = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawRect(bound, paint);
        paint.setColor(Color.BLACK);

        paint.setTextSize(textSize);
        paint.getTextBounds("2", 0, 1, bound);
        RandomText random = new RandomText(getWidth(), getHeight(), texts.length);
        int textHeight = bound.height();
        if (preValid == null){
            preValid = new PreValid();
            preValid.attrs = new TextAttr[textLength];
        }
        boolean equalsPre = preValid.text.equals(titleText);
        if(!equalsPre) preValid.text = titleText;

        for (int i = 0; i < texts.length; i++) {

            if (!equalsPre) {
                preValid.attrs[i] = random.getAttr(textSize, textHeight, i + 1);
                preValid.text = titleText;
            }
            paint.setTextSize(preValid.attrs[i].getTextsize());
            drawText(canvas, texts[i], preValid.attrs[i].getX(), preValid.attrs[i].getY(), paint, preValid.attrs[i].getAngle());

        }

        paint.setColor(Color.BLACK);
        //绘制随机线条
        for (int i = 0; i < Config.LINE_NUM; i++) {
            canvas.drawLine(random.getRadomInt(0, getWidth()), random.getRadomInt(0, getHeight()), random.getRadomInt(0, getWidth()), random.getRadomInt(0, getHeight()), paint);
        }

        //绘制随机圆点
        for (int i = 0; i < Config.POINT_NUM; i++) {
            canvas.drawCircle(random.getRadomInt(0, getWidth()), random.getRadomInt(0, getHeight()), 1, paint);
        }
    }



    void drawText(Canvas canvas, String text, float x, float y, Paint paint, double angle) {
        if (angle != 0) {
            canvas.rotate((float) angle, x, y);
        }
        canvas.drawText(text, x, y, paint);
        if (angle != 0) {
            canvas.rotate((float) -angle, x, y);
        }
    }


    public String getText() {
        return titleText;
    }

    public void changeText() {
        titleText = getRandomText();
        invalidate();
    }


    public boolean equalText(String s) {
        return s.toUpperCase().equals(titleText);
    }

}
