package com.example.onlineexamination;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class FirstFragment extends Fragment {


    TextView name,textViewusername,subject,score,textViewShow;
    private List<ResultModelClass> resultModelClasses;
    private RecyclerView recyclerView;

int data = 1;
    View view;
    ImageView imageViewBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first, container, false);




// get the reference of Button
        recyclerView = view.findViewById(R.id.recyclerViewResultShowDelete);
        recyclerView.setHasFixedSize(true);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        resultModelClasses = new ArrayList<>();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {



               //     Toast.makeText(getActivity(), "Last", Toast.LENGTH_LONG).show();

                }
            }
        });

        textViewusername = (TextView)view.findViewById(R.id.textViewUsernameRShowDelete);


        imageViewBack = (ImageView)view.findViewById(R.id.imageViewBackShowDelete);


     //   requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);


// perform setOnClickListener on first Button
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// display a message by using a Toast
                Toast.makeText(getActivity(), "First Fragment", Toast.LENGTH_LONG).show();
                 Intent intent = new Intent(getActivity(),ResultShow.class);
                 startActivity(intent);
                 getActivity().finish();

            }
        });
        downloadJSON(Constants.URL_RESULT_TO_SHOW);

        User user = SharedPrefManager.getInstance(getActivity()).getUser();



        //    name.setText(resultModelClass.getName());
        textViewusername.setText(user.getUsername());

        return view;
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
        for (i = 0; i < jsonArray.length(); i++) {

            JSONObject obj = jsonArray.getJSONObject(i);

//            Log.i("tagconvertstraaan", "["+obj.getString(String.valueOf(stocks))+"]");
            int a = 0;
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
        }

    /*    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stocks);
        listView.setAdapter(arrayAdapter);*/

        ResultShowDeleteAdapter adapter = new ResultShowDeleteAdapter(resultModelClasses, getActivity());
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

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.getActivity().finish();
        } else {
            getActivity().finish();
            startActivity(getActivity().getIntent());
            getFragmentManager().popBackStack();
        }
    }



}
