package com.example.dsatutor.UI.Dashboard.Learning;

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
import com.example.dsatutor.MainActivity;
import com.example.dsatutor.Model.Learning.Chapter;
import com.example.dsatutor.Model.Learning.SubChapter;
import com.example.dsatutor.Model.Sound;
import com.example.dsatutor.R;
import com.example.dsatutor.databinding.ActivityL1Binding;
import com.example.dsatutor.databinding.ActivityL2Binding;

import java.util.ArrayList;

public class L2Activity extends AppCompatActivity {
    private int currentApiVersion;
    private ChapterAdapter chapterAdapter;
    private ArrayList<SubChapter> subChapters1,subChapters2,subChapter3;
    private ArrayList<Chapter> chapters;
    private ActivityL2Binding binding;
    private Sound sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setScreenType();
        binding=ActivityL2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sound= new Sound(L2Activity.this);
        addChapter();
        ButtonClick();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.chapterRecycler.setLayoutManager(layoutManager);
        binding.chapterRecycler.setHasFixedSize(true);
        binding.chapterRecycler.setItemViewCacheSize(10);
        binding.chapterRecycler.setDrawingCacheEnabled(true);
        binding.chapterRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        binding.chapterRecycler.setNestedScrollingEnabled(true);
        chapterAdapter= new ChapterAdapter(this,L2Activity.this,chapters,binding.subChapterRecycler);
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
                startActivity(new Intent(L2Activity.this, MainActivity.class));
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
        subChapters1.add(new SubChapter(1,"Level 1","Definition","oKbc2YSdpdg"));
        subChapters1.add(new SubChapter(2,"Level 2","Classification of Data Structure","CfCSQqmk"));
        subChapters1.add(new SubChapter(3,"Level 3","Advantages of Data Structure","MHFan0xcWek"));
        subChapters1.add(new SubChapter(4,"Level 4","Basic Terminology","Mywwtl5B_Kw"));
        subChapters1.add(new SubChapter(5,"Level 5","Ds Algorithm","cOSTc6qBRQw"));
        subChapters1.add(new SubChapter(6,"Level 6","Characteristics of Algorithm","QGMM0tTKHr0"));
        subChapters1.add(new SubChapter(7,"Level 7","Data flow of an Algorithm","rJYgTJaXZU0"));
        subChapters1.add(new SubChapter(8,"Level 8","Need of Algorithm","80gm_EpVs6k"));
        subChapters1.add(new SubChapter(9,"Level 9","Declaration of Algorithm","GnUPZwa0Tlk"));

        subChapters2.add(new SubChapter(1,"Level 10","Definition","55l-aZ7_F24"));
        subChapters2.add(new SubChapter(2,"Level 11","Properties of array","S-U1pSrF2i8"));
        subChapters2.add(new SubChapter(3,"Level 12","Representation of array","hCjWMkWaYXA"));
        subChapters2.add(new SubChapter(4,"Level 13","Need of array","mrW7qrdroRc"));
        subChapters2.add(new SubChapter(5,"Level 14","Memory allocation of an array","udfbq4M2Kfc"));
        subChapters2.add(new SubChapter(6,"Level 15","Declaration of array","Bqud0_ozgcc"));
        subChapters2.add(new SubChapter(7,"Level 16","Initializing of array","GtcRReso9Xk"));
        subChapters2.add(new SubChapter(8,"Level 17","Accessing of array element","g0ClJ28-8LE"));
        subChapters2.add(new SubChapter(9,"Level 18","Implementation of array in memory","ZxqOvg05O6w"));
        subChapters2.add(new SubChapter(10,"Level 19","Traversal of array element","cjTNJhZ72Js"));
        subChapters2.add(new SubChapter(11,"Level 20","Insertion of array element","Uoomh63p_lU"));
        subChapters2.add(new SubChapter(12,"Level 21","Deletion of array element","CMbpZK_xqoc"));
        subChapters2.add(new SubChapter(13,"Level 22","Search of array element","MlehMuJgdM8"));
        subChapters2.add(new SubChapter(14,"Level 23","Update of array element","GDHn82NETes"));
        subChapters2.add(new SubChapter(15,"Level 24","Merging two arrays","65i1p6C8ybU"));
        subChapters2.add(new SubChapter(16,"Level 25","Declaration of 2D array","KDQXUysHLL8"));
        subChapters2.add(new SubChapter(17,"Level 26","Initializing of 2D array","J1aQ9JN4vZY"));
        subChapters2.add(new SubChapter(18,"Level 27","Accessing of 2D array element","_iZ3c7V-thw"));
        subChapters2.add(new SubChapter(19,"Level 28","Implementation of 2D array element","KDQXUysHLL8"));
        subChapters2.add(new SubChapter(20,"Level 29","Pointers and One-dimensional array","YVFmfczXfo8"));
        subChapters2.add(new SubChapter(21,"Level 30","Pointers and 2D array ","tw-qWGG8y5g"));
        subChapters2.add(new SubChapter(22,"Level 31","Pointers and Multi-dimensional array ","_j5lhHWkbnQ"));
        subChapters2.add(new SubChapter(23,"Level 32","Sparse Matrix and their Representation","mbfUA6mazeg"));


    }

    private void addChapter() {
        addSubChapter();
        chapters= new ArrayList<>();
        chapters.add(new Chapter(1,"Introduction to data structure",subChapters1));
        chapters.add(new Chapter(2,"Array",subChapters2));

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