package com.example.onlineexamination;

import android.util.Log;

public class ResultModelClass {
    private String name, username, subject, date, score;

    public ResultModelClass(String name, String username, String subject, String date, String score) {
        this.name = name;
        this.username = username;
        this.subject = subject;
        this.date = date;
        this.score = score;
        Log.i("tagconvertstrzx", "["+getUsername()+"]");

    }

    public String getName() {
        Log.i("tagconvertstr", "["+name+"]");
        return name;

    }

    public String getUsername() {
        return username;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getScore() {
        Log.i("tagconvertstr", "["+score+"]");
        return score;
    }
}
