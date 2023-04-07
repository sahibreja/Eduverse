package com.example.dsatutor.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsatutor.Model.Learning.SubChapter;
import com.example.dsatutor.Model.Sound;
import com.example.dsatutor.R;
import com.example.dsatutor.UI.Dashboard.Learning.WebViewActivity;
import com.example.dsatutor.UI.Dashboard.Learning.YoutubeActivity;

import java.util.ArrayList;

public class SubChapterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<SubChapter> subChapters;

    private Activity activity;
    public SubChapterAdapter(Context context,Activity activity, ArrayList<SubChapter> subChapters) {
        this.context = context;
        this.activity=activity;
        this.subChapters = subChapters;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.corse_content_layout_item,parent,false);
        return new FileLayoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FileLayoutHolder)holder).subchapterName.setText(subChapters.get(position).getSubChapterName());
        ((FileLayoutHolder)holder).levelName.setText(subChapters.get(position).getLevelName());
        SubChapter subChapter=subChapters.get(position);
        Sound sound= new Sound(context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to play the sound
                sound.playClickOnButtonSound();

                //calling Youtube activity for video
                Intent intent = new Intent(context, YoutubeActivity.class);
                intent.putExtra("videoId",subChapter.getVideoId());
                activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return subChapters.size();
    }
    static class FileLayoutHolder extends RecyclerView.ViewHolder{
        TextView subchapterName,levelName;
        public FileLayoutHolder(@NonNull View itemView) {
            super(itemView);
            subchapterName=itemView.findViewById(R.id.subChapterName);
            levelName=itemView.findViewById(R.id.levelName);
        }
    }
}
