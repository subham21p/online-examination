package com.example.onlineexamination;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class ResultShowAdapter extends RecyclerView.Adapter<ResultShowAdapter.ResultShowViewHolder> {

    private List<ResultModelClass> modelClasses;
    private Context context;

    public ResultShowAdapter(List<ResultModelClass> resultModelClasses, Context ctx) {
        modelClasses = resultModelClasses;
        context = ctx;
    }

    @NonNull
    @Override
    public ResultShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_show_recyclerview, parent, false);

        return new ResultShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultShowViewHolder holder, int position) {
        ResultModelClass resultModelClass = modelClasses.get(position);
String a = "pik";

      holder.textViewName.setText(resultModelClass.getName());
        holder.textViewUsername.setText(resultModelClass.getUsername());
        holder.textViewSubject.setText(resultModelClass.getSubject());
        holder.textViewDate.setText(resultModelClass.getDate());
        holder.textViewScore.setText(resultModelClass.getScore());
      //  Log.i("tagconvertstrabcd", String.valueOf(holder));
        Log.i("tagconvertstrqw", "["+holder.textViewUsername+"]");


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultShowAdapter.this.context, Result.class);
                  intent.putExtra("subject",holder.textViewSubject.getText().toString().trim());
               // intent.putExtra("exam",C);

                Toast.makeText(ResultShowAdapter.this.context, "Time Up !", LENGTH_LONG).show();

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

     class ResultShowViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewSubject, textViewUsername, textViewDate, textViewScore;
        LinearLayout linearLayout;

        public ResultShowViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewNameShow);
            textViewSubject = itemView.findViewById(R.id.textViewSubjectShow);
            textViewUsername = itemView.findViewById(R.id.textViewUsernameShow);
            textViewDate = itemView.findViewById(R.id.textViewDateShow);
            textViewScore = itemView.findViewById(R.id.textViewScoreShow);
            linearLayout = itemView.findViewById(R.id.linearLayout);

        }
    }

}
