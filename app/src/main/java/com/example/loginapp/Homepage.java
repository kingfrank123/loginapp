package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Homepage extends AppCompatActivity {
    private Button clogout;
    private Button cVideoView;
    private Button theQuestion;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        cVideoView = findViewById(R.id.VideoViewBtn);

        clogout = findViewById(R.id.googleSignOut);

        theQuestion = findViewById(R.id.Question);

        cVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Homepage.this, "Video Viewing", Toast.LENGTH_SHORT).show();
                // go to video viewing
                Intent intent = new Intent(Homepage.this, VideoViewAct.class);
                startActivity(intent);
            }
        });
        clogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(Homepage.this, "Logged out", Toast.LENGTH_SHORT).show();
                // go to homepage
                Intent intent = new Intent(Homepage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        theQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(Homepage.this, "showing the question", Toast.LENGTH_SHORT).show();
                // go to the Question
                Intent intent = new Intent(Homepage.this, Question.class);
                startActivity(intent);
            }
        });

    }
        /*clogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch (view.getId()) {
                    case R.id.googleSignOut:
                        signOut();
                        break;
                }
            }
        });

    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Homepage.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                        // go to homepage
                        Intent intent = new Intent(Homepage.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }*/

}