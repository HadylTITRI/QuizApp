package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    private QuizDbHelper dbHelper;
    private TextView questions;
    private TextView question;

    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton nextBtn;
    private Timer quizTimer;

    private int TotalTimeMins = 1;
    private int seconds = 0;

    private List<QuestionsList> questionsLists;

    private String selectedOptionByUser = "";
    private int currentQuestionsPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        dbHelper = new QuizDbHelper(this);

        final String getSelectedTopicName = getIntent().getStringExtra("selectedTopic");

        final ImageView backBtn = findViewById(R.id.backbtn);
        final TextView timer = findViewById(R.id.timer);
        final TextView selectedTopicName = findViewById(R.id.topicName);

        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        nextBtn = findViewById(R.id.nextBtn);

        selectedTopicName.setText(getSelectedTopicName);

        assert getSelectedTopicName != null;
        //questionsLists = QustionsBank.getQuestions(getSelectedTopicName);

        dbHelper.addQuestion("Which programming language is known for its simplicity and readability, and uses indentation to define code blocks?", "Java",
                "C++", "Python", "JavaScript", "Python", "", "programming");
        dbHelper.addQuestion("In object-oriented programming, what is encapsulation?", "The process of converting code into machine language",
                "The bundling of data and methods that operate on that data" , "The technique of organizing code into reusable units" , "The act of testing code before deployment",
                "The bundling of data and methods that operate on that data", "", "programming");
        dbHelper.addQuestion("Which data structure follows the Last In, First Out (LIFO) principle?", "Queue" ,
                "Stack", "Linked List" , "Array",
                "Stack", "", "programming");
        dbHelper.addQuestion("Which of the following programming languages is commonly used for building Android applications?", "Swift",
                "Kotlin", "Java", "C++", "Java", "", "programming");


        dbHelper.addQuestion("What is the result of 2^3 x 3^2 ?", "12",
                "72", "216", "64", "216", "", "maths");
        dbHelper.addQuestion("What is the perimeter of a rectangle with length 8 units and width 5 units?", "10 units",
                "16 units" , "26 units" , "34 units", "26 units", "", "maths");
        dbHelper.addQuestion("What is the area of a triangle with a base of 10 units and a height of 8 units?", "20 square units" ,
                "40 square units", "60 square units" , "80 square units",
                "60 square units", "", "maths");
        dbHelper.addQuestion("If 2x+3=11, what is the value of x ",
                "2", "4", "5", "7", "4", "", "maths");


        dbHelper.addQuestion("What is the primary goal of exploratory data analysis (EDA) in data science?", " Building predictive models",
                "Summarizing and visualizing data", "Cleaning and preprocessing data", "Evaluating model performance", "Summarizing and visualizing data", "", "data");
        dbHelper.addQuestion("Which of the following algorithms is commonly used for clustering in unsupervised machine learning?", "Decision Trees",
                "K-Means" , " Support Vector Machines (SVM)" , " Random Forest",
                "K-Means", "", "data");
        dbHelper.addQuestion("In the context of machine learning, what does \"overfitting\" refer to?", "The model performs well on the training data but poorly on new, unseen data" ,
                "The model is too simple and cannot capture the underlying patterns in the data", "The model memorizes the training data instead of learning the underlying patterns" , "The model is unable to converge during the training process",
                "The model memorizes the training data instead of learning the underlying patterns", "", "data");
        dbHelper.addQuestion("What is the purpose of the term \"feature scaling\" in machine learning?", "Selecting the most important features for model training",
                "Transforming categorical features into numerical representations", "Scaling the target variable for regression models", " Ensuring that all input features have the same scale or distribution", " Ensuring that all input features have the same scale or distribution", "", "data");

        dbHelper.addQuestion("What does the acronym \"TCP\" stand for in the context of networking?", "Transfer Control Protocol",
                "Transmission Control Protocol", "Telecommunication Control Protocol", "Technical Control Protocol", "Transmission Control Protocol", "", "networking");
        dbHelper.addQuestion("In the OSI model, which layer is responsible for logical addressing and routing?", "Data Link Layer",
                "Physical Layer" , "Network Layer" , "Transport Layer",
                "Network Layer", "", "networking");
        dbHelper.addQuestion("What is the purpose of a subnet mask in IP networking?", "Identifying the network portion of an IP address" ,
                "Assigning a unique identifier to each device on the network", "Encrypting data during transmission" , "Determining the physical location of a device", "Identifying the network portion of an IP address", "", "networking");
        dbHelper.addQuestion("Which protocol is commonly used for secure communication over the Internet, providing encryption and authentication?", "HTTP",
                "FTP", "SSL/TLS", "UDP", "SSL/TLS", "", "networking");

        questionsLists = dbHelper.getQuestions(getSelectedTopicName);

        startTimer(timer);

        questions.setText((currentQuestionsPosition + 1) + "/" + questionsLists.size());

        if (!questionsLists.isEmpty()) {
            question.setText(questionsLists.get(0).getQuestion());
            option1.setText(questionsLists.get(0).getOption1());
            option2.setText(questionsLists.get(0).getOption2());
            option3.setText(questionsLists.get(0).getOption3());
            option4.setText(questionsLists.get(0).getOption4());
        }

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option1.getText().toString();

                    option1.setBackgroundResource(R.drawable.round_back_red10);
                    option1.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionsLists.get(currentQuestionsPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option2.getText().toString();

                    option2.setBackgroundResource(R.drawable.round_back_red10);
                    option2.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionsLists.get(currentQuestionsPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option3.getText().toString();

                    option3.setBackgroundResource(R.drawable.round_back_red10);
                    option3.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionsLists.get(currentQuestionsPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option4.getText().toString();

                    option4.setBackgroundResource(R.drawable.round_back_red10);
                    option4.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionsLists.get(currentQuestionsPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedOptionByUser.isEmpty()) {
                    Toast.makeText(QuizActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                } else {
                    changeNextQuestion();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    private void changeNextQuestion() {

        currentQuestionsPosition++;

        if ((currentQuestionsPosition + 1) == questionsLists.size()) {
            nextBtn.setText("Submit Quiz");
        }
        if (currentQuestionsPosition < questionsLists.size()) {
            selectedOptionByUser = "";

            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor("#000000"));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor("#000000"));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor("#000000"));

            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor("#000000"));

            questions.setText((currentQuestionsPosition + 1) + "/" + questionsLists.size());

            question.setText(questionsLists.get(currentQuestionsPosition).getQuestion());
            option1.setText(questionsLists.get(currentQuestionsPosition).getOption1());
            option2.setText(questionsLists.get(currentQuestionsPosition).getOption2());
            option3.setText(questionsLists.get(currentQuestionsPosition).getOption3());
            option4.setText(questionsLists.get(currentQuestionsPosition).getOption4());


        } else {
            Intent intent = new Intent(QuizActivity.this, QuizResults.class);
            intent.putExtra("correct", getCorrectAnswers());
            intent.putExtra("incorrect", getIncorrectAnswers());

            startActivity(intent);
            finish();
        }

    }

    private void startTimer(TextView timerTextView) {

        quizTimer = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (seconds == 0) {
                    TotalTimeMins--;
                    seconds = 59;
                } else if (seconds == 0 && TotalTimeMins == 0) {
                    quizTimer.purge();
                    quizTimer.cancel();

                    Toast.makeText(QuizActivity.this, "Time Over", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity.this, QuizResults.class);

                    intent.putExtra("correct", getCorrectAnswers());
                    intent.putExtra("incorrect", getIncorrectAnswers());

                    startActivity(intent);
                    finish();

                } else {
                    seconds--;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String finalMinutes = String.valueOf(TotalTimeMins);
                        String finalSeconds = String.valueOf(seconds);

                        if (finalMinutes.length() == 1) {
                            finalMinutes = "0"+finalMinutes;
                        }
                        if (finalSeconds.length() == 1) {
                            finalSeconds = "0"+finalSeconds;
                        }
                        timerTextView.setText(finalMinutes + ":" + finalSeconds);
                    }
                });
            }
        }, 1000, 1000);
    }

    private int getCorrectAnswers() {

        int correctAnswer = 0;
        for (int i = 0; i < questionsLists.size(); i++) {
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if (getUserSelectedAnswer.equals(getAnswer)) {
                correctAnswer++;
            }
        }
        return correctAnswer;
    }

    private int getIncorrectAnswers() {

        int incorrectAnswer = 0;
        for (int i = 0; i < questionsLists.size(); i++) {
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if (!getUserSelectedAnswer.equals(getAnswer)) {
                incorrectAnswer++;
            }
        }
        return incorrectAnswer;
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(QuizActivity.this, MainActivity.class));
        finish();
    }


    private void revealAnswer() {

        final String getAnswer = questionsLists.get(currentQuestionsPosition).getAnswer();

        if (option1.getText().toString().equals(getAnswer)) {
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);

        } else if (option2.getText().toString().equals(getAnswer)) {
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);

        } else if (option3.getText().toString().equals(getAnswer)) {
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);

        } else if (option4.getText().toString().equals(getAnswer)) {
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }
    }
}