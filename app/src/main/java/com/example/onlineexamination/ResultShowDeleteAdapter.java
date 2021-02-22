package com.example.onlineexamination;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class ResultShowDeleteAdapter extends RecyclerView.Adapter<ResultShowDeleteAdapter.ResultShowDeleteViewHolder>{

    private List<ResultModelClass> modelClasses;
    private Context context;

    public ResultShowDeleteAdapter(List<ResultModelClass> resultModelClasses, Context ctx) {
        modelClasses = resultModelClasses;
        context = ctx;


    }
    @NonNull
    @Override
    public ResultShowDeleteAdapter.ResultShowDeleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_show_delete_recyclerview, parent, false);
        return new ResultShowDeleteAdapter.ResultShowDeleteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultShowDeleteAdapter.ResultShowDeleteViewHolder holder, int position) {

        ResultModelClass resultModelClass = modelClasses.get(position);

        holder.textViewName.setText(resultModelClass.getName());
        holder.textViewUsername.setText(resultModelClass.getUsername());
        holder.textViewSubject.setText(resultModelClass.getSubject());
        holder.textViewDate.setText(resultModelClass.getDate());
        holder.textViewScore.setText(resultModelClass.getScore());

        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*((Activity) context).finish();

                Intent intent = ((Activity) context).getIntent();
                ((Activity) context).setResult(((Activity) context).RESULT_OK,
                        intent);
*/


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("OnlineExamination");
                alertDialog.setMessage("Do you want to delete "+holder.textViewSubject.getText().toString().trim()+" exam result ?");
                //alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resultDelete(holder.textViewUsername.getText().toString().trim(),holder.textViewSubject.getText().toString().trim());

                        //   Toast.makeText(QuestionListAdapter.this.context, "Time Up !", LENGTH_LONG).show();
                        modelClasses.remove(position);
                        notifyDataSetChanged();

                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       /* Intent intent = new Intent(context,FirstFragment.class);
                        context.startActivity(intent);*/
                        // DO SOMETHING HERE
                       // Toast.makeText(ResultShowDeleteAdapter.this.context, "Time Up !", LENGTH_LONG).show();

                       /* ((ResultShow)context).finish();
                        context.startActivity(((ResultShow)context).getIntent());*/
                        notifyDataSetChanged();

                        dialog.cancel();

                   //     showMyFragment(v);



                       // showMyFragment(v);
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();














            }
        });
    }

    @Override
    public int getItemCount() {
        return  modelClasses.size();
    }








    public class ResultShowDeleteViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewSubject, textViewUsername, textViewDate, textViewScore;
        ImageView imageViewDelete;

        public ResultShowDeleteViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewNameShowDelete);
            textViewSubject = itemView.findViewById(R.id.textViewSubjectShowDelete);
            textViewUsername = itemView.findViewById(R.id.textViewUsernameShowDelete);
            textViewDate = itemView.findViewById(R.id.textViewDateShowDelete);
            textViewScore = itemView.findViewById(R.id.textViewScoreShowDelete);
            imageViewDelete = itemView.findViewById(R.id.imageViewShowDelete);

        }
    }

    private void resultDelete(String username, String subject){

        class UserLogin extends AsyncTask<Void, Void, String> {

            // ProgressBar progressBar;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // progressBar = (ProgressBar) findViewById(R.id.progressBar2);
              //  progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
           //     progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response


                        //storing the user in shared preferences
                      //  SharedPrefManager.getInstance(context).userLogin(user);
                       /* SharedPrefManager sharedPrefManager = (SharedPrefManager) context.getSharedPreferences("simplifiedcodingsharedprefa", Context.MODE_PRIVATE);
                        sharedPrefManager.getClass().clear().commit();*/
                       // context.getSharedPreferences("simplifiedcodingsharedprefa", 0).edit().clear().commit();
                        //starting the profile activity
                        //this.finish();
                       // context.startActivity(new Intent(context, Subjects.class));
                    } else {
                        Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show();
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
                return requestHandler.sendPostRequest(Constants.URL_RESULT_TO_SHOW_DELETE, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();


    }

    public void showMyFragment(View V){
        Fragment fragment = null;
        fragment = new FirstFragment();

        if (fragment != null) {
            FragmentManager fragmentManager = fragment.getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).addToBackStack(null).commit();





        }
    }

    public void onBackPressed() {
        Fragment fragment = null;
        fragment = new FirstFragment();
        if (fragment.getFragmentManager().getBackStackEntryCount() == 0) {
            fragment.getActivity().finish();
        } else {
            fragment.getActivity().finish();
            fragment.getActivity().getIntent();
            fragment.getFragmentManager().popBackStack();
        }
    }


}
