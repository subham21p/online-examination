package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.onlineexamination.C.i;

public class ResultShow extends AppCompatActivity {

    TextView name,textViewusername,subject,score,textViewShow;
    private List<ResultModelClass> resultModelClasses;
    Button button;
    ImageView imageViewBackShow;
  //  FloatingActionButton button;
    FrameLayout frameLayout;
    //the recyclerview
    private RecyclerView recyclerView;
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_show);

//    listView = (ListView)findViewById(R.id.listViewShow);
        recyclerView = findViewById(R.id.recyclerViewResultShow);
        recyclerView.setHasFixedSize(true);
      //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setVisibility(View.VISIBLE);
        resultModelClasses = new ArrayList<>();

        imageViewBackShow = (ImageView)findViewById(R.id.imageViewBackShow);
        imageViewBackShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        button = (Button) findViewById(R.id.button4);
        button.setVisibility(View.VISIBLE);
        textViewusername = (TextView)findViewById(R.id.textViewUsernameRShow);
        textViewShow = (TextView)findViewById(R.id.textViewShow);
        textViewShow.setVisibility(View.INVISIBLE);


       User user = SharedPrefManager.getInstance(this).getUser();



    //    name.setText(resultModelClass.getName());
        textViewusername.setText(user.getUsername());

       downloadJSON(Constants.URL_RESULT_TO_SHOW);


       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
// load First Fragment
              // loadFragment(new FirstFragment());
               showMyFragment(v);

               button.setVisibility(View.INVISIBLE);
               recyclerView.setVisibility(View.INVISIBLE);

               Snackbar.make(v, "Delete You Results", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();
           }
       });



    }






    private void downloadJSON(final String urlWebService) {

        final String username = textViewusername.getText().toString().trim();

        class DownloadJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            protected String doInBackground(Void... voids) {

                try {

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
        Log.i("tagconvertstr", "["+json+"]");
        JSONArray jsonArray = new JSONArray(json);
        String[] stocks = new String[jsonArray.length()];
        int a = 0;
        for (i = 0; i < jsonArray.length(); i++) {

            JSONObject obj = jsonArray.getJSONObject(i);

            Log.i("tagconvertstraaan", "["+stocks[i]+"]");

            if (obj.getString("username").equals(textViewusername.getText().toString().trim())){
                Log.i("tagconvertstraaam", "["+stocks[i]+"]");
                resultModelClasses.add(new ResultModelClass(
                        obj.getString("name"),
                        obj.getString("username"),
                        obj.getString("subject"),
                        obj.getString("date"),
                        obj.getString("score")

                ));
                a++;
            }

            Log.i("tagconvertstraaamC", "["+a+"]");


            if (a==0){
                textViewShow.setVisibility(View.VISIBLE);
                button.setVisibility(View.INVISIBLE);
            }
            else {
                textViewShow.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);

            }

        }
    /*    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stocks);
        listView.setAdapter(arrayAdapter);*/

       ResultShowAdapter adapter = new ResultShowAdapter(resultModelClasses, ResultShow.this);
        recyclerView.setAdapter(adapter);

    }



    public void showMyFragment(View V){
        Fragment fragment = null;
        fragment = new FirstFragment();

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).addToBackStack(null).commit();





        }
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            finish();
            startActivity(getIntent());
            getFragmentManager().popBackStack();
        }
    }
}