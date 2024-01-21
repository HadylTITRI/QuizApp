package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String selectedTopicName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout programming = findViewById(R.id.programmingLayout);
        final LinearLayout maths = findViewById(R.id.mathsLayout);
        final LinearLayout ds = findViewById(R.id.dsLayout);
        final LinearLayout networking = findViewById(R.id.networkingLayout);

        final Button startBtn = findViewById(R.id.startQuizBtn);

        programming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "programming";
                programming.setBackgroundResource(R.drawable.round_back_white_stroke10);
                maths.setBackgroundResource(R.drawable.round_back_white10);
                ds.setBackgroundResource(R.drawable.round_back_white10);
                networking.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "maths";
                maths.setBackgroundResource(R.drawable.round_back_white_stroke10);
                programming.setBackgroundResource(R.drawable.round_back_white10);
                ds.setBackgroundResource(R.drawable.round_back_white10);
                networking.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "data";
                ds.setBackgroundResource(R.drawable.round_back_white_stroke10);
                maths.setBackgroundResource(R.drawable.round_back_white10);
                programming.setBackgroundResource(R.drawable.round_back_white10);
                networking.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        networking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "networking";
                networking.setBackgroundResource(R.drawable.round_back_white_stroke10);
                maths.setBackgroundResource(R.drawable.round_back_white10);
                ds.setBackgroundResource(R.drawable.round_back_white10);
                programming.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(selectedTopicName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please select a topic", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this,QuizActivity.class);
                    intent.putExtra("selectedTopic", selectedTopicName);
                    startActivity(intent);
                }
            }
        });
    }
}