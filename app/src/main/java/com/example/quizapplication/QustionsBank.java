package com.example.quizapplication;

import java.util.ArrayList;
import java.util.List;

public class QustionsBank {
    private static List<QuestionsList> programmingQuestions(){
        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Which programming language is known for its simplicity and readability, and uses indentation to define code blocks?", "Java",
                "C++", "Python", "JavaScript", "Python", "");
        final QuestionsList question2 = new QuestionsList("In object-oriented programming, what is encapsulation?", "The process of converting code into machine language",
                "The bundling of data and methods that operate on that data" , "The technique of organizing code into reusable units" , "The act of testing code before deployment",
                 "The bundling of data and methods that operate on that data", "");
        final QuestionsList question3 = new QuestionsList("Which data structure follows the Last In, First Out (LIFO) principle?", "Queue" ,
                "Stack", "Linked List" , "Array",
                 "Stack", "");
        final QuestionsList question4 = new QuestionsList("Which of the following programming languages is commonly used for building Android applications?", "Swift",
                "Kotlin", "Java", "C++", "Java", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);

        return questionsLists;

    }
    private static List<QuestionsList> mathsQuestions(){
        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("What is the result of 2^3 x 3^2 ?", "12",
                "72", "216", "64", "216", "");
        final QuestionsList question2 = new QuestionsList("What is the perimeter of a rectangle with length 8 units and width 5 units?", "10 units",
                "16 units" , "26 units" , "34 units", "26 units", "");
        final QuestionsList question3 = new QuestionsList("What is the area of a triangle with a base of 10 units and a height of 8 units?", "20 square units" ,
                "40 square units", "60 square units" , "80 square units",
                "60 square units", "");
        final QuestionsList question4 = new QuestionsList("If 2x+3=11, what is the value of x ",
                "2", "4", "5", "7", "4", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);

        return questionsLists;

    }
    private static List<QuestionsList> dataQuestions(){
        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("What is the primary goal of exploratory data analysis (EDA) in data science?", " Building predictive models",
                "Summarizing and visualizing data", "Cleaning and preprocessing data", "Evaluating model performance", "Summarizing and visualizing data", "");
        final QuestionsList question2 = new QuestionsList("Which of the following algorithms is commonly used for clustering in unsupervised machine learning?", "Decision Trees",
                "K-Means" , " Support Vector Machines (SVM)" , " Random Forest",
                "K-Means", "");
        final QuestionsList question3 = new QuestionsList("In the context of machine learning, what does \"overfitting\" refer to?", "The model performs well on the training data but poorly on new, unseen data" ,
                "The model is too simple and cannot capture the underlying patterns in the data", "The model memorizes the training data instead of learning the underlying patterns" , "The model is unable to converge during the training process",
                "The model memorizes the training data instead of learning the underlying patterns", "");
        final QuestionsList question4 = new QuestionsList("What is the purpose of the term \"feature scaling\" in machine learning?", "Selecting the most important features for model training",
                "Transforming categorical features into numerical representations", "Scaling the target variable for regression models", " Ensuring that all input features have the same scale or distribution", " Ensuring that all input features have the same scale or distribution", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);

        return questionsLists;

    }
    private static List<QuestionsList> networkingQuestions(){
        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("What does the acronym \"TCP\" stand for in the context of networking?", "Transfer Control Protocol",
                "Transmission Control Protocol", "Telecommunication Control Protocol", "Technical Control Protocol", "Transmission Control Protocol", "");
        final QuestionsList question2 = new QuestionsList("In the OSI model, which layer is responsible for logical addressing and routing?", "Data Link Layer",
                "Physical Layer" , "Network Layer" , "Transport Layer",
                "Network Layer", "");
        final QuestionsList question3 = new QuestionsList("What is the purpose of a subnet mask in IP networking?", "Identifying the network portion of an IP address" ,
                "Assigning a unique identifier to each device on the network", "Encrypting data during transmission" , "Determining the physical location of a device", "Identifying the network portion of an IP address", "");
        final QuestionsList question4 = new QuestionsList("Which protocol is commonly used for secure communication over the Internet, providing encryption and authentication?", "HTTP",
                "FTP", "SSL/TLS", "UDP", "SSL/TLS", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);

        return questionsLists;

    }
    public static List<QuestionsList> getQuestions(String selectedTopicName) {
        switch(selectedTopicName){
            case "programming":
                return programmingQuestions();
            case "maths":
                return mathsQuestions();
            case "data":
                return dataQuestions();
            case "networking":
                return networkingQuestions();
        }
        return null;
    }
}
