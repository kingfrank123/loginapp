package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class Question extends AppCompatActivity {

    private Button choiceA;
    private Button choiceB;
    private Button choiceC;
    private TextView answer;
    private Button ret;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ret = findViewById(R.id.Re);

        answer = findViewById(R.id.Answer);
        choiceA = findViewById(R.id.A);
        choiceB = findViewById(R.id.B);
        choiceC = findViewById(R.id.C);

        choiceA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(Question.this,"Choice A is now chosen", Toast.LENGTH_SHORT).show();
                answer.setText("Choice A");

            }
        });
        choiceB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(Question.this,"Choice B is now chosen", Toast.LENGTH_SHORT).show();
                answer.setText("Choice B");
            }
        });
        choiceC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(Question.this,"Choice C is now chosen", Toast.LENGTH_SHORT).show();
                answer.setText("Choice C");
            }
        });

        ret.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(Question.this, "returning to homepage", Toast.LENGTH_SHORT).show();
                // go to homepage
                Intent intent = new Intent(Question.this, Homepage.class);
                startActivity(intent);
            }
        });
    }

}