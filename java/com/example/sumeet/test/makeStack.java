package com.example.sumeet.test;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;


public class makeStack extends ActionBarActivity {
    private LinearLayout linearLayout;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private ImageButton[] totes = new ImageButton[6];
    private ImageButton[] bins = new ImageButton[6];
    private int top = 0;
    private int bottom = 0;
    private int binClick = 0;
    private int toteClick = 0;
    public static boolean binToggle = false;
    public static boolean noodleToggle = false;
    public static boolean fromFeeder = false;
    public static boolean fromLandFill = false;
    private boolean toggle = false;
    private boolean topSet = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_stack);

        if(MainActivity.isTablet){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }



        Intent intent = getIntent();
        toggle = intent.getBooleanExtra("toggle",false);

        totes[0]=(ImageButton)(findViewById(R.id.imageButton2));
        totes[1]=(ImageButton)(findViewById(R.id.imageButton3));
        totes[2]=(ImageButton)(findViewById(R.id.imageButton4));
        totes[3]=(ImageButton)(findViewById(R.id.imageButton5));
        totes[4]=(ImageButton)(findViewById(R.id.imageButton6));
        totes[5]=(ImageButton)(findViewById(R.id.imageButton7));

        bins[0] = (ImageButton)(findViewById(R.id.imageButton8));
        bins[1] = (ImageButton)(findViewById(R.id.imageButton9));
        bins[2] = (ImageButton)(findViewById(R.id.imageButton10));
        bins[3] = (ImageButton)(findViewById(R.id.imageButton11));
        bins[4] = (ImageButton)(findViewById(R.id.imageButton12));
        bins[5] = (ImageButton)(findViewById(R.id.imageButton13));

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,showStacks.class);
        startActivity(intent);
    }

    public boolean onTouchEvent(MotionEvent event){
        linearLayout = (LinearLayout)(findViewById(R.id.activity_make_stack));
        linearLayout2 = (LinearLayout)(findViewById(R.id.linear_layout_totes));



        int resID = getResources().getIdentifier("tote","drawable",getPackageName());
        int resID2 = getResources().getIdentifier("grey","drawable",getPackageName());
        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX()<linearLayout2.getX()){
            for(int i = 0; i<totes.length; i++){
                totes[i].setImageResource(resID2);
            }
            for(int i = 0; i<bins.length; i++){
                bins[i].setImageResource(resID2);
            }

            binClick = 0;
            binToggle = false;
            noodleToggle = false;

        }


        return super.onTouchEvent(event);
    }

    public void setFromFeeder(View view){
        fromFeeder = true;
    }

    public void setFromLandFill(View view){
        fromLandFill = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_teleop, menu);
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

    public void changeTote(View input){
//        if(toggle){
//            top = showStacks.stacks.get(showStacks.temp).getTop();
//            bottom = showStacks.stacks.get(showStacks.temp).getBottom();
//        }
        toteClick++;
        //System.out.println("eyo");
        int resID4 = getResources().getIdentifier("grey","drawable",getPackageName());
        int resID3 = getResources().getIdentifier("bin","drawable",getPackageName());
        int resID2 = getResources().getIdentifier("tote2","drawable",getPackageName());
        int resID = getResources().getIdentifier("tote","drawable",getPackageName());
        int temp = 0;
        for(int i = 0; i<totes.length;i++){
            if(totes[i].getId()==input.getId()){
                if(top <= 0) {
                    top = i;
                }else if(bottom <= 0){
                    bottom = i;
                }else{
                    top = i;
                    bottom = 0;
                }
                if(bottom>top){
                    int x = top;
                    top = bottom;
                    bottom = x;
                }
            }
        }
        for(int i = 0; i < totes.length; i++){
            totes[i].setImageResource(resID);
            bins[i].setImageResource(resID4);

        }
        for(int i = bottom; i<=top; i++){
            totes[i].setImageResource(resID2);
            topSet = true;

        }
        for(int i = 0; i<top; i++){
            bins[i].setImageResource(resID4);
        }
        if(toteClick==1) {
            bins[top].setImageResource(resID3);
            temp = top;
        }
        if(toteClick==2){
            bins[temp].setImageResource(resID4);
            bins[top].setImageResource(resID3);
            toteClick = 0;
        }
    }

    public void changeBin(View input){
        int resID = getResources().getIdentifier("bin2","drawable",getPackageName());
        int resID2 = getResources().getIdentifier("noodle2","drawable",getPackageName());
        int resID3 = getResources().getIdentifier("grey","drawable",getPackageName());
        binClick++;
        if(topSet && binClick==1 ){
            for(int i = 0;i<bins.length;i++){
                if(bins[i].getId()==input.getId()){
                    bins[i].setImageResource(resID);
                    binToggle = true;
                }
            }
        }
        if(topSet && binClick==2){
            for(int i = 0; i<bins.length; i++){
                if(bins[i].getId()==input.getId()){
                    bins[i].setImageResource(resID2);
                    noodleToggle = true;
                }
            }
        }
        if(topSet && binClick==3){
            for(int i = 0; i<bins.length; i++){
                if(bins[i].getId()==input.getId()){
                    bins[i].setImageResource(resID3);
                    noodleToggle = false;
                    binToggle = false;
                }
            }
            binClick=0;
        }

    }

    //takes all the info on the stack and adds it as a new stack in the stack arraylist
    public void confirmTotes(View input){
        if(toggle) {
            showStacks.stacks.set(showStacks.temp1,new Stack(top, bottom, binToggle, noodleToggle,fromFeeder,fromLandFill));
        }else if(toggle==false){
            showStacks.stacks.add(new Stack(top,bottom,binToggle,noodleToggle,fromFeeder,fromLandFill));
        }
        for(int i = 0; i<totes.length; i++){
            totes[i].setImageResource(getResources().getIdentifier("tote","drawable",getPackageName()));
        }

        for(int i = 0; i<bins.length; i++){
            bins[i].setImageResource(getResources().getIdentifier("grey","drawable",getPackageName()));
        }
        binToggle = false;
        noodleToggle = false;

        Intent intent = new Intent(this,showStacks.class);
        startActivity(intent);

    }

}

