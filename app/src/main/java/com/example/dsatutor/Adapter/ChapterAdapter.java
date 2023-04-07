package com.example.dsatutor.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsatutor.Model.Learning.Chapter;
import com.example.dsatutor.Model.Sound;
import com.example.dsatutor.R;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<Chapter> chapters;
    private RecyclerView recyclerView;
    private SubChapterAdapter subChapterAdapter;
    private int selectedItemPosition = -1;

    public ChapterAdapter(Context context,Activity activity, ArrayList<Chapter> chapters,RecyclerView recyclerView) {
        this.context = context;
        this.activity=activity;
        this.chapters = chapters;
        this.recyclerView=recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_layout_item,parent,false);

        return new FileLayoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Sound sound = new Sound(context);
        ((FileLayoutHolder)holder).chapterName.setText(chapters.get(position).getChapterName());
        Chapter chapter=chapters.get(position);

        if (position == selectedItemPosition) {
            ((FileLayoutHolder)holder).cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.selected_color));
        } else {
            ((FileLayoutHolder)holder).cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.default_color));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to play the sound
                sound.playClickOnButtonSound();


                selectedItemPosition = holder.getAdapterPosition();

                //call the recycler view
                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemViewCacheSize(10);
                recyclerView.setDrawingCacheEnabled(true);
                recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                recyclerView.setNestedScrollingEnabled(true);
                subChapterAdapter= new SubChapterAdapter(context,activity,chapter.getSubChapterArrayList());
                recyclerView.setAdapter(subChapterAdapter);
                recyclerView.scrollToPosition(0);
                subChapterAdapter.notifyDataSetChanged();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapters.size();
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
        TextView chapterName;
        CardView cardView;
        public FileLayoutHolder(@NonNull View itemView) {
            super(itemView);
            chapterName=itemView.findViewById(R.id.chapter_name);
            cardView=itemView.findViewById(R.id.bg);
        }
    }
}
