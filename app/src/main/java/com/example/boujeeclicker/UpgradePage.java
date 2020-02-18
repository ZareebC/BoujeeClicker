package com.example.boujeeclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpgradePage extends AppCompatActivity {
    Button backClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_page);
        backClick = findViewById(R.id.backClick);
        backClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpgradePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
