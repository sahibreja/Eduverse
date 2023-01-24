package com.example.dsatutor.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsatutor.Model.PreRequisiteQuestion;
import com.example.dsatutor.R;

import java.util.ArrayList;

public class PreRequisiteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<PreRequisiteQuestion> questions;

    private boolean isSelected=false;

    private String selectedOption="";

    private int index=0;

    private int score=0;

    public PreRequisiteAdapter() {
    }

    public PreRequisiteAdapter(Context mContext, ArrayList<PreRequisiteQuestion> questions) {
        this.mContext = mContext;
        this.questions = questions;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.question_layout,parent,false);

        return new FileLayoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {




        ((FileLayoutHolder)holder).question_cat.setText(questions.get(position).getQuestionCat());
        ((FileLayoutHolder)holder).question_no.setText((position+1)+"/"+questions.size());
        ((FileLayoutHolder)holder).question.setText(questions.get(position).getQuestion());
        ((FileLayoutHolder)holder).option_1_txt.setText(questions.get(position).getOption1());
        ((FileLayoutHolder)holder).option_2_txt.setText(questions.get(position).getOption2());
        ((FileLayoutHolder)holder).option_3_txt.setText(questions.get(position).getOption3());
        ((FileLayoutHolder)holder).option_4_txt.setText(questions.get(position).getOption4());

        ((FileLayoutHolder)holder).option_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelected=true;
                selectedOption=((FileLayoutHolder)holder).option_1_txt.getText().toString();
                Resources res = mContext.getResources(); //resource handle
                Drawable drawable1 = res.getDrawable(R.drawable.pre_req_que_opt_selected_bg);
                Drawable drawable2 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                Drawable drawable3 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                Drawable drawable4 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                ((FileLayoutHolder)holder).option_1.setBackground(drawable1);
                ((FileLayoutHolder)holder).option_2.setBackground(drawable2);
                ((FileLayoutHolder)holder).option_3.setBackground(drawable3);
                ((FileLayoutHolder)holder).option_4.setBackground(drawable4);
            }
        });

        ((FileLayoutHolder)holder).option_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelected=true;
                selectedOption=((FileLayoutHolder)holder).option_2_txt.getText().toString();
                Resources res = mContext.getResources(); //resource handle
                Drawable drawable2 = res.getDrawable(R.drawable.pre_req_que_opt_selected_bg);
                Drawable drawable1 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                Drawable drawable3 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                Drawable drawable4 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                ((FileLayoutHolder)holder).option_1.setBackground(drawable1);
                ((FileLayoutHolder)holder).option_2.setBackground(drawable2);
                ((FileLayoutHolder)holder).option_3.setBackground(drawable3);
                ((FileLayoutHolder)holder).option_4.setBackground(drawable4);
            }
        });

        ((FileLayoutHolder)holder).option_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelected=true;
                selectedOption=((FileLayoutHolder)holder).option_3_txt.getText().toString();
                Resources res = mContext.getResources(); //resource handle
                Drawable drawable3 = res.getDrawable(R.drawable.pre_req_que_opt_selected_bg);
                Drawable drawable2 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                Drawable drawable1 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                Drawable drawable4 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                ((FileLayoutHolder)holder).option_1.setBackground(drawable1);
                ((FileLayoutHolder)holder).option_2.setBackground(drawable2);
                ((FileLayoutHolder)holder).option_3.setBackground(drawable3);
                ((FileLayoutHolder)holder).option_4.setBackground(drawable4);
            }
        });

        ((FileLayoutHolder)holder).option_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelected=true;
                selectedOption=((FileLayoutHolder)holder).option_4_txt.getText().toString();
                Resources res = mContext.getResources(); //resource handle
                Drawable drawable4 = res.getDrawable(R.drawable.pre_req_que_opt_selected_bg);
                Drawable drawable2 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                Drawable drawable3 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                Drawable drawable1 = res.getDrawable(R.drawable.pre_req_que_opt_bg);
                ((FileLayoutHolder)holder).option_1.setBackground(drawable1);
                ((FileLayoutHolder)holder).option_2.setBackground(drawable2);
                ((FileLayoutHolder)holder).option_3.setBackground(drawable3);
                ((FileLayoutHolder)holder).option_4.setBackground(drawable4);
            }
        });


//        ((FileLayoutHolder)holder).next_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(position==questions.size()-1)
//                {
//                    ((FileLayoutHolder)holder).next_btn.setText("Finish");
//
//                }else
//                {
//
//                    index++;
//                }
//            }
//        });
//
//        ((FileLayoutHolder)holder).previous_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(position==0)
//                {
//                    ((FileLayoutHolder)holder).previous_btn.setVisibility(View.GONE);
//                }else
//                {
//
//                    index--;
//                }
//            }
//        });






    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    static class FileLayoutHolder extends RecyclerView.ViewHolder{


        TextView question_cat,question_no,question,option_1_txt,option_2_txt,option_3_txt,option_4_txt;
        Button next_btn,previous_btn;
        RelativeLayout option_1,option_2,option_3,option_4;

        public FileLayoutHolder(@NonNull View itemView) {
            super(itemView);
            question_cat=itemView.findViewById(R.id.question_cat);
            question_no=itemView.findViewById(R.id.question_no);
            question=itemView.findViewById(R.id.question_txt);
            option_1_txt=itemView.findViewById(R.id.option_1_txt);
            option_2_txt=itemView.findViewById(R.id.option_2_txt);
            option_3_txt=itemView.findViewById(R.id.option_3_txt);
            option_4_txt=itemView.findViewById(R.id.option_4_txt);

            option_1=itemView.findViewById(R.id.option_1);
            option_2=itemView.findViewById(R.id.option_2);
            option_3=itemView.findViewById(R.id.option_3);
            option_4=itemView.findViewById(R.id.option_4);



        }
    }
}
