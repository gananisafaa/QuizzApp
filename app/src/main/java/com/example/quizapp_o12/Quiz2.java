package com.example.quizapp_o12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Quiz2 extends AppCompatActivity {
    private Button buttonNext, buttonPrevious;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        Intent intent = getIntent();
        this.score=intent.getIntExtra("score",0);
        buttonNext= (Button) findViewById(R.id.bNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextQuiz();
            }
        });
        buttonPrevious= (Button) findViewById(R.id.bPrevious);
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToPreviousQuiz();
            }
        });
    }
    private void backToPreviousQuiz() {
        Intent intent=new Intent(this, Quiz1.class);
        startActivity(intent);
    }

    public void goToNextQuiz() {
        Intent intent=new Intent(this, Quiz3.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_CD:
                if (checked) {
                    score++;
                    break;
                }
            case R.id.radio_dg:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radio_gucci:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radio_lv:
                if (checked)
                break;

        }
    }
}