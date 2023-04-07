package com.example.dsatutor.UI.start.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsatutor.Database.GameDatabase;
import com.example.dsatutor.Model.Sound;
import com.example.dsatutor.Model.ModelClass.Users;
import com.example.dsatutor.R;
import com.example.dsatutor.UI.start.Intro.IntroVideoActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText name,email;
    private TextInputEditText pass,confirmPass;
    private TextView alreadyAccount;
    private Button signup;

    private FirebaseAuth auth;

    private FirebaseDatabase database;

    private ProgressDialog progressDialog;
    private GameDatabase gameDatabase;
    private Sound sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setContentView(R.layout.activity_sign_up);
        init();
        ButtonClick();
    }
    private void heartTouchEffect(View view) {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        view.startAnimation(anim);
    }
    private void ButtonClick() {
        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartTouchEffect(v);
                sound.playClickOnButtonSound();
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finishAffinity();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartTouchEffect(v);
                sound.playClickOnButtonSound();
                String  userName = name.getText().toString();
                String  userEmail = email.getText().toString();
                String  userPass = pass.getText().toString();
                String  userConfirmPass = confirmPass.getText().toString();
                if(userName.isEmpty())
                {
                    name.setError("Please Enter Your Name");

                }else if(userEmail.isEmpty())
                {
                    email.setError("Please Enter Your Email");
                }
                else if(userPass.isEmpty())
                {
                    pass.setError("Please Enter Your Password");

                }else
                {
                    if(userPass.equals(userConfirmPass))
                    {
                        progressDialog.show();
                        auth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful())
                                {

                                            String uid=task.getResult().getUser().getUid().toString();
                                            Users users1= new Users(uid,userName,userEmail,userPass,1,5,System.currentTimeMillis(),0,0,0);
                                            database.getReference().child("Users").child(uid).setValue(users1);
                                            startActivity(new Intent(SignUpActivity.this, IntroVideoActivity.class));
                                            finishAffinity();

                                }else
                                {
                                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    //pass.setError("Password format"+"Aaa12#");
                                }
                            }
                        });
                    }else{

                        confirmPass.setError("Password Doesn't Match");
                    }
                }
            }
        });

    }
    private void init() {
        name =findViewById(R.id.userName);
        email =findViewById(R.id.email_txt);
        pass =findViewById(R.id.pass_txt);
        confirmPass =findViewById(R.id.confirm_pass_txt);
        alreadyAccount =findViewById(R.id.already_acct);
        signup =findViewById(R.id.signUp_Btn);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("we are creating your account");
        gameDatabase= Room.databaseBuilder(SignUpActivity.this,
                GameDatabase.class, "game_database").allowMainThreadQueries().build();

        sound= new Sound(SignUpActivity.this);
    }
}