package com.example.dsatutor.UI.Dashboard.Learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.dsatutor.Adapter.ChapterAdapter;
import com.example.dsatutor.Adapter.LeaderBoardAdapter;
import com.example.dsatutor.MainActivity;
import com.example.dsatutor.Model.Learning.Chapter;
import com.example.dsatutor.Model.Learning.SubChapter;
import com.example.dsatutor.Model.Sound;
import com.example.dsatutor.R;
import com.example.dsatutor.databinding.ActivityL1Binding;

import java.util.ArrayList;

public class L1Activity extends AppCompatActivity {
    private int currentApiVersion;
   private ChapterAdapter chapterAdapter;
    private ArrayList<SubChapter> subChapters1,subChapters2;
    private ArrayList<Chapter> chapters;
    private ActivityL1Binding binding;
    private Sound sound;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setScreenType();
        binding=ActivityL1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sound= new Sound(L1Activity.this);
        addChapter();
        ButtonClick();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.chapterRecycler.setLayoutManager(layoutManager);
        binding.chapterRecycler.setHasFixedSize(true);
        binding.chapterRecycler.setItemViewCacheSize(10);
        binding.chapterRecycler.setDrawingCacheEnabled(true);
        binding.chapterRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        binding.chapterRecycler.setNestedScrollingEnabled(true);
        chapterAdapter= new ChapterAdapter(this,L1Activity.this,chapters,binding.subChapterRecycler);
        binding.chapterRecycler.setAdapter(chapterAdapter);
        binding.chapterRecycler.scrollToPosition(0);
        chapterAdapter.notifyDataSetChanged();
    }

    private void ButtonClick() {
        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickSound();
                startActivity(new Intent(L1Activity.this, MainActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finishAffinity();
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickSound();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        });
    }

    private void heartTouchEffect(View view) {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        view.startAnimation(anim);
    }
    private void addSubChapter() {
        subChapters1= new ArrayList<>();
        subChapters2= new ArrayList<>();
        subChapters1.add(new SubChapter(1,"","Matrix Equation","UHhvcj0doaA"));
        subChapters1.add(new SubChapter(2,"","Linear Equation","tHm3X_Ta_iE"));
        subChapters1.add(new SubChapter(3,"","Permutation and Combination","Tr-TVt5JAWY"));
        subChapters1.add(new SubChapter(4,"","Pigeonhole Principle","B2A2pGrDG8I"));
        subChapters1.add(new SubChapter(5,"","Basics of set Theory","B2A2pGrDG8I"));

        subChapters2.add(new SubChapter(1,"","Data Type","wnbzTjWr5gY"));
        subChapters2.add(new SubChapter(1,"","Variable","fO4FwJOShdc"));
        subChapters2.add(new SubChapter(1,"","If else","Led5aHdLoT4"));
        subChapters2.add(new SubChapter(1,"","Loop","qUPXsPtWGoY"));
        subChapters2.add(new SubChapter(1,"","Function","3lqgdqoY83o"));
        subChapters2.add(new SubChapter(1,"","Pointer","f2i0CnUOniA"));
        subChapters2.add(new SubChapter(1,"","Structure,Union","zmRxC7gYw-g"));

    }

    private void addChapter() {
        addSubChapter();
        chapters= new ArrayList<>();
        chapters.add(new Chapter(1,"Problem solving using C language",subChapters2));
        chapters.add(new Chapter(2,"Solving Mathematics Problem",subChapters1));

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