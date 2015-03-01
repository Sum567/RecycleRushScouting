package com.example.sumeet.test;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Autonomous extends ActionBarActivity {
    private TextView textView;
    public static int totesSetAuton = 0;
    public static int binsSetAuton = 0;
    public static boolean autonMove = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autonomous);
        if(MainActivity.isTablet){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_autonomous, menu);
        return true;
    }

    public void addOne(View view){
        textView = (TextView)findViewById(R.id.textView);
        totesSetAuton++;
        if(totesSetAuton>4){
            totesSetAuton = 4;
        }
        textView.setText("" + totesSetAuton);
    }

    public void subtractOne(View view){
        textView = (TextView)findViewById(R.id.textView);
        totesSetAuton--;
        if(totesSetAuton<0){
            totesSetAuton=0;
        }
        textView.setText("" + totesSetAuton);
    }
    
    public void add(View view){
        textView = (TextView)findViewById(R.id.textView10);
        binsSetAuton++;
        if(binsSetAuton>4){
            binsSetAuton = 4;
        }
        textView.setText("" + binsSetAuton);
    }
    public void subtract(View view){
        textView = (TextView)findViewById(R.id.textView10);
        binsSetAuton--;
        if(binsSetAuton<0){
            binsSetAuton = 0;
        }
        textView.setText("" + binsSetAuton);
    }

    public void setMoveTrue(View view){
        autonMove = true;
    }
    
    public void submitTotes(View view){
        Intent intent = new Intent(this, showStacks.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
