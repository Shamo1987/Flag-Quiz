package com.info.flagquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewTrue,textViewFel,textViewQuestionNumber;
    private ImageView imageViewFlag;
    private Button buttonA,buttonB,buttonC,buttonD;
    private ArrayList<Flags> questions;
    private ArrayList<Flags> felSelect;
    private Flags trueQuestion;
    private myDatabase vt;
    //Fråga counter
    private int frogaNumber = 0 ;
    private int trueCounter = 0;
    private int felCounter = 0;

    private ArrayList<Flags> options = new ArrayList<>();
    private HashSet<Flags> mixingOptionsList = new HashSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewTrue = findViewById(R.id.textViewRatt);
        textViewFel = findViewById(R.id.textViewFel);
        textViewQuestionNumber = findViewById(R.id.textViewFrogaNumber);
        imageViewFlag = findViewById(R.id.imageViewFlag);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

        vt = new myDatabase(this);

        questions = new FlagsDao().random5get(vt);

        loadQuestion();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rattControl(buttonA);
                frogaOptionsControl();
                Log.e("OPTION",String.valueOf(frogaNumber));
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rattControl(buttonB);
                frogaOptionsControl();
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rattControl(buttonC);
                frogaOptionsControl();
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rattControl(buttonD);
                frogaOptionsControl();
            }
        });
    }

    public void loadQuestion(){
        textViewQuestionNumber.setText((frogaNumber+1)+". FRÅGA");
        textViewTrue.setText("Rätt : "+(trueCounter));
        textViewFel.setText("Fel : "+(felCounter));

        trueQuestion = questions.get(frogaNumber);

        felSelect = new FlagsDao().random3FelSelectGet(vt,trueQuestion.getFlag_id());

        imageViewFlag.setImageResource(getResources().getIdentifier(trueQuestion.getFlag_photo()
                ,"drawable",getPackageName()));


        mixingOptionsList.clear();
        mixingOptionsList.add(trueQuestion);
        mixingOptionsList.add(felSelect.get(0));
        mixingOptionsList.add(felSelect.get(1));
        mixingOptionsList.add(felSelect.get(2));


        options.clear();

        for(Flags b: mixingOptionsList){
            options.add(b);
        }

        buttonA.setText(options.get(0).getFlag_name());
        buttonB.setText(options.get(1).getFlag_name());
        buttonC.setText(options.get(2).getFlag_name());
        buttonD.setText(options.get(3).getFlag_name());

    }

    public void rattControl(Button button){

            String buttonWrite = button.getText().toString();
            String trueAnswer = trueQuestion.getFlag_name();
            Log.e("Ratt",trueAnswer);
            Log.e("ButtonYazi",buttonWrite);

            if(buttonWrite.equals(trueAnswer)){
                trueCounter++;
            }else{
                felCounter++;
            }

        textViewTrue.setText("Ratt : "+(trueCounter));
        textViewFel.setText("Fel : "+(felCounter));
    }

    public void frogaOptionsControl(){

        frogaNumber++;


        if(frogaNumber != 5){
            loadQuestion();
        }else{
            Intent i = new Intent(QuizActivity.this,ResultActivity.class);
            i.putExtra("trueCounter",trueCounter);
            startActivity(i);
            finish();
        }
    }
}
