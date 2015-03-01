package com.example.sumeet.test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class autonKnockedOver extends ActionBarActivity {
    public TextView textView;
    public static int totesAdded = 0;
    public static int binsAdded = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton_knocked_over);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Autonomous.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_auton_knocked_over, menu);
        return true;
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

    public void addTote(View view){
        textView = (TextView)findViewById(R.id.textView16);
        totesAdded++;
        if(totesAdded>6){
            totesAdded = 6;
        }
        textView.setText(""+totesAdded);

    }

    public void subtractTote(View view){
        textView = (TextView)findViewById(R.id.textView16);
        totesAdded--;
        if(totesAdded<0){
            totesAdded=0;
        }

        textView.setText(""+totesAdded);
    }

    public void addBin(View view){
        textView = (TextView)findViewById(R.id.textView17);
        binsAdded++;
        if(binsAdded>6){
            binsAdded = 6;
        }
        textView.setText(""+binsAdded);
    }

    public void subtractBin(View view){
        textView = (TextView)findViewById(R.id.textView17);
        binsAdded--;
        if(binsAdded<0){
            binsAdded=0;
        }
        textView.setText(""+binsAdded);
    }

    public void startTeleop(View view){
        Intent intent = new Intent(this,showStacks.class);
        startActivity(intent);
    }

}
