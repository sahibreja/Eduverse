package com.example.dsatutor.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsatutor.Model.Compare;
import com.example.dsatutor.Model.ModelClass.Users;
import com.example.dsatutor.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class LeaderBoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private ArrayList<Users> usersArrayList;
    private FirebaseAuth auth;

    public LeaderBoardAdapter(Context context, ArrayList<Users> usersArrayList) {
        this.context = context;
        this.usersArrayList = usersArrayList;
    }

    public LeaderBoardAdapter(Context context, ArrayList<Users> usersArrayList, FirebaseAuth auth) {
        this.context = context;
        this.usersArrayList = usersArrayList;
        this.auth = auth;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.leaderboard_item,parent,false);
        return new FileLayoutHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Collections.sort(usersArrayList, new Compare());


        if(getItemViewType(position)==0)
        {
            if(usersArrayList.get(position).getUserId().equals(Objects.requireNonNull(auth.getCurrentUser()).getUid()))
            {
                ((FileLayoutHolder)holder).background.setBackground(context.getDrawable(R.drawable.leaderboard_own));
            }else
            {
                ((FileLayoutHolder)holder).background.setBackground(context.getDrawable(R.drawable.leaderboard_top));
            }
        }
        if(getItemViewType(position)==1)
        {
            if(usersArrayList.get(position).getUserId().equals(Objects.requireNonNull(auth.getCurrentUser()).getUid()))
            {
                ((FileLayoutHolder)holder).background.setBackground(context.getDrawable(R.drawable.leaderboard_own));
            }else
            {
                ((FileLayoutHolder)holder).background.setBackground(context.getDrawable(R.drawable.leaderboard_2nd));
            }
        }else if(usersArrayList.get(position).getUserId().equals(Objects.requireNonNull(auth.getCurrentUser()).getUid()))
        {
            ((FileLayoutHolder)holder).background.setBackground(context.getDrawable(R.drawable.leaderboard_own));
        }
        ((FileLayoutHolder)holder).rankName.setText(String.valueOf(position+1));
        ((FileLayoutHolder)holder).userName.setText(usersArrayList.get(position).getUserName());
        ((FileLayoutHolder)holder).score.setText(String.valueOf(usersArrayList.get(position).getTotalScore()));


    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    static class FileLayoutHolder extends RecyclerView.ViewHolder{
        LinearLayout background;
        TextView rankName,userName,score;

        public FileLayoutHolder(@NonNull View itemView) {
            super(itemView);
            background=itemView.findViewById(R.id.background);
            rankName=itemView.findViewById(R.id.rank_name);
            userName=itemView.findViewById(R.id.user_name);
            score=itemView.findViewById(R.id.score);
        }
    }
}
