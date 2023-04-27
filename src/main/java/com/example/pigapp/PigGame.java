package com.example.pigapp;

import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.util.ArrayList;
import java.util.Random;


import android.content.Intent;

public class PigGame extends AppCompatActivity {
    private Button roll, stop;
    private TextView player1, player2, player1Score, player2Score, results, currentPlayer, totalRolls;
    private int score1Int, score2Int, turnTotal;
    private String playerTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pig_game);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        score1Int = 0;
        score2Int = 0;
        player1 = findViewById(R.id.p1Score);
        player2 = findViewById(R.id.p2Score);
        player1Score = findViewById(R.id.Score1);
        player2Score = findViewById(R.id.Score2);

        currentPlayer = findViewById(R.id.CurrentPlayer);
        totalRolls = findViewById(R.id.totalRoll);
        roll = findViewById(R.id.roll);
        stop = findViewById(R.id.stop);


        Intent intent = getIntent();
        playerTurn = intent.getStringExtra("player1");
        player1.setText(intent.getStringExtra("player1"));
        player2.setText(intent.getStringExtra("player2"));
        player1Score.setText(String.valueOf(score1Int));
        player2Score.setText(String.valueOf(score2Int));
        currentPlayer.setText(playerTurn);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop.setEnabled(true);
                Random rand = new Random();
                int rand1 = rand.nextInt(6) + 1;
                int rand2 = rand.nextInt(6) + 1;

                if(((rand1 == 1) && (rand2 != 1)) || ((rand1 != 1) && (rand2 == 1)))
                {
                    if(playerTurn.equals(player1.getText().toString()))
                    {
                        playerTurn = player2.getText().toString();
                        currentPlayer.setText(playerTurn);
                        turnTotal = 0;
                        totalRolls.setText(String.valueOf(turnTotal));
                        Toast.makeText(PigGame.this, "Your rolled a one. Your turn is over", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        playerTurn = player1.getText().toString();
                        currentPlayer.setText(playerTurn);
                        turnTotal = 0;
                        totalRolls.setText(String.valueOf(turnTotal));
                        Toast.makeText(PigGame.this, "Your rolled a one. Your turn is over", Toast.LENGTH_SHORT).show();
                    }
                } else if((rand1==1) && (rand2 ==1))
                {
                    if(playerTurn.equals(player1.getText().toString()))
                    {
                        turnTotal = 0;
                        totalRolls.setText(String.valueOf(turnTotal));
                        score1Int = 0;
                        player1Score.setText(String.valueOf(score1Int));
                        playerTurn = player2.getText().toString();
                        currentPlayer.setText(playerTurn);
                        Toast.makeText(PigGame.this, "Your rolled two one's. You lost all of your points", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        turnTotal = 0;
                        totalRolls.setText(String.valueOf(turnTotal));
                        score2Int = 0;
                        player2Score.setText(String.valueOf(score2Int));
                        playerTurn = player1.getText().toString();
                        currentPlayer.setText(playerTurn);
                        Toast.makeText(PigGame.this, "Your rolled two one's. You lost all of your points", Toast.LENGTH_SHORT).show();
                    }
                }  else if (rand1 == rand2)
                {
                    turnTotal += (rand1 + rand2);
                    totalRolls.setText(String.valueOf(turnTotal));
                    stop.setEnabled(false);
                    Toast.makeText(PigGame.this, "Your rolled two of a kind. You can't stop now!", Toast.LENGTH_SHORT).show();
                } else
                {
                    turnTotal += (rand1 + rand2);
                    totalRolls.setText(String.valueOf(turnTotal));
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playerTurn.equals(player1.getText().toString())) {
                    score1Int += turnTotal;
                    player1Score.setText(String.valueOf(score1Int));
                    playerTurn = player2.getText().toString();
                    currentPlayer.setText(playerTurn);
                    turnTotal = 0;
                } else if (playerTurn.equals(player2.getText().toString())) {
                    score2Int += turnTotal;
                    player2Score.setText(String.valueOf(score2Int));
                    playerTurn = player1.getText().toString();
                    currentPlayer.setText(playerTurn);
                    turnTotal = 0;
                }
                totalRolls.setText(String.valueOf(turnTotal));
                if (score1Int >= 100) {
                    currentPlayer.setText(player1.getText().toString());
                    Intent intent = new Intent(PigGame.this, Winner.class);
                    String getName = player1.getText().toString();
                    intent.putExtra("winner", getName);
                    startActivity(intent);
                } else if (score2Int >= 100) {
                    currentPlayer.setText(player2.getText().toString());
                    Intent intent = new Intent(PigGame.this, Winner.class);
                    String getName = player2.getText().toString();
                    intent.putExtra("winner", getName);
                    startActivity(intent);
                }
            }
        });
    }

}