package com.example.boujeeclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    ImageView circle;
    TextView score;
    TextView incScore;
    AtomicInteger scoreNum;
    ConstraintLayout constraintLayout;
    int clickScore =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreNum = new AtomicInteger();
        incScore = new TextView(this);
        incScore.setId(View.generateViewId());
        circle = findViewById(R.id.circle);
        score = findViewById(R.id.score);
        constraintLayout = findViewById(R.id.constraint);
        final ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        scaleAnimation.setDuration(250);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                scoreNum.getAndAdd(clickScore);
                score.setText(scoreNum+"");
                incScore.setText(clickScore+"");
            }
        });
    }
}
