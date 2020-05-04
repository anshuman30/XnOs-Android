package com.anshu2000vizag.xnos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int player = 0;
    int[][] grid = new int [3][3];
    void initialise(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                grid[i][j]=3;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initialise();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        if(counter.getDrawable() == null) {
            counter.setTranslationY(-1000f);
            GridLayout gr = (GridLayout) findViewById(R.id.GridOfGame);
            int i,j;
            int pos;
            if (player == 0) {
                counter.setImageResource(R.drawable.cross);
                pos = Integer.parseInt(counter.getTag().toString());
                i = pos/3;
                j = pos%3;
                grid[i][j]=player;
                player = 1;

                if(check()){
                    System.out.println("P1 wins!");
                    winner(0);
                }
            }
            else {

                counter.setImageResource(R.drawable.oh);
                pos = Integer.parseInt(counter.getTag().toString());
                i = pos/3;
                j = pos%3;
                grid[i][j]=player;
                player = 0;
                if(check()){
                    System.out.println("P2 wins!");
                    winner(1);
                }
            }
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
        }
    }
    public void winner(int i){
        LinearLayout ll = (LinearLayout) findViewById(R.id.WinnerDisplay);
        TextView tv = (TextView) findViewById(R.id.textView2);
        if(i==0) {
            tv.setText("P1 Wins!");
        }
        else if(i==1){
            tv.setText("P2 Wins!");
        }
        ll.setVisibility(View.VISIBLE);
    }
    public void Restart(View view){
        player = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                grid[i][j]=3;
            }
        }
        LinearLayout ll = (LinearLayout) findViewById(R.id.WinnerDisplay);
        ll.setVisibility(View.INVISIBLE);
        GridLayout gr = (GridLayout) findViewById(R.id.GridOfGame);
        for(int i=0;i<gr.getChildCount();i++){
            ((ImageView) gr.getChildAt(i)).setImageResource(0);
        }
    }
    public boolean check(){

        for(int i=0;i<3;i++){
            if(grid[i][0]==grid[i][1] && grid[i][1]==grid[i][2] && grid[i][0]!=3){
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(grid[0][i]==grid[1][i] && grid[1][i]==grid[2][i] && grid[i][0]!=3){
                return true;
            }
        }
        if(grid[0][0]==grid[1][1] && grid[1][1]==grid[2][2] && grid[0][0]!=3){
            return true;
        }
        if(grid[0][2]==grid[1][1] && grid[1][1]==grid[2][0] && grid[2][0]!=3){
            return true;
        }
        return false;

    }

}
