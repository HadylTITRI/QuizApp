package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        //1st topic questions
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

        dbHelper.addQuestion("What does HTML stand for?", "Hyper Text Markup Language", "High Tech Multi Language", "Hyperlink and Text Markup Language", "Home Tool Markup Language", "Hyper Text Markup Language", "", "programming");

        dbHelper.addQuestion("What is the output of the following code snippet in Python?\nprint(2+3*4)", "20", "14", "15", "24", "14", "", "programming");

        dbHelper.addQuestion("Which of the following is a valid JavaScript variable name?", "my-variable", "2myVariable", "_myVariable", "my variable", "_myVariable", "", "programming");

        dbHelper.addQuestion("What does CSS stand for?", "Computer Style Sheets", "Creative Style Sheets", "Cascading Style Sheets", "Colorful Style Sheets", "Cascading Style Sheets", "", "programming");

        dbHelper.addQuestion("Which of the following is not a programming language?", "Python", "HTML", "Java", "C++", "HTML", "", "programming");

        dbHelper.addQuestion("What is the result of the following expression in Java?\nint x = 5;\nint y = x++;", "x = 5, y = 6", "x = 6, y = 5", "x = 5, y = 5", "x = 6, y = 6", "x = 6, y = 5", "", "programming");


        //2nd topic questions
        dbHelper.addQuestion("What is the result of 2^3 x 3^2 ?", "12",
                "72", "216", "64", "216", "", "maths");
        dbHelper.addQuestion("What is the perimeter of a rectangle with length 8 units and width 5 units?", "10 units",
                "16 units" , "26 units" , "34 units", "26 units", "", "maths");
        dbHelper.addQuestion("What is the area of a triangle with a base of 10 units and a height of 8 units?", "20 square units" ,
                "40 square units", "60 square units" , "80 square units",
                "60 square units", "", "maths");
        dbHelper.addQuestion("If 2x+3=11, what is the value of x ",
                "2", "4", "5", "7", "4", "", "maths");
        dbHelper.addQuestion("What is the value of π (pi) to two decimal places?", "3.14", "3.16", "3.12", "3.18", "3.14", "", "maths");

        dbHelper.addQuestion("What is the square root of 144?", "10", "12", "14", "16", "12", "", "maths");

        dbHelper.addQuestion("If a triangle has angles measuring 45°, 45°, and 90°, what type of triangle is it?", "Equilateral triangle", "Isosceles triangle", "Scalene triangle", "Right-angled triangle", "Right-angled triangle", "", "maths");

        dbHelper.addQuestion("What is the value of sin(90°)?", "0", "1", "-1", "Undefined", "1", "", "maths");

        dbHelper.addQuestion("What is the area of a circle with radius 5 units (rounded to the nearest whole number)?", "31 square units", "79 square units", "25 square units", "16 square units", "79 square units", "", "maths");

        dbHelper.addQuestion("If a = 5 and b = 7, what is the value of a² + b²?", "24", "50", "74", "84", "74", "", "maths");


        //3rd topic questions
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
        dbHelper.addQuestion("What is data normalization in the context of data science?", "A process to put data in a standardized form", "Removing outliers from the data", "Converting data into a machine-readable format", "Applying mathematical models to the data", "A process to put data in a standardized form", "", "data");

        dbHelper.addQuestion("What is the primary purpose of Exploratory Data Analysis (EDA)?", "Identifying patterns and relationships in the data", "Validating the results of a predictive model", "Creating appealing visualizations of the data", "Preparing the data for model training", "Identifying patterns and relationships in the data", "", "data");

        dbHelper.addQuestion("Which technique is used to reduce the dimensionality of data while retaining as much information as possible?", "Linear regression", "Discriminant analysis", "Principal Component Analysis (PCA)", "Neural networks", "Principal Component Analysis (PCA)", "", "data");

        dbHelper.addQuestion("What type of problem is logistic regression primarily used to solve?", "Predicting continuous values", "Binary classification", "Clustering of data", "Predicting time series", "Binary classification", "", "data");

        dbHelper.addQuestion("What is the first step in the data preprocessing process in machine learning?", "Data collection", "Data cleaning", "Data exploration", "Feature engineering", "Data collection", "", "data");

        dbHelper.addQuestion("Which method is used to evaluate the performance of a machine learning model on unseen data?", "Training and testing split", "Cross-validation", "Confusion matrix analysis", "Feature scaling", "Cross-validation", "", "data");

        //4th topic questions
        dbHelper.addQuestion("What does the acronym \"TCP\" stand for in the context of networking?", "Transfer Control Protocol",
                "Transmission Control Protocol", "Telecommunication Control Protocol", "Technical Control Protocol", "Transmission Control Protocol", "", "networking");
        dbHelper.addQuestion("In the OSI model, which layer is responsible for logical addressing and routing?", "Data Link Layer",
                "Physical Layer" , "Network Layer" , "Transport Layer",
                "Network Layer", "", "networking");
        dbHelper.addQuestion("What is the purpose of a subnet mask in IP networking?", "Identifying the network portion of an IP address" ,
                "Assigning a unique identifier to each device on the network", "Encrypting data during transmission" , "Determining the physical location of a device", "Identifying the network portion of an IP address", "", "networking");
        dbHelper.addQuestion("Which protocol is commonly used for secure communication over the Internet, providing encryption and authentication?", "HTTP",
                "FTP", "SSL/TLS", "UDP", "SSL/TLS", "", "networking");
        dbHelper.addQuestion("What is TCP/IP?", "Transmission Control Protocol/Internet Protocol", "Technical Control Protocol/Internet Protocol", "Transfer Control Protocol/Internet Program", "Transfer Control Program/Internet Protocol", "Transmission Control Protocol/Internet Protocol", "", "networking");

        dbHelper.addQuestion("What is the default port for HTTP?", "80", "443", "22", "21", "80", "", "networking");

        dbHelper.addQuestion("Which IP address is reserved for loopback?", "127.0.0.1", "192.168.1.1", "10.0.0.1", "172.16.0.1", "127.0.0.1", "", "networking");

        dbHelper.addQuestion("Which protocol is used for sending emails?", "SMTP", "FTP", "HTTP", "DHCP", "SMTP", "", "networking");

        dbHelper.addQuestion("What does DNS stand for in computing?", "Domain Name System", "Digital Network Service", "Data Name Server", "Distributed Network System", "Domain Name System", "", "networking");

        dbHelper.addQuestion("Which device is used to connect two networks of different sizes?", "Router", "Switch", "Hub", "Modem", "Router", "", "networking");


        questionsLists = dbHelper.getQuestions(getSelectedTopicName);

        startTimer(timer);

        questions.setText((currentQuestionsPosition + 1) + "/" + 10);
        question.setText(questionsLists.get(0).getQuestion());
        option1.setText(questionsLists.get(0).getOption1());
        option2.setText(questionsLists.get(0).getOption2());
        option3.setText(questionsLists.get(0).getOption3());
        option4.setText(questionsLists.get(0).getOption4());


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

        if ((currentQuestionsPosition + 1) == 10) {
            nextBtn.setText("Submit Quiz");
        }
        if (currentQuestionsPosition < 10) {
            selectedOptionByUser = "";

            option1.setBackgroundResource(R.drawable.round_back_orange20);
            option1.setTextColor(Color.parseColor("#000000"));

            option2.setBackgroundResource(R.drawable.round_back_orange20);
            option2.setTextColor(Color.parseColor("#000000"));

            option3.setBackgroundResource(R.drawable.round_back_orange20);
            option3.setTextColor(Color.parseColor("#000000"));

            option4.setBackgroundResource(R.drawable.round_back_orange20);
            option4.setTextColor(Color.parseColor("#000000"));

            questions.setText((currentQuestionsPosition + 1) + "/" + 10);

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
        for (int i = 0; i < 10; i++) {
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
        for (int i = 0; i < 10; i++) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // Gérer l'action correspondant à l'élément de menu "Paramètres"
            return true;
        }
        // Ajoutez d'autres cas pour gérer les autres éléments de menu si nécessaire

        return super.onOptionsItemSelected(item);
    }
}