package com.example.boujeeclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UpgradePage extends AppCompatActivity {
    Button backClick;
    ImageView passive;
    ImageView passiveAni;
    TextView clickQuantity;
    TextView money;
    int clickScore;
    int score;
    int clickThresh = 10;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_page);
        backClick = findViewById(R.id.backClick);
        passive = findViewById(R.id.passive);
        money = findViewById(R.id.money);
        clickQuantity = findViewById(R.id.test);
        constraintLayout = findViewById(R.id.constraint);
        final Intent intent = new Intent(UpgradePage.this, MainActivity.class);
        final Bundle extras = getIntent().getExtras();
        clickScore = Integer.parseInt(extras.getString("clickScore"));
        score = Integer.parseInt(extras.getString("scoreTotal"));
        money.setText("" + score);
        clickQuantity.setText(""+clickScore);
        passive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(extras!= null){
                    clickQuantity.setText((""+clickScore));
                }
                if(score >= clickThresh) {
                    clickScore++;
                    score -= clickThresh;
                    clickThresh*=1.5;
                    clickQuantity.setText((""+clickScore));
                    money.setText("" + score);
                }
                intent.putExtra("score", score+"");
                intent.putExtra("incScore", clickScore+"");

                //Animation

                passiveAni = new ImageView(UpgradePage.this);
                passiveAni.setId(View.generateViewId());
                passiveAni.setImageResource(R.drawable.passive);

                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                        50, 50
                );
                passiveAni.setLayoutParams(params);
                constraintLayout.addView(passiveAni);

                final ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);

                constraintSet.connect(passiveAni.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
                constraintSet.connect(passiveAni.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(passiveAni.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);
                constraintSet.connect(passiveAni.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);

                float rand = (float) ((Math.random() * 1.8f) + 0f);
                constraintSet.setHorizontalBias(passiveAni.getId(), rand);
                constraintSet.setVerticalBias(passiveAni.getId(), .3f);
                constraintSet.applyTo(constraintLayout);

                ObjectAnimator animation = ObjectAnimator.ofFloat(passiveAni, "translationY", -6000f);
                animation.setDuration(2000);
                animation.start();

            }
        });
        backClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("incScore", clickScore+"");
                intent.putExtra("score", score+"");
                startActivity(intent);
            }
        });
    }
}
