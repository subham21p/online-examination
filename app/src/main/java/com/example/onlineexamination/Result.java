package com.example.onlineexamination;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.security.auth.Subject;

import static com.example.onlineexamination.Constants.URL_RESULT_CHECK;

public class Result extends AppCompatActivity {

    TextView mask, examName, username, PName, date;
    String ms, en;
    Button button;
    AlertDialog.Builder builder;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mask = (TextView)findViewById(R.id.mark);
        examName = (TextView)findViewById(R.id.textViewExamNameR);
        PName = (TextView)findViewById(R.id.textViewPName);
      //  username = (TextView)findViewById(R.id.textViewUsername);
        date = (TextView)findViewById(R.id.textViewdate);


        button = (Button)findViewById(R.id.button);


        User user = SharedPrefManager.getInstance(this).getUser();

        Intent mIntent = getIntent();
        String subject = mIntent.getStringExtra("subject");


        resutlShow(subject,user.getUsername());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }




    private void resutlShow(String subject, String username){


        class ResultShow extends AsyncTask<Void, Void, String>{
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
                      //  Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONObject userJson = obj.getJSONObject("result");

                        PName.setText(userJson.getString("name"));
                        examName.setText(userJson.getString("subject"));
                        date.setText(userJson.getString("date"));
                        mask.setText(userJson.getString("score"));

                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                return requestHandler.sendPostRequest(URL_RESULT_CHECK, params);
            }
        }


        ResultShow ru = new ResultShow();
        ru.execute();


    }


}