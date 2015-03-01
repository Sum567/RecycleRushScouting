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


public class makeYellowStack extends ActionBarActivity {
    private LinearLayout linearLayout;
    private ImageButton[] imageButtons = new ImageButton[4];
    private int top = 0;
    private int bottom = 0;
    private int toteClick = 0;
    private boolean toggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_yellow_stack);

        if(MainActivity.isTablet){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }



        Intent intent = getIntent();
        toggle = intent.getBooleanExtra("toggle",false);

        imageButtons[0] = (ImageButton)findViewById(R.id.imageButton22);
        imageButtons[1] = (ImageButton)findViewById(R.id.imageButton23);
        imageButtons[2] = (ImageButton)findViewById(R.id.imageButton24);
        imageButtons[3] = (ImageButton)findViewById(R.id.imageButton25);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,showStacks.class);
        startActivity(intent);
    }

    public boolean onTouchEvent(MotionEvent event){
        linearLayout = (LinearLayout)findViewById(R.id.yellow_totes);
        int resID = getResources().getIdentifier("yellowtote","drawable",getPackageName());

        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX()>linearLayout.getX()){
            for(int i = 0; i<imageButtons.length; i++){
                imageButtons[i].setImageResource(resID);
            }
        }

        return super.onTouchEvent(event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_make_yellow_stack, menu);
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

    public void changeTote(View view){
        int resID = getResources().getIdentifier("yellowtote","drawable",getPackageName());
        int resID2 = getResources().getIdentifier("yellowtote2","drawable",getPackageName());

        for(int i = 0; i<imageButtons.length;i++){
            if(imageButtons[i].getId()==view.getId()){
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

        for(int i = 0; i < imageButtons.length; i++){
            imageButtons[i].setImageResource(resID);
        }
        for(int i = bottom; i<=top; i++){
            imageButtons[i].setImageResource(resID2);
        }

    }

    public void confirmTotes(View view){
        if(toggle){
            showStacks.yellowStacks.set(showStacks.temp2,new Stack(top,bottom,false,false,false,false));
        }
     if (toggle==false){
            showStacks.yellowStacks.add(new Stack(top,bottom,false,false,false,false));
        }

        for(int i = 0; i<imageButtons.length; i++){
            imageButtons[i].setImageResource(getResources().getIdentifier("yellowtote","drawable",getPackageName()));
        }

        Intent intent = new Intent(this,showStacks.class);
        startActivity(intent);

    }
}
