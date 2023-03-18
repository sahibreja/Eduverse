package com.example.dsatutor.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.VideoView;

import com.example.dsatutor.MainActivity;
import com.example.dsatutor.Model.PrefManager;
import com.example.dsatutor.R;
import com.example.dsatutor.UI.start.Intro.IntroVideoActivity;
import com.example.dsatutor.UI.start.authentication.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {
    //private VideoView view;
    private int currentApiVersion;
    private FirebaseAuth auth;
    private PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        setScreenType();
        setContentView(R.layout.activity_splash_screen_1);

        init();
        VideoView view = (VideoView)findViewById(R.id.video_view);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.intro_video;
        view.setVideoURI(Uri.parse(path));
        view.start();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              FirebaseUser currentUser = auth.getCurrentUser();
              if(currentUser != null && !prefManager.isFirstTimeLaunch()){

                  startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                  overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                  finish();

              }else
              {
                  if(currentUser==null)
                  {
                      startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
                      overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                      finish();
                  }else
                  {
                      startActivity(new Intent(SplashScreenActivity.this,IntroVideoActivity.class));
                      overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                      finish();
                  }

              }


          }
      },5900);
    }
    private void init()
    {
        //view=findViewById(R.id.video);

        auth = FirebaseAuth.getInstance();
        prefManager= new PrefManager(SplashScreenActivity.this,"Game");


    }




    private void setScreenType()
    {
        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
        {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }

    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

}