package com.example.onlineexamination;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_LONG;
import static com.example.onlineexamination.C.i;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionListViewHolder>{

    String gen;

    private List<cQuestions> questions;
    private Context context;

    private RadioGroup lastCheckedRadioGroup = null;
 //   private List<cQuestions> questionsList;
 private RadioButton lastCheckedRB = null;

private  int c = 0;

    public QuestionListAdapter(List<cQuestions> questionsList
            , Context ctx) {

        questions = questionsList;
        context = ctx;
    }


    @Override
    public QuestionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.questions_list, null);
        return new QuestionListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(QuestionListViewHolder holder, int position) {
        cQuestions cquestions = questions.get(position);
        final int pos = position;

        final int[] a = {0};

        holder.textViewQusNo.setText(String.valueOf(cquestions.getId()));
        holder.textViewQus.setText(cquestions.getQuestion());
        holder.radioButton1.setText(cquestions.getOption_one());
        holder.radioButton2.setText(cquestions.getOption_two());
        holder.radioButton3.setText(cquestions.getOption_three());
        holder.radioButton4.setText(cquestions.getOption_four());
        String abc = "abc";
        holder.textViewCorrectAns.setText(cquestions.getCorrect_option());
        Log.i("tagconvertstrabcd", String.valueOf(questions));






                //  final String gender = " ";

        // final String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();


      //  Toast.makeText(QuestionListAdapter.this.context,"selected" + c,LENGTH_LONG).show();

        //final RadioButton[] radioButton = new RadioButton[0];

       /* holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
               radioButton[pos] = (RadioButton) group.findViewById(checkedId);
                gen = radioButton[pos].getText().toString();
                Toast.makeText(context, gen, Toast.LENGTH_SHORT).show(); }
        });
*/

        if (holder.radioButton1.isChecked()){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(context, C.class);
        bundle.putParcelable("movie_id_key", (Parcelable) holder.radioButton1);
            intent.putExtras(bundle);

            //you can name the keys whatever you like
        }

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checked_rb = (RadioButton) group.findViewById(checkedId);
                if (lastCheckedRB != null) {
                    lastCheckedRB.setChecked(true);
                }
                //store the clicked radiobutton
                lastCheckedRB = checked_rb;
              //  Toast.makeText(QuestionListAdapter.this.context,"selected" + lastCheckedRB.getText(),LENGTH_LONG).show();

                int radioID = holder.radioGroup.getCheckedRadioButtonId();
               RadioButton radioButton = group.findViewById(radioID);
               String abc = (String) checked_rb.getText();
              String cba = (String)holder.textViewCorrectAns.getText().toString();


               if (lastCheckedRB != null) {
                    lastCheckedRB.setChecked(true);
                }
                lastCheckedRB = checked_rb;



                if (lastCheckedRB.getText().toString().toLowerCase().trim().equals(cba.toLowerCase().trim())){
                    a[0]++;

                }
                else {
                   a[0] = 0;
                 //   Toast.makeText(QuestionListAdapter.this.context,"selected" + abc + "\n"+ cba,LENGTH_LONG).show();

                }
                  c = c + a[0];
                Log.i("score2", "["+a[0]+"]");

            }
        });

        Handler myHandler;
        Runnable myRunnable;
        String C = "C";
        myHandler = new Handler();
        myRunnable = new Runnable() {
            @Override
            public void run() {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("OnlineExamination");
                alertDialog.setMessage("Your Exam is over please submit !");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                    @Override // QuestionListAdapter.this.context
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(QuestionListAdapter.this.context, Result.class);

                        Log.i("score2", "["+c+"]");
/*

                        //    intent.putExtra("exam",C);*/
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        String currentDateandTime = sdf.format(new Date());

                        SubjectModelClass subjectModelClass = SharedPrefManager.getInstance(context).getSubjects();
                        String subject = " ";
                        subject = subjectModelClass.getSub();
                      //  PName = user.getName();
                        intent.putExtra("subject",subject);



                        Log.i("subject", "["+subject+"]");
                        Log.i("score", "["+c+holder.textViewCorrectAns.getText().toString()+"]");
                        Toast.makeText(QuestionListAdapter.this.context, "Time Up !", LENGTH_LONG).show();

                        dialog.dismiss();

                        resultRegister(subject,currentDateandTime,c);
                        context.startActivity(intent);
                        ((Activity)context).finish();


                    }
                });


                AlertDialog dialoga = alertDialog.create();
                dialoga.show();



                //   finish();
            }
        };
        myHandler.postDelayed(myRunnable,10*1000);

//        Toast.makeText(context, gen, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {

      //  Toast.makeText(QuestionListAdapter.this.context,"selected" + c,Toast.LENGTH_SHORT).show();
        return questions.size();
    }

    class QuestionListViewHolder extends RecyclerView.ViewHolder {

        TextView textViewQus, textViewQusNo, textViewCorrectAns, examName;
        RadioGroup radioGroup;
        RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton;
        public  int corrtect = 0;
        TextView textView;
      public   String abc = "";
        public QuestionListViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewQusNo = itemView.findViewById(R.id.textViewQusNo);
            textViewQus = itemView.findViewById(R.id.textViewAQus1);
            radioGroup = itemView.findViewById(R.id.radioGroup1);
            radioButton1 = itemView.findViewById(R.id.optA11);
            radioButton2 = itemView.findViewById(R.id.optA12);
            radioButton3 = itemView.findViewById(R.id.optA13);
            radioButton4 = itemView.findViewById(R.id.optA14);
            textViewCorrectAns = itemView.findViewById(R.id.textViewCorrectAns);
           //int c = 0 ;



        }


   }

    public void resultRegister(String sub, String presentdate, int mask){
        String PName = "", PUsername = "";

        User user = SharedPrefManager.getInstance(context).getUser();

      /*  String a = "";
        a.equals(user.getName());*/

        PName = user.getName();
        PUsername = user.getUsername();

        final   String name = PName;
        final   String usermame = PUsername;
        final   String subject = sub;
        final   String date = presentdate;
        final   String score = String.valueOf(mask);



        class Resultshow extends AsyncTask<Void, Void, String> {


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
                        Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("result");

                        ResultModelClass resultModelClass = new ResultModelClass(
                                userJson.getString("name"),
                                userJson.getString("username"),
                                userJson.getString("subject"),
                                userJson.getString("date"),
                                userJson.getString("score")
                        );


                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(context).resultLogin(resultModelClass);

                        //starting the profile activity
                        //      finish();
                    } else {
                        //Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();
                       // startActivity(new Intent(Subjects.this, Aptitude.class));


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
                params.put("username",usermame);
                params.put("subject",subject);
                params.put("date",date);
                params.put("score",score);

                //returing the response
                return requestHandler.sendPostRequest(Constants.URL_RESULT, params);
            }
        }

        //executing the async task
        Resultshow resultShow = new Resultshow();
        resultShow.execute();

    }
}

