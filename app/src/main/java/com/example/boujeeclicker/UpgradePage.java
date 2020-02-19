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
    TextView textView;
    String clickScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_page);
        backClick = findViewById(R.id.backClick);
        passive = findViewById(R.id.passive);
        textView = findViewById(R.id.test);
        passive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                if(extras!= null){
                    clickScore = extras.getString("clickScore");
                    textView.setText((""+clickScore));
                }
            }
        });
        backClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpgradePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
