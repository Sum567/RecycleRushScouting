package com.example.sumeet.test;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class makeBinStack extends ActionBarActivity {
    private LinearLayout linearLayout;
    private ImageButton[]bins = new ImageButton[6];
    private int level;
    private boolean toggle = false;
    private int binClick = 0;
    private boolean hasNoodle = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_bin_stack);

        if(MainActivity.isTablet){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        bins[0] = (ImageButton)(findViewById(R.id.imageButton42));
        bins[1] = (ImageButton)(findViewById(R.id.imageButton43));
        bins[2] = (ImageButton)(findViewById(R.id.imageButton44));
        bins[3] = (ImageButton)(findViewById(R.id.imageButton45));
        bins[4] = (ImageButton)(findViewById(R.id.imageButton46));
        bins[5] = (ImageButton)(findViewById(R.id.imageButton47));

    }

    public void onBackPressed(){
        Intent intent = new Intent(this,showStacks.class);
        startActivity(intent);
    }

    public boolean onTouchEvent(MotionEvent event){
        linearLayout = (LinearLayout)findViewById(R.id.linear_layout_bins);
        int resID = getResources().getIdentifier("grey","drawable",getPackageName());
        for(int i = 0; i<bins.length; i++){
            bins[i].setImageResource(resID);

        }

        return super.onTouchEvent(event);
    }

    public void changeBin(View view){
        binClick++;
        toggle = !toggle;
        int resID = getResources().getIdentifier("bin2","drawable",getPackageName());
        int resID2 = getResources().getIdentifier("noodle2","drawable",getPackageName());
        int resID3 = getResources().getIdentifier("bin","drawable",getPackageName());


        if(binClick==1){
            for(int i = 0; i<bins.length; i++){
                if(bins[i].getId()==view.getId()){
                    bins[i].setImageResource(resID);
                }
            }
        }else if(binClick==2){
            hasNoodle = true;
            for(int i = 0; i<bins.length; i++){
                if(bins[i].getId() == view.getId()){
                    bins[i].setImageResource(resID2);
                }
            }
        }else if(binClick ==3){
            for(int i = 0; i<bins.length; i++){
                if(bins[i].getId()==view.getId()){
                    bins[i].setImageResource(resID3);
                }
            }
            binClick = 0;
        }
    }

    public void finishBinStack(View view){
        if(!hasNoodle) {
            showStacks.binStacks.add(new Stack(level, level, true, false, false, false));
        }else{
            showStacks.binStacks.add(new Stack(level,level,true,hasNoodle,false,false));
        }

        Intent intent = new Intent(this,showStacks.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_make_bin_stack, menu);
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
}
