package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Member;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RegistrationPage extends AppCompatActivity {

    private EditText editTextname, editTextdob, editTextgender, editTextemail, editTextphone, editTextusername, editTextpassword;
    private TextView textViewLogin;
//    String name1,dob1,gender1,email1,phone1,username1,password1;
    private Button singup;
    private ProgressDialog progressDialog;
   // ProgressBar progressBar;
    Member member;
    long maxId = 0;

  //  private static final String REQUEST_URL = "https://baronetical-file.000webhostapp.com/registration.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);


        editTextname = (EditText)findViewById(R.id.editTextName);
        editTextdob = (EditText)findViewById(R.id.editTextDOB);
        editTextgender = (EditText)findViewById(R.id.editTextGender);
        editTextemail = (EditText)findViewById(R.id.editTextEmail);
        editTextphone = (EditText)findViewById(R.id.editTextPhone);
        editTextusername = (EditText)findViewById(R.id.editTextUsername);
        editTextpassword = (EditText)findViewById(R.id.editTextPassword);
        singup = (Button)findViewById(R.id.singup);
        textViewLogin = (TextView)findViewById(R.id.textViewLogin);

       progressDialog = new ProgressDialog(this);


       //textViewLogin.setOnClickListener(this);


        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
}



// this is it

    private void registerUser() {

/*
        String name1 = name.getText().toString().trim();
        String dob1 = dob.getText().toString().trim();
        String gender1 = gender.getText().toString().trim();
        String email1 = email.getText().toString().trim();
        String phone1 = phone.getText().toString().trim();
        String username1 = username.getText().toString().trim();
        String password1 = password.getText().toString().trim();

        progressDialog.setMessage("Registering...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Intent intent = new Intent(RegistrationPage.this,LoginPage.class);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                           // Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(RegistrationPage.this, "Server problem", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("name",name1);
                params.put("dob",dob1);
                params.put("gender",gender1);
                params.put("email",email1);
                params.put("phone",phone1);
                params.put("username",username1);
                params.put("password",password1);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
*/









      final   String name = editTextname.getText().toString().trim();
      final   String dob = editTextdob.getText().toString().trim();
      final   String gender = editTextgender.getText().toString().trim();
      final String email = editTextemail.getText().toString().trim();
      final    String phone = editTextphone.getText().toString().trim();
      final   String username = editTextusername.getText().toString().trim();
      final   String password = editTextpassword.getText().toString().trim();

       // final String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();

        //first we will do the validations

        if (TextUtils.isEmpty(username)) {
            editTextusername.setError("Please enter your username");
            editTextusername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            editTextpassword.setError("Please enter your password");
            editTextpassword.requestFocus();
            return;
        }

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

          //  private ProgressBar progressBar;



            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                /*progressBar = (ProgressBar) findViewById(R.id.progressBar1);
                progressBar.setVisibility(View.VISIBLE);*/
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
             //   progressBar.setVisibility(View.GONE);
                progressDialog.setMessage("Registering...");
                progressDialog.show();


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getString("name"),
                                userJson.getString("dob"),
                                userJson.getString("gender"),
                                userJson.getString("email"),
                                userJson.getString("phone"),
                                userJson.getString("username")
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                       // finish();
                        startActivity(new Intent(getApplicationContext(), LoginPage.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_LONG).show();
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
                params.put("name",name);
                params.put("dob",dob);
                params.put("gender",gender);
                params.put("email",email);
                params.put("phone",phone);
                params.put("username",username);
                params.put("password",password);

                //returing the response
                return requestHandler.sendPostRequest(Constants.URL_REGISTER, params);
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();




    }

   /* @Override
    public void onClick(View v) {

        if (v == singup) {
            registerUser();
        }
        *//*if (v == textViewLogin){
            Intent intent = new Intent(RegistrationPage.this,LoginPage.class);
            startActivity(intent);
        }*//*
    }*/

}