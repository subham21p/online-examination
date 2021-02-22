package com.example.onlineexamination;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String SHARED_PREF_NAMEA = "simplifiedcodingsharedprefa";
    private static final String SHARED_PREF_SUBJECT = "simplifiedcodingsharedprefasub";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_DOB = "keydob";
    private static final String KEY_PHONE = "keyphone";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_NAMEA = "keynamea";
    private static final String KEY_USERNAMEA = "keyusernamea";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_DATE = "date";
    private static final String KEY_SCORE = "score";
    private static final String KEY_SELECET_SUBJECT = "selectSubject";


    private static SharedPrefManager mInstance;
    private static Context mCtx;

    SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_DOB, user.getDob());
        editor.putString(KEY_GENDER, user.getGender());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PHONE, user.getPhone());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.apply();
    }

    public void resultLogin(ResultModelClass resultModelClass) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAMEA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAMEA, resultModelClass.getName());
        editor.putString(KEY_USERNAMEA, resultModelClass.getUsername());
        editor.putString(KEY_SUBJECT, resultModelClass.getSubject());
        editor.putString(KEY_DATE, resultModelClass.getDate());
        editor.putString(KEY_SCORE, resultModelClass.getScore());
        editor.apply();
    }




    public void selectedSubject(SubjectModelClass subjectModelClass) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_SUBJECT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SELECET_SUBJECT, subjectModelClass.getSub());
        editor.apply();
    }





    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_DOB, null),
                sharedPreferences.getString(KEY_GENDER, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PHONE, null),
                sharedPreferences.getString(KEY_USERNAME, null)


        );
    }


    public ResultModelClass getResult() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAMEA, Context.MODE_PRIVATE);
        return new ResultModelClass(
                sharedPreferences.getString(KEY_NAMEA, null),
                sharedPreferences.getString(KEY_USERNAMEA, null),
                sharedPreferences.getString(KEY_SUBJECT, null),
                sharedPreferences.getString(KEY_DATE, null),
                sharedPreferences.getString(KEY_SCORE, null)


        );
    }


    public SubjectModelClass getSubjects() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_SUBJECT, Context.MODE_PRIVATE);
        return new SubjectModelClass(
                sharedPreferences.getString(KEY_SELECET_SUBJECT, null)

        );
    }



    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginPage.class));
    }
}
