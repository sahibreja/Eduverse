package com.example.dsatutor.UI.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsatutor.Model.PreRequisiteQuestion;
import com.example.dsatutor.R;

import java.util.ArrayList;


public class PreReqQuesFragment extends Fragment {


    private ArrayList<PreRequisiteQuestion> questionArrayList;
    private int index = 0;
    int correctAnswers = 0;
    private PreRequisiteQuestion questions;

    private TextView questionCat,questionCounter,question_text,option_1_txt,option_2_txt,option_3_txt,option_4_txt;
    private Button nextButton,previousButton;
    private boolean isClicked=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pre_req_ques, container, false);
        init(view);
        AddQuestion();

        setNextQuestion();
        ButtonClick();

       return view;
    }

    private void ButtonClick() {

        option_1_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isClicked)
                {
                    checkAnswer(option_1_txt);
                    isClicked=true;
                }
            }
        });
        option_2_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isClicked)
                {
                    checkAnswer(option_2_txt);
                    isClicked=true;
                }

            }
        });
        option_3_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isClicked)
                {
                    checkAnswer(option_3_txt);
                    isClicked=true;
                }
            }
        });
        option_4_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isClicked)
                {
                    checkAnswer(option_4_txt);
                    isClicked=true;
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                if(index<questionArrayList.size()) {
                    index++;
                    setNextQuestion();
                    if(index==questionArrayList.size()-1)
                    {
                        index++;
                        nextButton.setText("Finish");
                    }

                } else {
                    Toast.makeText(getContext(), "Correct Question's Answer is "+correctAnswers, Toast.LENGTH_SHORT).show();
                    correctAnswers=0;
                    index=0;
                    nextButton.setText("Next question");
                    setNextQuestion();
                }
            }
        });
    }

    private void AddQuestion()
    {
        questionArrayList=new ArrayList<>();
        questionArrayList.add(new PreRequisiteQuestion("1",
                "Variable",
                "Which of the following is true for variable names in C?",
                "Variable names cannot start with a digit",
                "Variable can be of any length",
                "They can contain alphanumeric characters as well as special characters",
                "Reserved Word can be used as variable name",
                "Variable names cannot start with a digit"
        ));
        questionArrayList.add(new PreRequisiteQuestion("2",
                "Array",
                "Array is ______ datatype in C Programming language.",
                "Derived Data type",
                "Primitive Data type",
                "Custom Data type",
                "None of these",
                "Derived Data type"));

        questionArrayList.add(new PreRequisiteQuestion("3",
                "Loop's Question",
                "Choose correct C while loop syntax.",
                "while(condition)\n" +
                        "{\n" +
                        "    //statements\n" +
                        "}",
                "{\n" +
                        "    //statements\n" +
                        "}while(condition)",
                "while(condition);\n" +
                        "{\n" +
                        "    //statements\n" +
                        "}",
                "while()\n" +
                        "{\n" +
                        "    if(condition)\n" +
                        "    {\n" +
                        "        //statements\n" +
                        "    }\n" +
                        "}",
                "while(condition)\n" +
                        "{\n" +
                        "    //statements\n" +
                        "}"));
    }
    private void init(View view)
    {
        questionCat=view.findViewById(R.id.question_cat);
        questionCounter=view.findViewById(R.id.question_no);
        question_text=view.findViewById(R.id.question_txt);
        option_1_txt=view.findViewById(R.id.option_1_txt);
        option_2_txt=view.findViewById(R.id.option_2_txt);
        option_3_txt=view.findViewById(R.id.option_3_txt);
        option_4_txt=view.findViewById(R.id.option_4_txt);

        nextButton =view.findViewById(R.id.nextBtn);
        previousButton =view.findViewById(R.id.pre_Btn);
    }

    private void setNextQuestion() {

        if(index < questionArrayList.size()) {
            questionCounter.setText(String.format("%d/%d", (index+1), questionArrayList.size()));
            questions = questionArrayList.get(index);
            questionCat.setText(questions.getQuestionCat());
            question_text.setText(questions.getQuestion());
            option_1_txt.setText(questions.getOption1());
            option_2_txt.setText(questions.getOption2());
            option_3_txt.setText(questions.getOption3());
            option_4_txt.setText(questions.getOption4());
        }else {

        }
    }

    private void checkAnswer(TextView selected) {
        String selectedAnswer = selected.getText().toString();
        if(selectedAnswer.equals(questions.getAnswer())) {
            correctAnswers++;
            selected.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_selected_bg));
        } else {
            showAnswer();
            selected.setBackground(getResources().getDrawable(R.drawable.pre_req_wrong_opt));
        }
    }

    private void showAnswer() {
        if(questions.getAnswer().equals(option_1_txt.getText().toString()))
            option_1_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_selected_bg));
        else if(questions.getAnswer().equals(option_2_txt.getText().toString()))
            option_2_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_selected_bg));
        else if(questions.getAnswer().equals(option_3_txt.getText().toString()))
            option_3_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_selected_bg));
        else if(questions.getAnswer().equals(option_4_txt.getText().toString()))
            option_4_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_selected_bg));
    }
    void reset() {
        isClicked=false;
        option_1_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_bg));
        option_2_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_bg));
        option_3_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_bg));
        option_4_txt.setBackground(getResources().getDrawable(R.drawable.pre_req_que_opt_bg));
    }

}