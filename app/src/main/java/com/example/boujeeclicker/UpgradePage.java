package com.example.boujeeclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UpgradePage extends AppCompatActivity {
    Button backClick;
    ImageView passive;
    TextView clickQuantity;
    TextView money;
    int clickScore;
    int score;
    int clickThresh = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_page);
        backClick = findViewById(R.id.backClick);
        passive = findViewById(R.id.passive);
        money = findViewById(R.id.money);
        clickQuantity = findViewById(R.id.test);
        final Intent intent = new Intent(UpgradePage.this, MainActivity.class);
        final Bundle extras = getIntent().getExtras();
        clickScore = Integer.parseInt(extras.getString("clickScore"));
        score = Integer.parseInt(extras.getString("scoreTotal"));
        money.setText("" + score);
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
                    money.setText("" + score);
                }
                intent.putExtra("score", score+"");
                intent.putExtra("incScore", clickScore+"");
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
