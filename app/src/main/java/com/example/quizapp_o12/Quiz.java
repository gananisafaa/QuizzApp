package com.example.quizapp_o12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.quizapp_o12.Model.QuestionAnswer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Quiz extends AppCompatActivity {

    private Button buttonNext, buttonPrevious;
    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private RadioButton answer4;
    private TextView question;
    private ImageView image;
    private int score=0;
    private int cmpt=0;
    QuestionAnswer questionAnswer;
    DatabaseReference databaseReference;
    DatabaseReference option1;
    DatabaseReference option2;
    DatabaseReference option3;
    DatabaseReference option4;
    private String TAG="Quizz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        answer1=(RadioButton) findViewById(R.id.radio_1);
        answer2=(RadioButton) findViewById(R.id.radio_2);
        answer3=(RadioButton) findViewById(R.id.radio_3);
        answer4=(RadioButton) findViewById(R.id.radio_4);
        question=(TextView) findViewById(R.id.tvQuestion);
        image=(ImageView) findViewById(R.id.ivImage);
        buttonNext= (Button) findViewById(R.id.bNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateView();
            }
        });
        buttonPrevious= (Button) findViewById(R.id.bPrevious);
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        updateView();

    }

    private void updateView() {

        if(cmpt<12) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("questions").child(String.valueOf(cmpt));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    questionAnswer = snapshot.getValue(QuestionAnswer.class);

                    question.setText(questionAnswer.getQuestion());
                    answer1.setText(questionAnswer.getAnswer1());
                    answer2.setText(questionAnswer.getAnswer2());
                    answer3.setText(questionAnswer.getAnswer3());
                    answer4.setText(questionAnswer.getAnswer4());
                    Picasso.with(getApplicationContext()).load(questionAnswer.getImage()).into(image);

                    answer1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (questionAnswer.getCorrectIndex() == 0) score++;

                        }
                    });
                    answer2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (questionAnswer.getCorrectIndex() == 1) score++;

                        }
                    });
                    answer3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (questionAnswer.getCorrectIndex() == 2) score++;

                        }
                    });
                    answer4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (questionAnswer.getCorrectIndex()== 3) score++;

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            cmpt++;
        }
        else
        {
            Intent intent=new Intent(this, Score.class);
            intent.putExtra("score", score);
            startActivity(intent);
        }
    }

}