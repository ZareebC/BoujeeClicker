package com.example.boujeeclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    ImageView circle;
    TextView score;
    TextView incScore;
    Button upgradeButton;
    AtomicInteger scoreNum;
    ConstraintLayout constraintLayout;
    int clickScore =2;
    int scoreNumAdd = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreNum = new AtomicInteger();
        circle = findViewById(R.id.passive);
        upgradeButton = findViewById(R.id.upgradeButton);
        score = findViewById(R.id.score);
        constraintLayout = findViewById(R.id.constraint);
        final Intent intent = new Intent(MainActivity.this, UpgradePage.class);
        final Bundle extras = getIntent().getExtras();
        if(extras!= null) {
            scoreNumAdd = Integer.parseInt(extras.getString("score"));
        }
        scoreNum.set(scoreNumAdd);
        score.setText(scoreNum+"");

        //Cookie Animation
        final ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        scaleAnimation.setDuration(250);

        //Cookie Click Method
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                if(extras!= null) {
                    clickScore = Integer.parseInt(extras.getString("incScore"));
                }
                scoreNum.getAndAdd(clickScore);
                scoreNumAdd += clickScore;
                score.setText(scoreNum+"");
                //Animated +1
                incScore = new TextView(MainActivity.this);
                incScore.setId(View.generateViewId());

                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
                );
                incScore.setLayoutParams(params);
                constraintLayout.addView(incScore);

                final ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);

                constraintSet.connect(incScore.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
                constraintSet.connect(incScore.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(incScore.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);
                constraintSet.connect(incScore.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);

                incScore.setText(clickScore+"");
                float rand = (float)((Math.random()*.5f)+.3f);
                constraintSet.setHorizontalBias(incScore.getId(), rand);
                constraintSet.setVerticalBias(incScore.getId(), .3f);
                constraintSet.applyTo(constraintLayout);

                ObjectAnimator animation = ObjectAnimator.ofFloat(incScore, "translationY", -6000f);
                animation.setDuration(2000);
                animation.start();
            }
        });
        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("clickScore",  ""+clickScore);
                intent.putExtra("scoreTotal", ""+scoreNumAdd);
                startActivity(intent);
            }
        });
    }
}
