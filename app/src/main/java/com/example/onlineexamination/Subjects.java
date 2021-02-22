package com.example.onlineexamination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.example.onlineexamination.Constants.URL_RESULT_CHECK;

public class Subjects extends AppCompatActivity {

  private   TextView welcome, name, choose, nameNav, emailNav;

    Button button_c, button_java, button_python, button_js;

 private String subject_C = "C", subject_JAVA = "JAVA", subject_PYTHON = "PYTHON", subject_JS = "JS";

 private  DrawerLayout drawerLayout;
 private NavigationView navigationView;
 private ImageView imageViewMenu, imageViewNavBack;
 private TextView textViewUsername;
 private String i = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);


        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginPage.class));
        }

        welcome = (TextView)findViewById(R.id.textViewWelcome);
        name = (TextView)findViewById(R.id.textViewName);
        choose = (TextView)findViewById(R.id.textViewChooseSub);
        button_c = (Button)findViewById(R.id.subC);
        button_java = (Button)findViewById(R.id.subJava);
        button_python = (Button)findViewById(R.id.subPython);
        button_js = (Button)findViewById(R.id.subJS);




        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);

        imageViewMenu = (ImageView)findViewById(R.id.imageViewNavMenu);

        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if(!drawerLayout.isDrawerOpen(Gravity.START)) drawerLayout.openDrawer(Gravity.START);
                else drawerLayout.closeDrawer(Gravity.END);
            }
        });


        navigationView = (NavigationView)findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.result:
                       // checkExamForResult();
                        startActivity(new Intent(Subjects.this,ResultShow.class));
                        drawerLayout.closeDrawers();
                       // Toast.makeText(Subjects.this, "Result",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.logout:
                        SharedPrefManager.getInstance(getApplicationContext()).logout();
                        Toast.makeText(Subjects.this, "Logout",Toast.LENGTH_SHORT).show();
                    default:
                        return true;
                }

            }
        });

        View header = navigationView.getHeaderView(0);
        nameNav = (TextView) header.findViewById(R.id.textViewNavName);
        emailNav = (TextView) header.findViewById(R.id.textViewNavEmail);
        textViewUsername = (TextView)findViewById(R.id.textViewSUsername);
        imageViewNavBack = (ImageView)header.findViewById(R.id.imageViewNavBack);

        imageViewNavBack.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if(!drawerLayout.isDrawerOpen(Gravity.START)) drawerLayout.openDrawer(Gravity.START);
                else drawerLayout.closeDrawer(Gravity.START);
            }
        });

        User user = SharedPrefManager.getInstance(this).getUser();

        name.setText(user.getName());
        nameNav.setText(user.getName());
        emailNav.setText(user.getEmail());
        textViewUsername.setText(user.getUsername());





        //  reson = (Button)findViewById(R.id.subResoning);
       // eng = (Button)findViewById(R.id.subEnglish);

       // textViewArray = (TextView)findViewById(R.id.textViewArray);

       /// show = (TextView)findViewById(R.id.show);



        button_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SubjectModelClass subjectModelClass = new SubjectModelClass(
                        subject_C
                );
                SharedPrefManager.getInstance(getApplicationContext()).selectedSubject(subjectModelClass);
                checkIsExamGiven(subject_C,URL_RESULT_CHECK);
            }
        });


        button_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SubjectModelClass subjectModelClass = new SubjectModelClass(
                        subject_JAVA
                );
                SharedPrefManager.getInstance(getApplicationContext()).selectedSubject(subjectModelClass);

                checkIsExamGiven(subject_JAVA,URL_RESULT_CHECK);
            }
        });

        button_python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SubjectModelClass subjectModelClass = new SubjectModelClass(
                        subject_PYTHON
                );
                SharedPrefManager.getInstance(getApplicationContext()).selectedSubject(subjectModelClass);

                checkIsExamGiven(subject_PYTHON,URL_RESULT_CHECK);
            }
        });


        button_js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SubjectModelClass subjectModelClass = new SubjectModelClass(
                        subject_JS
                );
                SharedPrefManager.getInstance(getApplicationContext()).selectedSubject(subjectModelClass);

                checkIsExamGiven(subject_JS,URL_RESULT_CHECK);
            }
        });

    }

    private void checkIsExamGiven(String subject, String url){

        final String username = textViewUsername.getText().toString();

        class CheckUser extends AsyncTask<Void, Void, String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    //converting response to json
                    Log.i("fhdb",s);
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    } else {
                        //Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Subjects.this, C.class));


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("subject", subject);

                //returing the response
                return requestHandler.sendPostRequest(url, params);
            }
        }


        CheckUser ru = new CheckUser();
        ru.execute();


    }















}