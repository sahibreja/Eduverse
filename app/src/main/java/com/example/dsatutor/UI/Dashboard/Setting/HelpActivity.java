package com.example.dsatutor.UI.Dashboard.Setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.dsatutor.Adapter.ChapterAdapter;
import com.example.dsatutor.Adapter.HelpAdapter;
import com.example.dsatutor.MainActivity;
import com.example.dsatutor.Model.ModelClass.HelpQuestion;
import com.example.dsatutor.Model.Sound;
import com.example.dsatutor.R;
import com.example.dsatutor.UI.Dashboard.Learning.L1Activity;
import com.example.dsatutor.databinding.ActivityHelpBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HelpActivity extends AppCompatActivity {
    private ActivityHelpBinding binding;
    private int currentApiVersion;
    private Sound sound;
    private ArrayList<HelpQuestion> helpQuestions;
    private HelpAdapter helpAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setScreenType();
        binding=ActivityHelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        clickOnButton();
        setView();
    }

    private void setView() {
        Collections.shuffle(helpQuestions);
        binding.popularTxt1.setText(helpQuestions.get(0).getQuestion());
        binding.popularTxt2.setText(helpQuestions.get(1).getQuestion());
        binding.popularTxt3.setText(helpQuestions.get(2).getQuestion());
        binding.popularTxt4.setText(helpQuestions.get(3).getQuestion());
        binding.popularTxt5.setText(helpQuestions.get(4).getQuestion());
        binding.popularTxt6.setText(helpQuestions.get(5).getQuestion());
    }

    private void init(){
        sound= new Sound(HelpActivity.this);
        initializeQuestionAndAnswer();
        setRecyclerView();
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setItemViewCacheSize(10);
        binding.recyclerView.setDrawingCacheEnabled(true);
        binding.recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        binding.recyclerView.setNestedScrollingEnabled(true);
        helpAdapter= new HelpAdapter(HelpActivity.this, helpQuestions);
        binding.recyclerView.setAdapter(helpAdapter);
        binding.recyclerView.scrollToPosition(0);
        helpAdapter.notifyDataSetChanged();
    }

    private void initializeQuestionAndAnswer() {
        helpQuestions= new ArrayList<>();
        helpQuestions.add(new HelpQuestion(1,"New to the game? Read this tips!",""));
        helpQuestions.add(new HelpQuestion(2,"How do I turned off the sound?",""));
        helpQuestions.add(new HelpQuestion(3,"Can I skip any level?",""));
        helpQuestions.add(new HelpQuestion(4,"Tips to pass difficult levels.",""));
        helpQuestions.add(new HelpQuestion(5,"How options are disappeared?",""));
        helpQuestions.add(new HelpQuestion(6,"How do I save mt progress?",""));
        helpQuestions.add(new HelpQuestion(7,"Where did my progress stored?",""));
        helpQuestions.add(new HelpQuestion(8,"How to get 3 stars?",""));
        helpQuestions.add(new HelpQuestion(9,"How we get power?",""));
        helpQuestions.add(new HelpQuestion(10,"How power works?",""));
        helpQuestions.add(new HelpQuestion(11,"How to get extra lives?",""));
        helpQuestions.add(new HelpQuestion(12,"Will I lose all my game progress , if there is more than one player on my device?",""));
        helpQuestions.add(new HelpQuestion(13,"If I reinstall or upgrade the game , will I lose my progress",""));
        helpQuestions.add(new HelpQuestion(14,"How can we fill progress bar?",""));
    }

    private void clickOnButton() {
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickSound();
                startActivity(new Intent(HelpActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finishAffinity();

            }
        });
    }

    private void heartTouchEffect(View view) {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        view.startAnimation(anim);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(HelpActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        finishAffinity();
    }

    private void setScreenType() {
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
    public void onWindowFocusChanged(boolean hasFocus) {
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