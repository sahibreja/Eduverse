package com.example.dsatutor.Model.ViewSet;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StarsView extends View {
    private Paint starPaint;
    private Random random;
    private List<PointF> stars;
    private AnimatorSet animatorSet;

    public StarsView(Context context) {
        super(context);
        init();
    }

    public StarsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        starPaint = new Paint();
        starPaint.setColor(Color.WHITE);
        random = new Random();
        stars = new ArrayList<>();
        animatorSet = new AnimatorSet();

        ObjectAnimator translationX = ObjectAnimator.ofFloat(this, "translationX", -100f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", -100f);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(this, "rotation", 0f, 180f);
        animatorSet.playTogether(translationX, translationY, rotation);
        animatorSet.setDuration(15000);
        animatorSet.setInterpolator(new LinearInterpolator());
        rotation.setRepeatMode(ValueAnimator.RESTART);
        rotation.setRepeatCount(ValueAnimator.INFINITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //set randomly 50 stars appear on screen
        for (int i = 0; i < 50; i++) {
            float x = random.nextInt(getWidth());
            float y = random.nextInt(getHeight());
            stars.add(new PointF(x, y));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw to stars
        for (PointF point : stars) {
            canvas.drawCircle(point.x, point.y, 2, starPaint);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animatorSet.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animatorSet.cancel();
    }
}

