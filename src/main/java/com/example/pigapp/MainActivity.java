package com.example.pigapp;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText player1Name, player2Name;
    private Button startButton;
    private int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        player1Name = findViewById(R.id.player1Name);
        player2Name = findViewById(R.id.player2Name);
        startButton = findViewById(R.id.startButton);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String get1Name = player1Name.getText().toString();
                String get2Name = player2Name.getText().toString();

                Intent intent = new Intent(MainActivity.this, PigGame.class);
                intent.putExtra("player1", get1Name);
                intent.putExtra("player2", get2Name);
                startActivity(intent);
            }
        });
    }
}