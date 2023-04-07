package com.example.dsatutor.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsatutor.Model.ModelClass.Level;
import com.example.dsatutor.Model.Levels.LevelCreate;
import com.example.dsatutor.Model.Sound;
import com.example.dsatutor.R;
import com.example.dsatutor.UI.Game.QuizActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LevelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private Sound sound;
    private Activity activity;
    private ArrayList<LevelCreate> levelCreateArrayList;

    private FirebaseDatabase database;

    private FirebaseAuth auth;

    private int lives;
    private int currentLevel;
    private Animation floatAnimation,floatAnimation1,floatAnimation2;
    private ArrayList<Level> levelArrayList=new ArrayList<>();

    public LevelAdapter(Context context, ArrayList<LevelCreate> levelCreateArrayList) {
        this.context = context;
        this.levelCreateArrayList = levelCreateArrayList;

    }

    public LevelAdapter(Activity activity,Context context, ArrayList<LevelCreate> levelCreateArrayList, int lives, int currentLevel, ArrayList<Level> levelArrayList) {
        this.activity=activity;
        this.context = context;
        this.levelCreateArrayList = levelCreateArrayList;
        this.lives = lives;
        this.currentLevel = currentLevel;
        this.levelArrayList = levelArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.level_layout,parent,false);

        return new FileLayoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        sound= new Sound(context);
        ((FileLayoutHolder)holder).background.setImageDrawable(levelCreateArrayList.get(position).getBackground());
        ((FileLayoutHolder)holder).levelTxt1.setText(String.valueOf((position*6)+1));
        ((FileLayoutHolder)holder).levelTxt2.setText(String.valueOf((position*6)+2));
        ((FileLayoutHolder)holder).levelTxt3.setText(String.valueOf((position*6)+3));
        ((FileLayoutHolder)holder).levelTxt4.setText(String.valueOf((position*6)+4));
        ((FileLayoutHolder)holder).levelTxt5.setText(String.valueOf((position*6)+5));
        ((FileLayoutHolder)holder).levelTxt6.setText(String.valueOf((position*6)+6));
         LevelClick(holder,getItemViewType(position));
        setLockView(holder,getItemViewType(position));
        setStarsToLevel(holder,getItemViewType(position));
        floatAnim(holder,getItemViewType(position));

    }

    //Function of set the stars to the level
    private void setStarsToLevel(@NonNull RecyclerView.ViewHolder holder,int position) {

        if(currentLevel>1)
        {
            for(int i=0;i<levelArrayList.size();i++)
            {
               if((i+1)==(position*6)+1) {
                   ((FileLayoutHolder)holder).star_layout_1.setVisibility(View.VISIBLE);
                   if(levelArrayList.get(i).getLevelStars()==1)
                   {
                       ((FileLayoutHolder)holder).level1_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==2)
                   {
                       ((FileLayoutHolder)holder).level1_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level1_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==3)
                   {
                       ((FileLayoutHolder)holder).level1_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level1_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                       ((FileLayoutHolder)holder).level1_stars3.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }
               }else if((i+1)==(position*6)+2)
               {
                   ((FileLayoutHolder)holder).star_layout_2.setVisibility(View.VISIBLE);
                   if(levelArrayList.get(i).getLevelStars()==1)
                   {
                       ((FileLayoutHolder)holder).level2_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==2)
                   {
                       ((FileLayoutHolder)holder).level2_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level2_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==3)
                   {
                       ((FileLayoutHolder)holder).level2_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level2_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                       ((FileLayoutHolder)holder).level2_stars3.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }
               }else if((i+1)==(position*6)+3)
               {
                   ((FileLayoutHolder)holder).star_layout_3.setVisibility(View.VISIBLE);
                   if(levelArrayList.get(i).getLevelStars()==1)
                   {
                       ((FileLayoutHolder)holder).level3_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==2)
                   {
                       ((FileLayoutHolder)holder).level3_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level3_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==3)
                   {
                       ((FileLayoutHolder)holder).level3_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level3_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                       ((FileLayoutHolder)holder).level3_stars3.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }

               }else if((i+1)==(position*6)+4)
               {
                   ((FileLayoutHolder)holder).star_layout_4.setVisibility(View.VISIBLE);
                   if(levelArrayList.get(i).getLevelStars()==1)
                   {
                       ((FileLayoutHolder)holder).level4_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==2)
                   {
                       ((FileLayoutHolder)holder).level4_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level4_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==3)
                   {
                       ((FileLayoutHolder)holder).level4_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level4_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                       ((FileLayoutHolder)holder).level4_stars3.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }

               }else if((i+1)==(position*6)+5)
               {
                   ((FileLayoutHolder)holder).star_layout_5.setVisibility(View.VISIBLE);

                   if(levelArrayList.get(i).getLevelStars()==1)
                   {
                       ((FileLayoutHolder)holder).level5_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==2)
                   {
                       ((FileLayoutHolder)holder).level5_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level5_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==3)
                   {
                       ((FileLayoutHolder)holder).level5_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level5_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                       ((FileLayoutHolder)holder).level5_stars3.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }

               } else if((i+1)==(position*6)+6)
               {
                   ((FileLayoutHolder)holder).star_layout_6.setVisibility(View.VISIBLE);

                   if(levelArrayList.get(i).getLevelStars()==1)
                   {
                       ((FileLayoutHolder)holder).level6_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==2)
                   {
                       ((FileLayoutHolder)holder).level6_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level6_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                   }else if(levelArrayList.get(i).getLevelStars()==3)
                   {
                       ((FileLayoutHolder)holder).level6_stars1.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                       ((FileLayoutHolder)holder).level6_stars2.setImageDrawable(context.getResources().getDrawable(R.drawable.star2_fill));
                       ((FileLayoutHolder)holder).level6_stars3.setImageDrawable(context.getResources().getDrawable(R.drawable.star1_fill));
                   }
               }
            }
        }

    }

    //Function of click on level
    private void LevelClick(@NonNull RecyclerView.ViewHolder holder,int position) {



        ((FileLayoutHolder)holder).level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickOnLevelSound();
                if(lives>0 && currentLevel>=(position*6)+1)
                {
                    Intent intent= new Intent(context, QuizActivity.class);
                    intent.putExtra("level",(position*6)+1);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                }else
                {
                    if(currentLevel<(position*6)+1) {
                        Toast.makeText(context, "Planet is not open", Toast.LENGTH_SHORT).show();
                    }
                    else if(lives<=0)
                    {
                        Toast.makeText(context, "Health is reviving..", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        ((FileLayoutHolder)holder).level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickOnLevelSound();
                if(lives>0 && currentLevel>=(position*6)+2)
                {
                    Intent intent= new Intent(context, QuizActivity.class);
                    intent.putExtra("level",(position*6)+2);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                }else
                {
                    if(currentLevel<(position*6)+2) {
                        Toast.makeText(context, "Planet is not open", Toast.LENGTH_SHORT).show();
                    }
                    else if(lives<=0)
                    {
                        Toast.makeText(context, "Health is reviving..", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        ((FileLayoutHolder)holder).level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickOnLevelSound();
                if(lives>0 && currentLevel>=(position*6)+3)
                {
                    Intent intent= new Intent(context, QuizActivity.class);
                    intent.putExtra("level",(position*6)+3);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                }else
                {
                    if(currentLevel<(position*6)+3) {
                        Toast.makeText(context, "Planet is not open", Toast.LENGTH_SHORT).show();
                    }
                    else if(lives<=0)
                    {
                        Toast.makeText(context, "Health is reviving..", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        ((FileLayoutHolder)holder).level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickOnLevelSound();
                if(lives>0 && currentLevel>=(position*6)+4)
                {
                    Intent intent= new Intent(context, QuizActivity.class);
                    intent.putExtra("level",(position*6)+4);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                }else
                {
                    if(currentLevel<(position*6)+4) {
                        Toast.makeText(context, "Planet is not open", Toast.LENGTH_SHORT).show();
                    }
                    else if(lives<=0)
                    {
                        Toast.makeText(context, "Health is reviving..", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        ((FileLayoutHolder)holder).level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickOnLevelSound();
                if(lives>0 && currentLevel>=(position*6)+5)
                {
                    Intent intent= new Intent(context, QuizActivity.class);
                    intent.putExtra("level",(position*6)+5);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                }else
                {
                    if(currentLevel<(position*6)+5) {
                        Toast.makeText(context, "Planet is not open", Toast.LENGTH_SHORT).show();
                    }
                    else if(lives<=0)
                    {
                        Toast.makeText(context, "Health is reviving..", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        ((FileLayoutHolder)holder).level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickOnLevelSound();
                if(lives>0 && currentLevel>=(position*6)+6)
                {
                    Intent intent= new Intent(context, QuizActivity.class);
                    intent.putExtra("level",(position*6)+6);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                }else
                {
                    if(currentLevel<(position*6)+6) {
                        Toast.makeText(context, "Planet is not open", Toast.LENGTH_SHORT).show();
                    }
                    else if(lives<=0)
                    {
                        Toast.makeText(context, "Health is reviving..", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    //function to animate like a floating of anything
    private void floatAnim(@NonNull RecyclerView.ViewHolder holder,int position){
        floatAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.float_anim);
        floatAnimation1 = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.float_anim1);
        floatAnimation2 = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.float_anim2);

            ((FileLayoutHolder)holder).level1.startAnimation(floatAnimation);
            ((FileLayoutHolder)holder).level2.startAnimation(floatAnimation1);
            ((FileLayoutHolder)holder).level3.startAnimation(floatAnimation2);
            ((FileLayoutHolder)holder).level4.startAnimation(floatAnimation1);
            ((FileLayoutHolder)holder).level5.startAnimation(floatAnimation);
            ((FileLayoutHolder)holder).level6.startAnimation(floatAnimation2);

    }

    //Function of set the lock and unlock value to the levels
    private void setLockView(@NonNull RecyclerView.ViewHolder holder,int position) {
        for(int i=(position*6)+1;i<=currentLevel;i++) {
            if(i%6==1) {
                ((FileLayoutHolder)holder).level1lock.setVisibility(View.GONE);
            }else if(i%6==2){
                ((FileLayoutHolder)holder).level1lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level2lock.setVisibility(View.GONE);
            }else if(i%6==3){
                ((FileLayoutHolder)holder).level1lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level2lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level3lock.setVisibility(View.GONE);
            }else if(i%6==4){
                ((FileLayoutHolder)holder).level1lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level2lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level3lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level4lock.setVisibility(View.GONE);
            }else if(i%6==5){
                ((FileLayoutHolder)holder).level1lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level2lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level3lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level4lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level5lock.setVisibility(View.GONE);
            }else if(i%6==0){
                ((FileLayoutHolder)holder).level1lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level2lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level3lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level4lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level5lock.setVisibility(View.GONE);
                ((FileLayoutHolder)holder).level6lock.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return levelCreateArrayList.size();
    }


    //Function of touch effect
    private void heartTouchEffect(View view) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale);
        view.startAnimation(anim);
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
        RelativeLayout level1,level2,level3,level4,level5,level6;
        TextView levelTxt1,levelTxt2,levelTxt3,levelTxt4,levelTxt5,levelTxt6;
        ImageView level1lock,level2lock,level3lock,level4lock,level5lock,level6lock;
        LinearLayout star_layout_1,star_layout_2,star_layout_3,star_layout_4,star_layout_5,star_layout_6;
        ImageView level1_stars1,level1_stars2,level1_stars3;
        ImageView level2_stars1,level2_stars2,level2_stars3;
        ImageView level3_stars1,level3_stars2,level3_stars3;
        ImageView level4_stars1,level4_stars2,level4_stars3;
        ImageView level5_stars1,level5_stars2,level5_stars3;
        ImageView level6_stars1,level6_stars2,level6_stars3;

        ImageView background;

        LinearLayout layout;
        public FileLayoutHolder(@NonNull View itemView) {
            super(itemView);

            level1=itemView.findViewById(R.id.level_1);
            level2=itemView.findViewById(R.id.level_2);
            level3=itemView.findViewById(R.id.level_3);
            level4=itemView.findViewById(R.id.level_4);
            level5=itemView.findViewById(R.id.level_5);
            level6=itemView.findViewById(R.id.level_6);

            levelTxt1=itemView.findViewById(R.id.level_txt0);
            levelTxt2=itemView.findViewById(R.id.level_txt1);
            levelTxt3=itemView.findViewById(R.id.level_txt2);
            levelTxt4=itemView.findViewById(R.id.level_txt3);
            levelTxt5=itemView.findViewById(R.id.level_txt4);
            levelTxt6=itemView.findViewById(R.id.level_txt5);

            level1lock=itemView.findViewById(R.id.level1_lock);
            level2lock=itemView.findViewById(R.id.level2_lock);
            level3lock=itemView.findViewById(R.id.level3_lock);
            level4lock=itemView.findViewById(R.id.level4_lock);
            level5lock=itemView.findViewById(R.id.level5_lock);
            level6lock=itemView.findViewById(R.id.level6_lock);


            star_layout_1=itemView.findViewById(R.id.star_layout_1);
            star_layout_2=itemView.findViewById(R.id.star_layout_2);
            star_layout_3=itemView.findViewById(R.id.star_layout_3);
            star_layout_4=itemView.findViewById(R.id.star_layout_4);
            star_layout_5=itemView.findViewById(R.id.star_layout_5);
            star_layout_6=itemView.findViewById(R.id.star_layout_6);

            level1_stars1=itemView.findViewById(R.id.level1_stars1); level1_stars2=itemView.findViewById(R.id.level1_stars2); level1_stars3=itemView.findViewById(R.id.level1_stars3);
            level2_stars1=itemView.findViewById(R.id.level2_stars1); level2_stars2=itemView.findViewById(R.id.level2_stars2); level2_stars3=itemView.findViewById(R.id.level2_stars3);
            level3_stars1=itemView.findViewById(R.id.level3_stars1); level3_stars2=itemView.findViewById(R.id.level3_stars2); level3_stars3=itemView.findViewById(R.id.level3_stars3);
            level4_stars1=itemView.findViewById(R.id.level4_stars1); level4_stars2=itemView.findViewById(R.id.level4_stars2); level4_stars3=itemView.findViewById(R.id.level4_stars3);
            level5_stars1=itemView.findViewById(R.id.level5_stars1); level5_stars2=itemView.findViewById(R.id.level5_stars2); level5_stars3=itemView.findViewById(R.id.level5_stars3);
            level6_stars1=itemView.findViewById(R.id.level6_stars1); level6_stars2=itemView.findViewById(R.id.level6_stars2); level6_stars3=itemView.findViewById(R.id.level6_stars3);


            background=itemView.findViewById(R.id.background);

            layout=itemView.findViewById(R.id.v);



        }



    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ((FileLayoutHolder)holder).level1.startAnimation(floatAnimation);
        ((FileLayoutHolder)holder).level2.startAnimation(floatAnimation1);
        ((FileLayoutHolder)holder).level3.startAnimation(floatAnimation2);
        ((FileLayoutHolder)holder).level4.startAnimation(floatAnimation1);
        ((FileLayoutHolder)holder).level5.startAnimation(floatAnimation);
        ((FileLayoutHolder)holder).level6.startAnimation(floatAnimation2);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        ((FileLayoutHolder)holder).level1.clearAnimation();
        ((FileLayoutHolder)holder).level2.clearAnimation();
        ((FileLayoutHolder)holder).level3.clearAnimation();
        ((FileLayoutHolder)holder).level4.clearAnimation();
        ((FileLayoutHolder)holder).level5.clearAnimation();
        ((FileLayoutHolder)holder).level6.clearAnimation();
    }
}
