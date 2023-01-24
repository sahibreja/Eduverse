package com.example.dsatutor.UI.start.Intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.dsatutor.Adapter.PreRequisiteAdapter;
import com.example.dsatutor.Model.PreRequisiteQuestion;
import com.example.dsatutor.R;
import com.example.dsatutor.UI.Fragment.PreReqQuesFragment;

import java.util.ArrayList;

public class IntroVideoActivity extends AppCompatActivity {
    private int currentApiVersion;
    private com.airbnb.lottie.LottieAnimationView click_here;
    private RelativeLayout overView;

    private SurfaceView surfaceView;

    private MediaPlayer mediaPlayer;
    boolean isPlay=true;

    int stage;
    SharedPreferences sharedpreferences;

    private RecyclerView recyclerView;

    private ArrayList<PreRequisiteQuestion> questions=new ArrayList<>();

    private PreRequisiteAdapter preRequisiteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setScreenType();
        setContentView(R.layout.activity_intro_video);
        //initialize
        init();

        stage=sharedpreferences.getInt("stage",0);
        surfaceView.setKeepScreenOn(true);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                mediaPlayer.setDisplay(surfaceHolder);
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });

        mediaPlayer.start();


    }

    private void AddQuestion()
    {
        questions.add(new PreRequisiteQuestion("1",
                "Variable",
                "Which of the following is true for variable names in C?",
                "Variable names cannot start with a digit",
                "Variable can be of any length",
                "They can contain alphanumeric characters as well as special characters",
                "Reserved Word can be used as variable name",
                "Variable names cannot start with a digit"
        ));
        questions.add(new PreRequisiteQuestion("2",
                "Array",
                "Array is ______ datatype in C Programming language.",
                "Derived Data type",
                "Primitive Data type",
                "Custom Data type",
                "None of these",
                "Derived Data type"));
    }
    private void init()
    {
        surfaceView=findViewById(R.id.video_view);
        mediaPlayer = MediaPlayer.create(this,R.raw.prerequisite_video);
        sharedpreferences = getSharedPreferences("PreReq", Context.MODE_PRIVATE);

        FragmentTransaction shopTrans = getSupportFragmentManager().beginTransaction();
        shopTrans.replace(R.id.frame_layout,new PreReqQuesFragment());
        shopTrans.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isPlay)
        {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
         this.recreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
        }
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