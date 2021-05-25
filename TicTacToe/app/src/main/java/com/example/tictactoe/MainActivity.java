package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[3][3];

    private Boolean player1Turn = true;

    private int roundCounts;

    private int player1Points1;
    private int player1Points2;
    private TextView text_view_p1;
    private TextView text_view_p2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_view_p1 = findViewById(R.id.text_view_p1);
        text_view_p2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "bouuton_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (((Button) v).getText().toString().equals("")) {
            return;

        }
        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("o");
        }
        roundCounts++;
        if(checkForWin()){
            if(player1Turn){
                player1Win();
            }else {
                player2Win();
            }
        }else if(roundCounts==9){
            draw();
        }else{
            player1Turn=!player1Turn;
        }
    }


    private Boolean checkForWin() {
        String[][] field = new String[3][3];


        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
            return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }
    private  void player1Win(){

        player1Points1++;
        Toast.makeText(this, "player 1 Win", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }
    private void  player2Win(){
        player1Points2++;
        Toast.makeText(this, "player 2 Win", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void  draw(){
        Toast.makeText(this, "draw", Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private  void updatePointsText(){
        text_view_p1.setText("player 1: " +player1Points1);
        text_view_p2.setText("player 2: " +player1Points2);
    }

    private void resetBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
             buttons[i][j].setText("");
            }
    }
        roundCounts=0;
        player1Turn=true;
}
          private void resetGame(){
    player1Points1=0;
    player1Points2=0;
    updatePointsText();
    resetBoard();
}

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        savedInstanceState.putInt("rounCount",roundCounts);
        savedInstanceState.putInt("player1Points",player1Points1);
        savedInstanceState.putInt("player2Points",player1Points2);
        savedInstanceState.putBoolean("playerTurn1", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCounts=savedInstanceState.getInt("roundcount");
        player1Points1=savedInstanceState.getInt("player2points");
        player1Points2=savedInstanceState.getInt("player2points");
        player1Turn=savedInstanceState.getBoolean("player1Turn");
    }
}