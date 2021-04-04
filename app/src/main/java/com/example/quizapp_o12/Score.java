package com.example.quizapp_o12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Score extends AppCompatActivity {
    private Button buttonPlayAgain;
    private TextView textView;
    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        this.score=intent.getIntExtra("score",0);
        textView=(TextView) findViewById(R.id.tvScoreValue);
        textView.setText(score+"/12");
        buttonPlayAgain= (Button) findViewById(R.id.bPlayAgain);
        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
            }
        });

    }
    private void playAgain() {
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(this, Login.class);
        startActivity(intent);
    }
}