package com.example.dsatutor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsatutor.Model.ModelClass.HelpQuestion;
import com.example.dsatutor.R;

import java.util.ArrayList;

public class HelpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<HelpQuestion> helpQuestions;

    public HelpAdapter(Context context, ArrayList<HelpQuestion> helpQuestions) {
        this.context = context;
        this.helpQuestions = helpQuestions;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.help_item,parent,false);
        return new FileLayoutHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           HelpQuestion helpQuestion= helpQuestions.get(position);
        ((FileLayoutHolder)holder).question.setText(helpQuestion.getQuestion());
    }

    @Override
    public int getItemCount() {
        return helpQuestions.size();
    }

    static class FileLayoutHolder extends RecyclerView.ViewHolder{
        TextView question;
        public FileLayoutHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.question);
        }
    }
}
