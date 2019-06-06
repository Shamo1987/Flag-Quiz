package com.info.flagquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView textViewResult,textViewAllResult;
    private Button buttonAgain;
    private int trueCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResult = findViewById(R.id.textViewResult);
        textViewAllResult = findViewById(R.id.textViewAllResult);
        buttonAgain = findViewById(R.id.buttonAgain);

        trueCounter = getIntent().getIntExtra("trueCounter",0);

        textViewResult.setText(trueCounter+" RÄTT "+(5-trueCounter)+" FEL");
        textViewAllResult.setText("% "+(trueCounter*100)/5+" Framgång");

        buttonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this,QuizActivity.class));
                finish();
            }
        });
    }
}
