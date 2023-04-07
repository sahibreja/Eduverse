package com.example.dsatutor.UI.Dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.dsatutor.Model.Sound;
import com.example.dsatutor.R;
import com.example.dsatutor.UI.Dashboard.Learning.L1Activity;
import com.example.dsatutor.UI.Dashboard.Learning.L2Activity;
import com.example.dsatutor.databinding.ActivityLearningBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LearningActivity extends AppCompatActivity {
    private int currentApiVersion;
    private ImageView backBtn;
    private de.hdodenhof.circleimageview.CircleImageView l1,l2;
    ActivityLearningBinding binding;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private Sound sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setScreenType();
        binding=ActivityLearningBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        ButtonClick();
    }
    private void heartTouchEffect(View view) {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        view.startAnimation(anim);
    }
    private void ButtonClick() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickSound();
                finish();
            }
        });
        binding.learn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playClickSound();
                startActivity(new Intent(LearningActivity.this, L1Activity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
        binding.learn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playClickSound();
                startActivity(new Intent(LearningActivity.this, L2Activity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }
    private void init()
    {
        sound=new Sound(LearningActivity.this);
        backBtn=findViewById(R.id.back_btn);
        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        firebaseDatabase.getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name=snapshot.child("userName").getValue().toString();
                binding.userName.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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