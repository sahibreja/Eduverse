package com.example.dsatutor.UI.start.Intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.dsatutor.MainActivity;
import com.example.dsatutor.Model.ModelClass.PreRequisiteQuestion;
import com.example.dsatutor.Model.PrefManager;
import com.example.dsatutor.Model.Sound;
import com.example.dsatutor.R;

import java.util.ArrayList;
import java.util.Collections;

public class IntroVideoActivity extends AppCompatActivity {
    private int currentApiVersion;
    private FrameLayout frameLayout, popup_layout, positive_layout, negative_layout;
    private com.airbnb.lottie.LottieAnimationView click_here;
    private RelativeLayout overView;
    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    boolean isPlay = true;
    private ArrayList<PreRequisiteQuestion> questionArrayList;
    private int index = 0;
    int correctAnswers = 0;
    private PreRequisiteQuestion questions;
    private TextView question_text, option_1_txt, option_2_txt, option_3_txt, option_4_txt;
    private Button nextButton, previousButton, goBtn;
    private boolean isClicked = false, isFinish = false;
    private ProgressBar progressBar;
    private PrefManager prefManager;
    private Sound sound;
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
        AddQuestion();
        Collections.shuffle(questionArrayList);
        setNextQuestion();
        ButtonClick();

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


        //showCustomPopupMenu();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.pause();
                popup_layout.setVisibility(View.VISIBLE);
                sound.playPopupSound();
                YoYo.with(Techniques.FadeIn).duration(1000).playOn(goBtn);
                goBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sound.playClickSound();
                        YoYo.with(Techniques.FadeOut).duration(1000).playOn(goBtn);
                        popup_layout.setVisibility(View.GONE);
                        frameLayout.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.FadeIn).duration(1000).playOn(frameLayout);

                    }
                });
                //frameLayout.setVisibility(View.VISIBLE);

            }
        }, 19100);


    }
    private void init() {
        surfaceView = findViewById(R.id.video_view);
        mediaPlayer = MediaPlayer.create(this, R.raw.prerequisite_video);
        frameLayout = findViewById(R.id.frame_layout);
        prefManager= new PrefManager(IntroVideoActivity.this,"Game");
        question_text = findViewById(R.id.question_txt);
        option_1_txt = findViewById(R.id.option_1_txt);
        option_2_txt = findViewById(R.id.option_2_txt);
        option_3_txt = findViewById(R.id.option_3_txt);
        option_4_txt = findViewById(R.id.option_4_txt);

        nextButton = findViewById(R.id.nextBtn);
        previousButton = findViewById(R.id.pre_Btn);
        popup_layout = findViewById(R.id.popup_layout);
        goBtn = findViewById(R.id.goBtn);
        positive_layout = findViewById(R.id.positive_layout);
        negative_layout = findViewById(R.id.negative_layout);
        progressBar = findViewById(R.id.progressBar);

        sound = new Sound(IntroVideoActivity.this);

    }

    private void ButtonClick() {

        option_1_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked && !isFinish) {
                    checkAnswer(option_1_txt);
                    isClicked = true;
                    nextButton.setVisibility(View.VISIBLE);
                }
            }
        });
        option_2_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked && !isFinish) {
                    checkAnswer(option_2_txt);
                    isClicked = true;
                    nextButton.setVisibility(View.VISIBLE);
                }

            }
        });
        option_3_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked && !isFinish) {
                    checkAnswer(option_3_txt);
                    isClicked = true;
                    nextButton.setVisibility(View.VISIBLE);
                }
            }
        });
        option_4_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked && !isFinish) {
                    checkAnswer(option_4_txt);
                    isClicked = true;
                    nextButton.setVisibility(View.VISIBLE);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playClickOnButtonSound();
                reset();
                if (index < questionArrayList.size()) {
                    progressBar.setProgress(index + 1);
                    progressBar.setMax(questionArrayList.size());
                    index++;
                    setNextQuestion();
                    if (index == questionArrayList.size() - 1) {
                        index++;
                        nextButton.setText("Finish");
                    }

                } else {
                    //frameLayout.setVisibility(View.GONE);
                    isFinish = true;
                    progressBar.setProgress(index);
                    float per = (correctAnswers / (float) questionArrayList.size()) * 100;
                    if (per > 50) {
                        sound.playExcellentSound();
                        positive_layout.setVisibility(View.VISIBLE);

                        Button ps_ok_btn = findViewById(R.id.ps_ok_btn);
                        ps_ok_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sound.playClickSound();
                                prefManager.setFirstTimeLaunch(false);
                                startActivity(new Intent(IntroVideoActivity.this, MainActivity.class));
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                finish();
                                //loadingScreen();
                            }
                        });
                    } else {
                        sound.playBadResultSound();
                        negative_layout.setVisibility(View.VISIBLE);
                        Button ps_no_btn = findViewById(R.id.ps_no_btn);

                        ps_no_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sound.playClickSound();
                                prefManager.setFirstTimeLaunch(false);
                                startActivity(new Intent(IntroVideoActivity.this, MainActivity.class));
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                finish();
                                //loadingScreen();
                            }
                        });
                    }
                }
            }
        });

    }


    private void AddQuestion() {
        questionArrayList = new ArrayList<>();
        questionArrayList.add(new PreRequisiteQuestion("1",
                "Variable",
                "Which of the following is true for variable names in C?",
                "Variable names cannot start with a digit",
                "Variable can be of any length",
                "They can contain alphanumeric characters as well as special characters",
                "Reserved Word can be used as variable name",
                "Variable names cannot start with a digit"
        ));
        questionArrayList.add(new PreRequisiteQuestion("2",
                "Array",
                "Array is ______ datatype in C Programming language.",
                "Derived Data type",
                "Primitive Data type",
                "Custom Data type",
                "None of these",
                "Derived Data type"));

        questionArrayList.add(new PreRequisiteQuestion("3",
                "Variable", "Which is valid C expression?", "int my_num = 100,000;", "int my_num = 100000;", "int my num = 1000;",
                "int $my_num = 10000;", "int my_num = 100000;"));
        questionArrayList.add(new PreRequisiteQuestion("4", "", "#include <stdio.h>\n" +
                "    void main()\n" +
                "    {\n" +
                "        int x = 5;\n" +
                "        if (x < 1)\n" +
                "            printf(\"hello\");\n" +
                "        if (x == 5)\n" +
                "            printf(\"hi\");\n" +
                "        else\n" +
                "            printf(\"no\");\n" +
                "    }", "hi", "hello", "no", "error", "hi"));
        questionArrayList.add(new PreRequisiteQuestion("4", "Operator",
                "What will be the value of y if x = 8?\n" +
                        "y = (x  > 6 ? 4 : 6);",
                "Compilation Error",
                "0",
                "4",
                "6",
                "4"));
        questionArrayList.add(new PreRequisiteQuestion("5", "Set",
                "If x ∈ N and x is prime, then x is ________ set.",
                "Infinite set",
                "Finite set",
                "Empty set",
                "Not a set",
                "Infinite set"));
        questionArrayList.add(new PreRequisiteQuestion("6", "Matrix",
                "Total number of possible matrices of order 3 × 3 with each entry 2 or 0 is",
                "9", "27", "81", "512", "512"));

        questionArrayList.add(new PreRequisiteQuestion("7", "Linear equation",
                "Find the value of k, if x=1,y=2 is a solution of the equation 2x+3y=k",
                "5", "6", "7", "8", "8"));
        questionArrayList.add(new PreRequisiteQuestion("8", "Permutations and Combination",
                "How many possible two digit number can be formed by using the digit 3,5 and 7 ?",
                "10", "7", "9", "8", "9"));

        questionArrayList.add(new PreRequisiteQuestion("9", "Linear Equation",
                "If x and y are both positive solutions of equation ax+by+c=0, always lie in the",
                "First Quadrant",
                "Second Quadrant",
                "Third Quadrant",
                "Fourth Quadrant",
                "First Quadrant"));
        questionArrayList.add(new PreRequisiteQuestion("10", "Loop",
                "To perform a set of instructions repeatedly which of the following can be used?",
                "for", "while", "if-else-if", "both for and while", "both for and while"));


    }

    private void setNextQuestion() {
        if (index < questionArrayList.size()) {

            questions = questionArrayList.get(index);
            question_text.setText(questions.getQuestion());
            option_1_txt.setText(questions.getOption1());
            option_2_txt.setText(questions.getOption2());
            option_3_txt.setText(questions.getOption3());
            option_4_txt.setText(questions.getOption4());
        } else {

        }
    }

    private void checkAnswer(TextView selected) {
        String selectedAnswer = selected.getText().toString();
        if (selectedAnswer.equals(questions.getAnswer())) {
            sound.playCorrectAnswerSound();
            correctAnswers++;
            selected.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_selected_bg));
        } else {
            showAnswer();
            sound.playWrongAnswerSound();
            selected.setBackground(getResources().getDrawable(R.drawable.pre_req_wrong_opt));
        }
    }

    private void showAnswer() {
        if (questions.getAnswer().equals(option_1_txt.getText().toString()))
            option_1_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_selected_bg));
        else if (questions.getAnswer().equals(option_2_txt.getText().toString()))
            option_2_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_selected_bg));
        else if (questions.getAnswer().equals(option_3_txt.getText().toString()))
            option_3_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_selected_bg));
        else if (questions.getAnswer().equals(option_4_txt.getText().toString()))
            option_4_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_selected_bg));
    }

    void reset() {
        isClicked = false;
        nextButton.setVisibility(View.GONE);
        option_1_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_bg));
        option_2_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_bg));
        option_3_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_bg));
        option_4_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_bg));
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (isPlay) {
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
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
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
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
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
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
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