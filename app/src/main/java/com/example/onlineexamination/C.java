package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.onlineexamination.Constants.URL_C;
import static com.example.onlineexamination.Constants.URL_JAVA;
import static com.example.onlineexamination.Constants.URL_JS;
import static com.example.onlineexamination.Constants.URL_PYTHON;

public class C extends AppCompatActivity {

    public float counter;
    TextView name2, dob2,gender2,email2,phone2, exam, marks, textViewQ1, textViewQ2, corr_opt, timmer;
    Button submit;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4,radioGroup5, radioGroup6, radioGroup7, radioGroup8, radioGroup9, radioGroup10;
    private RadioButton radiobutton, opt1, opt2, opt3, opt4, ans6, ans7, ans8, ans9, ans10;
    private int currentQuizQuestion;

    private int quizCount;

    private cQuestions firstQuestions;

   // private List<cQuestions> parsedObject;

public static  String choos_textView;
    Handler myHandler;
    Runnable myRunnable;
    private RequestQueue mRequestQueue;

    public static LinearLayout data ;

    public static String radio1;

    //a list to store all the products
    public static   List<cQuestions> questionList;

    //the recyclerview
    private     RecyclerView recyclerView;

    QuestionListAdapter questionListAdapter;

    public static TextView textArray;
    public static String[] stocks1 = new String[10];
    public static int i = 0;

//public static String radio1;
    TextureView textureView;
public static int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);


        exam = (TextView)findViewById(R.id.textViewExamName);
        timmer = (TextView)findViewById(R.id.textViewTimmer);


        new CountDownTimer(10000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                timmer.setText(""+String.format("%d:%d ",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                timmer.setText("done!");
            }
        }.start();
        
        /*Bundle bundle = getIntent().getExtras();
        String message1 = exam.getText().toString();
        message1 = bundle.getString("exam");*/
        //exam.setText(message1);

        submit = (Button)findViewById(R.id.submit);

        textViewQ1 = (TextView)findViewById(R.id.textViewAQus1);
     ///   textViewQ2 = (TextView)findViewById(R.id.textViewAQus2);


        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        opt1 = (RadioButton)findViewById(R.id.optA11);
        opt2 = (RadioButton)findViewById(R.id.optA12);
        opt3 = (RadioButton)findViewById(R.id.optA13);
        opt4 = (RadioButton)findViewById(R.id.optA14);
      //  corr_opt = (TextView) findViewById(R.id.corr_ans);

      /*  ans6 = (RadioButton)findViewById(R.id.optA21);
        ans7 = (RadioButton)findViewById(R.id.optA22);
        ans8 = (RadioButton)findViewById(R.id.optA23);
        ans9 = (RadioButton)findViewById(R.id.optA24);
*/
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(C.this));
     //   choos_textView = ((RadioButton) findViewById(radioGroup1.getCheckedRadioButtonId())).getText().toString();


        data = (LinearLayout)findViewById(R.id.linear);


        /*AsyncJsonObject asyncObject = new AsyncJsonObject();

        asyncObject.execute("");
*/








        //initializing the productlist
        questionList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
     //   loadProducts();
    //    loadProducts();



        textArray = (TextView)findViewById(R.id.text);

        SubjectModelClass subjectModelClass = SharedPrefManager.getInstance(this).getSubjects();
        String subject = " ";
        subject = subjectModelClass.getSub();

        String c = "C", java = "JAVA", python = "PYTHON", js = "JS";

        if (c == subject){
            downloadJSON(URL_C);
        }

        else if (java == subject){
            downloadJSON(URL_JAVA);
        }

        else if (python == subject){
            downloadJSON(URL_PYTHON);
        }

        else if (js == subject){
            downloadJSON(URL_JS);
        }




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Bundle bundle= getIntent().getExtras();
                String id = bundle.getString("movie_id_key");
                textArray.setText(id);*/


               /* String ItemName = textArray.getText().toString();
              //  String qty = quantity.getText().toString();
                Intent intent = new Intent("custom-message");
                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
            //    intent.putExtra("quantity",qty);
                intent.putExtra("item",ItemName);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                Toast.makeText(Aptitude.this,ItemName,Toast.LENGTH_LONG).show();*/

             //   LocalBroadcastManager.getInstance(Aptitude.this).registerReceiver(mMessageReceiver,
               //         new IntentFilter("custom-message"));

                //    downloadJSON("http://192.168.1.6/online_examination/includes/Api.php?apicall=c");




        /*        int radioSelected = radioGroup1.getCheckedRadioButtonId();

                int userSelection = getSelectedAnswer(radioSelected);

                int correctAnswerForQuestion = firstQuestions.getCorrectAnswer();

                if (userSelection == correctAnswerForQuestion) {

// correct answer

                    Toast.makeText(Aptitude.this, "You got the answer correct", Toast.LENGTH_LONG).show();

                    currentQuizQuestion++;

                    if (currentQuizQuestion >= quizCount) {

                        Toast.makeText(Aptitude.this, "End of the Quiz Questions", Toast.LENGTH_LONG).show();

                        return;

                    } else {

                        firstQuestions = parsedObject.get(currentQuizQuestion);

                        textViewQ1.setText(firstQuestions.getQuestion());

                        String[] possibleAnswers = firstQuestions.getAnswers().split(",");

                        uncheckedRadioButton();

                        opt1.setText(possibleAnswers[0]);

                        opt2.setText(possibleAnswers[1]);

                        opt3.setText(possibleAnswers[2]);

                        opt4.setText(possibleAnswers[3]);

                    }

                }

                else{

// failed question

                    Toast.makeText(Aptitude.this, "You chose the wrong answer", Toast.LENGTH_LONG).show();

                    return;

                }*/
            }


        });

        }
    private BroadcastReceiver aLBReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // perform action here.
            radio1 = intent.getStringExtra("key");
            textArray.setText(radio1);
          //  radio1.split(",");
         //   Toast.makeText(Aptitude.this,"mask" + radio1.toString() ,Toast.LENGTH_SHORT).show();

        }
    };

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            radio1 = intent.getStringExtra("radioButton1");
            String radio2 = intent.getStringExtra("radioButton1");
            String radio3 = intent.getStringExtra("radioButton1");
            String radio4 = intent.getStringExtra("radioButton1");

            if(textArray.getText().toString() == radio1){
                c++;
            }

            Toast.makeText(C.this,"mask" + c ,Toast.LENGTH_SHORT).show();

           // Toast.makeText(Aptitude.this,radio1 +" "+radio2 ,Toast.LENGTH_SHORT).show();
        }
    };


    private void downloadJSON(final String urlWebService) {

        class DownloadJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //   Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                  /*  RequestHandler requestHandler = new RequestHandler();
                    HashMap<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);

                    //returing the response
                    return requestHandler.sendPostRequest(Constants.URL_LOGIN, params);*/
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] stocks = new String[jsonArray.length()];
        stocks1 = new String[jsonArray.length()];
        for (i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
         //   stocks[i] = obj.getInt("id") + ")" + obj.getString("question") + "\n" + obj.getString("option_one") + "\n" + obj.getString("option_two") + "\n" + obj.getString("option_three") + "\n" + obj.getString("option_four") + "\n" + obj.getString("correct_option");
            //  cQuestions cQuestions = new cQuestions()

            stocks1[i] = obj.getString("correct_option");
          /*  textArray.append(stocks1[i]);
            textArray.append("\n");*/
         //   textArray.getText().toString();


             questionList.add(new cQuestions(
                    obj.getInt("id"),
                    obj.getString("question"),
                    obj.getString("option_one"),
                    obj.getString("option_two"),
                    obj.getString("option_three"),
                    obj.getString("option_four"),
                    obj.getString("correct_option")
            ));

            //storing the user in shared preferences
           // Subjects.SharedPrefManager1.getInstance(getApplicationContext()).Question(cquestions);
            Log.i("tagconvertstr", "["+questionList+"]");

        }
       /* ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stocks);
        listView.setAdapter(arrayAdapter);*/

        QuestionListAdapter adapter = new QuestionListAdapter(questionList, C.this);

        recyclerView.setAdapter(adapter);
      //  setRadioGroup();
    }




    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_C,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject obj = array.getJSONObject(i);

                                //adding the product to product list
                                questionList.add(new cQuestions(
                                        obj.getInt("id"),
                                        obj.getString("question"),
                                        obj.getString("option_one"),
                                        obj.getString("option_two"),
                                        obj.getString("option_three"),
                                        obj.getString("option_four"),
                                        obj.getString("correct_option")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            QuestionListAdapter adapter = new QuestionListAdapter(questionList, C.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }













/*

    private class AsyncJsonObject extends AsyncTask<String, Void, String> {

        private ProgressDialog progressDialog;
*/

        //@Override

/*        protected String doInBackground(String... params) {

            HttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());

            HttpPost httpPost = new HttpPost("http://10.0.2.2/androidlogin/index.php");

            String jsonResult = "";

            try {

                HttpResponse response = httpClient.execute(httpPost);

                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();

                System.out.println("Returned Json object " + jsonResult.toString());

            } catch (ClientProtocolException e) {

// TODO Auto-generated catch block

                e.printStackTrace();

            } catch (IOException e) {

// TODO Auto-generated catch block

                e.printStackTrace();

            }

            return jsonResult;

        }*/

/*        @Override
        protected String doInBackground(String... strings) {

            HttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());

            HttpPost httpPost = new HttpPost("http://192.168.1.6/online_examination/v1/index.php");

            String jsonResult = "";

            try {

                HttpResponse response = httpClient.execute(httpPost);

                jsonResult = inputStreamToString(((org.apache.http.HttpResponse) response).getEntity().getContent()).toString();

                System.out.println("Returned Json object " + jsonResult.toString());

            } catch (ClientProtocolException e) {

// TODO Auto-generated catch block

                e.printStackTrace();

            } catch (IOException e) {

// TODO Auto-generated catch block

                e.printStackTrace();

            }




            return jsonResult;
        }

        @Override

        protected void onPreExecute() {

// TODO Auto-generated method stub

            super.onPreExecute();

            progressDialog = ProgressDialog.show(Aptitude.this, "Downloading Quiz","Wait....", true);

        }

        @Override

        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            progressDialog.dismiss();

            System.out.println("Resulted Value: " + result);

            try {
                parsedObject = returnParsedJsonObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(parsedObject == null){

                return;

            }

           quizCount = parsedObject.size();

            firstQuestions = parsedObject.get(0);

            textViewQ1.setText(firstQuestions.getQuestion());

            String[] possibleAnswers = firstQuestions.getAnswers().split(",");

            opt1.setText(possibleAnswers[0]);

            opt2.setText(possibleAnswers[1]);

            opt3.setText(possibleAnswers[2]);

            opt4.setText(possibleAnswers[3]);

        }

        private StringBuilder inputStreamToString(InputStream is) {

            String rLine = "";

            StringBuilder answer = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            try {

                while ((rLine = br.readLine()) != null) {

                    answer.append(rLine);

                }

            } catch (IOException e) {

// TODO Auto-generated catch block

                e.printStackTrace();

            }

            return answer;

        }

    }


    private List<cQuestions> returnParsedJsonObject(String result) throws JSONException {

        List<cQuestions> jsonObject = new ArrayList<cQuestions>();
      //  JSONArray jsonarray = new JSONArray(result);
        JSONObject resultObject = null;

        JSONArray jsonArray = new JSONArray(result);;


        cQuestions newItemObject = null;

        try {

            resultObject = new JSONObject(result);

            System.out.println("Testing the water " + resultObject.toString());
        //    JSONArray jsonArray = new JSONArray(result);
            jsonArray = resultObject.optJSONArray("online_examination");

        } catch (JSONException e) {

            e.printStackTrace();

        }

        for(int i = 0; i < jsonArray.length(); i++){

            JSONObject jsonChildNode = null;

            try {

                jsonChildNode = jsonArray.getJSONObject(i);

                int id = jsonChildNode.getInt("id");

                String question = jsonChildNode.getString("question");

                String answerOptions = jsonChildNode.getString("possible_answers");

                int correctAnswer = jsonChildNode.getInt("correct_answer");

                newItemObject = new cQuestions(id, question, answerOptions, correctAnswer);

                jsonObject.add(newItemObject);

            } catch (JSONException e) {

                e.printStackTrace();

            }

        }

        return jsonObject;

    }


    private int getSelectedAnswer(int radioSelected){

        int answerSelected = 0;

        if(radioSelected == R.id.optA11){

            answerSelected = 1;

        }

        if(radioSelected == R.id.optA12){

            answerSelected = 2;

        }

        if(radioSelected == R.id.optA13){

            answerSelected = 3;

        }

        if(radioSelected == R.id.optA14){

            answerSelected = 4;

        }

        return answerSelected;

    }

    private void uncheckedRadioButton(){

        opt1.setChecked(false);

        opt2.setChecked(false);

        opt3.setChecked(false);

        opt4.setChecked(false);

    }*/













        /*final TextView counttime=findViewById(R.id.textViewTimmer);

        new void CountDownTimer(10000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                counttime.setText(""+String.format("%d:%d ",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                counttime.setText("done!");
            }
        }.start();





        myHandler = new Handler();
        myRunnable = new Runnable() {
            @Override
            public void run() {



//                questions qust = new questions();
//                qust.execute();





                // marks.setText("mark = "+i);


                Toast.makeText(Aptitude.this, "Time Up !", Toast.LENGTH_SHORT).show();
                finish();
            }
        };


        myHandler.postDelayed(myRunnable,10*1000);*/








  /*      class questions extends AsyncTask<Void,Void,String>{

           *//* @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // progressBar = (ProgressBar) findViewById(R.id.progressBar2);
               // progressBar.setVisibility(View.VISIBLE);
            }*//*

                String data = "";
            @Override
            protected String doInBackground(Void... voids) {

                try {
                    URL url = new URL(URL_C);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    String line = null;
                    while (line != null){
                        line = bufferedReader.readLine();
                        data = data + line;
                    }

                   *//* //creating request handler object
                    RequestHandler requestHandler = new RequestHandler();

                    //creating request parameters
                    HashMap<String, String> params = new HashMap<>();
                    //   String choos_textView = null;
                    params.put("c_ans", choose_ans);
                    //  params.put("password", password);

                    //returing the response

                    return requestHandler.sendPostRequest(Constants.URL_C_ANSWER, params);*//*
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return null;
            }



            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
             //   progressBar.setVisibility(View.GONE);

                Aptitude.textViewQ1.setText(this.data);
*//*
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("qus");

                        //creating a new user object
                        *//**//*User user = new User(
                                userJson.getString("name"),
                                userJson.getString("dob"),
                                userJson.getString("gender"),
                                userJson.getString("email"),
                                userJson.getString("phone"),
                                userJson.getString("username")
                        );*//**//*

                        fetchQuestion qus = new fetchQuestion(
                                userJson.getString("question"),
                                userJson.getString("option_one"),
                                userJson.getString("option_two"),
                                userJson.getString("option_three"),
                                userJson.getString("option_four"),
                                userJson.getString("correct_option"),
                                userJson.getString("c_ans")
                                );

                        //storing the user in shared preferences
                        SharedPrefManager1.getInstance(getApplicationContext()).Question(qus);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), Subjects.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or ....", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*//*
            }





        }
*/





 /* public static class SharedPrefManager1 {

      private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
      private static final String KEY_QUESTION = "keyqus";
      private static final String KEY_OPT_ONE = "keyopt1";
      private static final String KEY_OPT_TWO = "keyopt2";
      private static final String KEY_OPT_THREE = "keyopt3";
      private static final String KEY_OPT_FOUR = "keyopt4";
      private static final String KEY_CORRECT_OPT = "keycorrect";
      private static final String KEY_CHOOSE_OPT = "keychoose";


      private static SharedPrefManager1 mInstance;
      private static Context mCtx;

      private SharedPrefManager1(Context context) {
          mCtx = context;
      }

      public static synchronized SharedPrefManager1 getInstance(Context context) {
          if (mInstance == null) {
              mInstance = new SharedPrefManager1(context);
          }
          return mInstance;
      }

      //method to let the user login
      //this method will store the user data in shared preferences
      public void Question(fetchQuestion qus) {
          SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
          SharedPreferences.Editor editor = sharedPreferences.edit();
          editor.putString(KEY_QUESTION, qus.getQuestion());
          editor.putString(KEY_OPT_ONE, qus.getOpt1());
          editor.putString(KEY_OPT_TWO, qus.getOpt2());
          editor.putString(KEY_OPT_THREE, qus.getOpt3());
          editor.putString(KEY_OPT_FOUR, qus.getOpt4());
          editor.putString(KEY_CORRECT_OPT, qus.getCorr_opt());
          editor.putString(KEY_CHOOSE_OPT, qus.getChoose_opt());

          editor.apply();
      }

      //this method will checker whether user is already logged in or not
      public boolean isLoggedIn() {
          SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
          return sharedPreferences.getString(KEY_CHOOSE_OPT, null) != null;
      }

      //this method will give the logged in user
      public fetchQuestion getUser() {
          SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
          return new fetchQuestion(
                  sharedPreferences.getString(KEY_QUESTION, null),
                  sharedPreferences.getString(KEY_OPT_ONE, null),
                  sharedPreferences.getString(KEY_OPT_TWO, null),
                  sharedPreferences.getString(KEY_OPT_THREE, null),
                  sharedPreferences.getString(KEY_OPT_FOUR, null),
                  sharedPreferences.getString(KEY_CORRECT_OPT, null),
                  sharedPreferences.getString(KEY_CHOOSE_OPT, null)



          );
      }

   */
}
