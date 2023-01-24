package com.example.dsatutor.UI.start;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.dsatutor.MainActivity;
import com.example.dsatutor.R;

public class LandingPageActivity extends AppCompatActivity {
    private android.widget.Button playBtn;
    private android.widget.Button how_to_play_btn;
    private ImageView logo;
    private LinearLayout view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setContentView(R.layout.activity_landing_page);
        init();
        ButtonClick();
        viewAnim();

    }
    private void ButtonClick()
    {
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.BounceIn).duration(1000).playOn(playBtn);
                startActivity(new Intent(LandingPageActivity.this, MainActivity.class));
                finish();
            }
        });
    }
    private void viewAnim()
    {
        //play Btn

        YoYo.with(Techniques.Pulse).duration(2000).repeat(-1).playOn(playBtn);
        YoYo.with(Techniques.Pulse).duration(2000).delay(500).repeat(-1).playOn(how_to_play_btn);


//        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", 150f);
//        animation.setDuration(2000);
//        animation.setRepeatMode(ValueAnimator.REVERSE);
//        animation.setRepeatCount(Animation.INFINITE);
//        animation.start();
        //animation.reverse();

    }
    private void init()
    {
        playBtn=findViewById(R.id.playBtn);
        how_to_play_btn=findViewById(R.id.how_to_play_btn);
        logo=findViewById(R.id.logo);
        view=findViewById(R.id.view);
    }
}