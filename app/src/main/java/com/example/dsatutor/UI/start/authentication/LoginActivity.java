package com.example.dsatutor.UI.start.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsatutor.MainActivity;
import com.example.dsatutor.R;
import com.example.dsatutor.UI.start.LandingPageActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private EditText email,pass;
    private TextView newUser,forgotPass;
    private Button loginBtn;

    private FirebaseAuth auth;

    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setContentView(R.layout.activity_login);

        init();
        buttonClick();
    }

    private void buttonClick() {

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString().trim();
                String userPassword = pass.getText().toString();
                if(userEmail.isEmpty())
                {
                    email.setError("Please Enter Your Email");
                }else if(userPassword.isEmpty())
                {
                    pass.setError("Please Enter Your Password");
                }else
                {
                    auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }else
                            {
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTxt=email.getText().toString().trim();
                if(emailTxt.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Enter Your registered email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.sendPasswordResetEmail(emailTxt).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void init()
    {
        email =findViewById(R.id.email_txt);
        pass =findViewById(R.id.pass_txt);
        newUser =findViewById(R.id.new_user);
        loginBtn =findViewById(R.id.login_btn);
        forgotPass =findViewById(R.id.forgot_pass);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            //reload();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
}